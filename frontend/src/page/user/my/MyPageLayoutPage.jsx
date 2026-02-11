import {NavLink} from 'react-router-dom';
import './MyPageLayoutPage.css';

const tabs = [
  {to: '/my/account', label: '회원 정보'},
  {to: '/my/profile', label: '프로필'},
  {to: '/my/articles', label: '내 글'},
  {to: '/my/favorites', label: '즐겨찾기'},
];

export function MyPageLayoutPage() {
  return (
      <div className="mypage-layout">
        <aside className="mypage-sidebar">
          <nav className="mypage-tabs">
            {tabs.map(t => (
                <NavLink key={t.to} to={t.to} className={({isActive}) => `mypage-tab ${isActive ? 'active' : ''}`}>
                  {t.label}
                </NavLink>
            ))}
          </nav>
        </aside>
        <main className="mypage-content">

        </main>
      </div>
  );
}
