import {useState} from 'react';

export function LoginPage() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleLogin = async (e) => {
    e.preventDefault();
    setError('');

    try {
      const resp = await fetch('/api/auth/login', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({username, password}),
      });
      if (resp.ok) {
        const data = await resp.json();
        localStorage.setItem('token', data.token);
        // this triggers full reload, which resets 'isLoggedIn' state of Header.
        location.href = '/';
      } else {
        const errData = await resp.json();
        setError(errData.message || '로그인에 실패했습니다.');
      }
    } catch (error) {
      setError('서버와의 통신에 실패했습니다.');
    }
  };

  return (
      <div className="login-container">
        <h2>로그인</h2>
        <form onSubmit={handleLogin}>
          <label htmlFor="username">아이디</label>
          <input id="username" value={username} onChange={e => setUsername(e.target.value)} placeholder="아이디" required="required"/>
          <label htmlFor="password">비밀번호</label>
          <input id="password" type="password" value={passowrd} onChange={e => setPassword(e.target.value)} placeholder="비밀번호" required="required"/>
          <input type="submit" value="로그인"/>
        </form>
        {error && <div style={{color: 'red'}}>{error}</div>}
      </div>
  );
}
