import React, { useCallback, useEffect, useMemo, useState } from 'react'
import { getMyAccounts, getSnapshot } from '../api/api'
import SymbolSearch from '../components/SymbolSearch'
import { useAuth } from '../auth/AuthProvider'

export default function Trade() {
  const auth = useAuth()
  const [symbol, setSymbol] = useState('AAPL')
  const [snapshot, setSnapshot] = useState(null)
  const [accounts, setAccounts] = useState([])
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)
  const [orderType, setOrderType] = useState('MARKET')
  const [quantity, setQuantity] = useState('')
  const [limitPrice, setLimitPrice] = useState('')
  const [notice, setNotice] = useState(null)

  const currency = snapshot?.currency || snapshot?.info?.currency || ''
  const price = snapshot?.current_price || snapshot?.quote?.current_price || 0

  const totalAvailable = useMemo(() => {
    return accounts.reduce((sum, acct) => sum + (Number(acct.availableCash) || 0), 0)
  }, [accounts])

  const estimatedCost = useMemo(() => {
    const qty = Number(quantity) || 0
    const unit = orderType === 'LIMIT' ? Number(limitPrice) || 0 : Number(price) || 0
    return qty * unit
  }, [quantity, limitPrice, orderType, price])

  const fetchSnapshot = useCallback(async nextSymbol => {
    const target = (nextSymbol || symbol || '').trim()
    if (!target) return
    setLoading(true)
    setError(null)
    setNotice(null)
    try {
      const data = await getSnapshot(target)
      setSnapshot(data)
      const acctList = await getMyAccounts({ currencies: [data?.currency || data?.info?.currency].filter(Boolean) })
      setAccounts(Array.isArray(acctList) ? acctList : [])
    } catch (e) {
      setError(e.response?.data || e.message)
      setSnapshot(null)
      setAccounts([])
    } finally {
      setLoading(false)
    }
  }, [symbol])

  useEffect(() => {
    fetchSnapshot(symbol)
  }, [])

  const onSubmit = e => {
    e.preventDefault()
    setNotice('구매 API가 아직 연결되어 있지 않습니다.')
  }

  return (
    <div className="trade-page">
      <section className="trade-hero">
        <div>
          <h2>주식 구매</h2>
          <p className="trade-subtitle">종목 가격과 동일 통화 계좌 잔고를 함께 확인하세요.</p>
        </div>
        <div className="trade-meta">
          {auth?.user ? <span>로그인됨</span> : <span>로그인이 필요합니다</span>}
          <button className="ghost-button" onClick={() => fetchSnapshot(symbol)} disabled={loading}>
            {loading ? '불러오는 중...' : '가격 조회'}
          </button>
        </div>
      </section>

      <section className="trade-search">
        <SymbolSearch value={symbol} onChange={setSymbol} onSelect={setSymbol} />
        <button className="primary-button" onClick={() => fetchSnapshot(symbol)} disabled={loading}>
          조회
        </button>
      </section>

      {error && <div className="trade-alert is-error">{JSON.stringify(error)}</div>}
      {notice && <div className="trade-alert">{notice}</div>}

      <section className="trade-grid">
        <div className="trade-card">
          <h3>현재 시세</h3>
          {snapshot ? (
            <div className="trade-quote">
              <div>
                <span>심볼</span>
                <strong>{snapshot.symbol}</strong>
              </div>
              <div>
                <span>현재가</span>
                <strong>{price}</strong>
              </div>
              <div>
                <span>통화</span>
                <strong>{currency || '-'}</strong>
              </div>
              <div>
                <span>이전 종가</span>
                <strong>{snapshot?.quote?.previous_close ?? '-'}</strong>
              </div>
            </div>
          ) : (
            <div className="trade-empty">종목을 조회하세요.</div>
          )}
        </div>

        <div className="trade-card">
          <h3>동일 통화 계좌 잔고</h3>
          {currency ? (
            <div className="trade-balance">
              <div className="trade-balance__total">
                <span>사용 가능 합계</span>
                <strong>{totalAvailable}</strong>
              </div>
              {accounts.length === 0 ? (
                <div className="trade-empty">해당 통화 계좌가 없습니다.</div>
              ) : (
                <ul className="trade-account-list">
                  {accounts.map(account => (
                    <li key={account.accountNo}>
                      <div>
                        <strong>{account.accountNo}</strong>
                        <span>{account.type}</span>
                      </div>
                      <div>
                        <span>Available</span>
                        <strong>{account.availableCash}</strong>
                      </div>
                    </li>
                  ))}
                </ul>
              )}
            </div>
          ) : (
            <div className="trade-empty">통화 정보를 확인할 수 없습니다.</div>
          )}
        </div>
      </section>

      <section className="trade-card trade-order">
        <h3>구매 주문</h3>
        <form onSubmit={onSubmit} className="trade-form">
          <label>주문 유형</label>
          <select value={orderType} onChange={e => setOrderType(e.target.value)}>
            <option value="MARKET">MARKET</option>
            <option value="LIMIT">LIMIT</option>
          </select>
          <label>수량</label>
          <input
            type="number"
            min="0"
            value={quantity}
            onChange={e => setQuantity(e.target.value)}
            placeholder="0"
          />
          {orderType === 'LIMIT' && (
            <>
              <label>지정가</label>
              <input
                type="number"
                min="0"
                value={limitPrice}
                onChange={e => setLimitPrice(e.target.value)}
                placeholder="0"
              />
            </>
          )}
          <div className="trade-summary">
            <div>
              <span>예상 주문 금액</span>
              <strong>{estimatedCost}</strong>
            </div>
            <div>
              <span>사용 가능 합계</span>
              <strong>{totalAvailable}</strong>
            </div>
          </div>
          <button type="submit" className="primary-button" disabled={!snapshot}>
            구매
          </button>
        </form>
      </section>
    </div>
  )
}
