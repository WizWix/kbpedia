import {Link, useNavigate} from 'react-router-dom';
import {useAuth} from '../../context/AuthContext.jsx';
import {apiFetch} from '../../util/api_fetch.js';

export function Header() {
  const {user, setUser} = useAuth();
  const navigate = useNavigate();

  const handleLogout = async (e) => {
    e.preventDefault();
    await apiFetch('/api/auth/logout', {method: 'POST'});
    setUser(null);
    navigate('/');
  };

  return (
      <header className="app-header">
        <Link to="/">
          <img src="/assets/img/logo.png" alt="Logo" height="30px"/>
        </Link>
        <nav>
          {user ? (
              <p>
                환영합니다, {user.username}.&nbsp;<Link to="/my">회원 정보</Link>&nbsp;|&nbsp;<a href="#" onClick={handleLogout}>로그아웃</a>
              </p>
          ) : (
              <p>
                <Link to="/register">회원 가입</Link>&nbsp;|&nbsp;<Link to="/login">로그인</Link>
              </p>
          )}
        </nav>
      </header>
  );
}
