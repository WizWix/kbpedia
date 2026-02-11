import {useAuth} from '../../context/AuthContext.jsx';
import {Navigate, useLocation} from 'react-router-dom';

export function ProtectedRoute({children, adminOnly = false}) {
  const {user, loading} = useAuth();
  const location = useLocation();

  if (loading) return <div>로딩 중...</div>;

  if (!user) return <Navigate to="/login" state={{from: location}} replace/>;

  if (adminOnly) {
    const hasAdmin = user.roles?.some(r => (typeof r === 'string' ? r === 'ROLE_ADMIN' : r.authority === 'ROLE_ADMIN'));
    if (!hasAdmin) return <Navigate to="/" replace/>;
  }

  return children;
}
