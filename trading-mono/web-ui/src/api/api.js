import axios from 'axios'

// Use Vite env var (VITE_API_BASE) in the browser, fallback to localhost
const API_BASE = import.meta.env.VITE_API_BASE || 'http://localhost:8080'

const api = axios.create({ baseURL: API_BASE })

// simple token handling
api.interceptors.request.use(config => {
  const token = localStorage.getItem('access_token')
  if (token) config.headers.Authorization = `Bearer ${token}`
  return config
})

api.interceptors.response.use(
  r => r,
  async err => {
    const original = err.config
    if (err.response && err.response.status === 401 && !original._retry) {
      original._retry = true
      const refresh = localStorage.getItem('refresh_token')
      if (!refresh) return Promise.reject(err)
      try {
        const resp = await axios.post(`${API_BASE}/api/auth/reissue`, { refreshToken: refresh })
        const { accessToken, refreshToken } = resp.data || {}
        if (accessToken) localStorage.setItem('access_token', accessToken)
        if (refreshToken) localStorage.setItem('refresh_token', refreshToken)
        original.headers.Authorization = `Bearer ${accessToken}`
        return api(original)
      } catch (e) {
        localStorage.removeItem('access_token')
        localStorage.removeItem('refresh_token')
        return Promise.reject(e)
      }
    }
    return Promise.reject(err)
  }
)

export async function signup(payload) {
  const res = await api.post('/api/auth/signup', payload)
  return res.data
}

export async function login(payload) {
  const res = await api.post('/api/auth/login', payload)
  // assume response contains accessToken/refreshToken
  if (res.data?.accessToken) localStorage.setItem('access_token', res.data.accessToken)
  if (res.data?.refreshToken) localStorage.setItem('refresh_token', res.data.refreshToken)
  return res.data
}

export async function reissue(payload) {
  const res = await api.post('/api/auth/reissue', payload)
  return res.data
}

export async function getQuote(symbol) {
  const res = await api.get(`/api/market/quote/${encodeURIComponent(symbol)}`)
  return res.data
}

export async function getQuotesBulk(symbolsCsv) {
  const res = await api.get(`/api/market/quote`, { params: { symbols: symbolsCsv } })
  return res.data
}

export async function getHistorical(symbol, start, end, interval = '1d') {
  const res = await api.get(`/api/market/historical/${encodeURIComponent(symbol)}`, {
    params: { start, end, interval }
  })
  return res.data
}

export async function getInfo(symbol) {
  const res = await api.get(`/api/market/info/${encodeURIComponent(symbol)}`)
  return res.data
}

export async function getSnapshot(symbol) {
  const res = await api.get(`/api/market/snapshot/${encodeURIComponent(symbol)}`)
  return res.data
}
