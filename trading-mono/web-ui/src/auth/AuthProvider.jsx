import React, { createContext, useContext, useEffect, useState } from 'react'
import { login as apiLogin, signup as apiSignup } from '../api/api'

const AuthContext = createContext(null)

export function AuthProvider({ children }) {
  const [user, setUser] = useState(null)

  useEffect(() => {
    const token = localStorage.getItem('access_token')
    if (token) setUser({ authenticated: true })
  }, [])

  async function login(payload) {
    const res = await apiLogin(payload)
    setUser({ authenticated: true })
    return res
  }

  async function signup(payload) {
    const res = await apiSignup(payload)
    return res
  }

  function logout() {
    localStorage.removeItem('access_token')
    localStorage.removeItem('refresh_token')
    setUser(null)
  }

  return (
    <AuthContext.Provider value={{ user, login, signup, logout }}>
      {children}
    </AuthContext.Provider>
  )
}

export function useAuth() {
  return useContext(AuthContext)
}
