import {useState} from 'react';
import {useNavigate} from 'react-router-dom';
import {useAuth} from '../context/AuthContext.jsx';

export function LoginPage() {
  const {setUser} = useAuth();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    const resp = await fetch('/api/auth/login', {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({username, password}),
    });

    if (resp.ok) {
      const userData = await resp.json();
      setUser(userData);
      navigate('/');
    } else {
      setError('로그인 실패: 아이디나 비밀번호를 확인하세요');
    }
  };

  return (
      <div className="login-container">
        <h2>로그인</h2>
        <form onSubmit={handleLogin}>
          <label htmlFor="username">아이디</label>
          <input id="username" value={username} onChange={e => setUsername(e.target.value)} placeholder="아이디" required="required"/>
          <label htmlFor="password">비밀번호</label>
          <input id="password" type="password" value={password} onChange={e => setPassword(e.target.value)} placeholder="비밀번호" required="required"/>
          <input type="submit" value="로그인"/>
        </form>
        {error && <div style={{color: 'red'}}>{error}</div>}
      </div>
  );
}
