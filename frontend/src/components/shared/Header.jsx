import {useEffect, useState} from 'react';
import {Link, useNavigate} from 'react-router-dom';

export function Header() {
  const navigate = useNavigate();
  const token = localStorage.getItem('token');
  const isLoggedIn = !!token;

  const handleLogout = (e) => {
    e.preventDefault();
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    navigate('/')
    location.reload();
  };

  return (
      <header className="app-header">
        <Link to="/">
          <img src="/assets/img/logo.png" alt="Logo" height="30px"/>
        </Link>
        <nav>
          {isLoggedIn ? (<p>
            <Link to="/my">회원 정보</Link>&nbsp;|&nbsp;<a onClick={handleLogout}>로그아웃</a>
          </p>) : (<p>
            <Link to="/register">회원 가입</Link>&nbsp;|&nbsp;<Link to="/login">로그인</Link>
          </p>)}
        </nav>
      </header>
  );
}
