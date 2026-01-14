import React from 'react'
import { Routes, Route, Link, useNavigate } from 'react-router-dom'
import Home from './pages/Home'
import Login from './pages/Login'
import Signup from './pages/Signup'
import Market from './pages/Market'
import { useAuth } from './auth/AuthProvider'
import ThemeToggle from './components/ThemeToggle'

export default function App() {
  const auth = useAuth()
  const navigate = useNavigate()

  function handleLogout() {
    auth.logout()
    navigate('/login')
  }

  return (
    <div className="app">
      <header className="app-header">
        <h1>Trading Charts</h1>
        <nav className="nav-right">
          <Link to="/">Home</Link>
          {!auth?.user ? (
            <>
              <Link to="/login">Login</Link>
              <Link to="/signup">Signup</Link>
              <Link to="/market">Market</Link>
              <ThemeToggle />
            </>
          ) : (
            <>
              <span className="greeting">안녕하세요</span>
              <button onClick={handleLogout} className="logout">Logout</button>
              <ThemeToggle />
            </>
          )}
        </nav>
      </header>

      <main>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/market" element={<Market />} />
        </Routes>
      </main>
    </div>
  )
}
