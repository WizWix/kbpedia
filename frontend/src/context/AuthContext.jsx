import {createContext, useContext, useEffect, useState} from 'react';

const AuthContext = createContext(null);

export function AuthProvider({children}) {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  const checkAuth = async () => {
    try {
      const resp = await fetch('/api/auth/me');
      if (resp.ok) {
        const data = await resp.json();
        setUser(data);
      } else {
        setUser(null);
      }
    } catch (error) {
      setUser(null);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    // noinspection JSIgnoredPromiseFromCall
    checkAuth();
  }, []);

  return (
      <AuthContext.Provider value={{user, setUser, loading, setLoading}}>
        {!loading && children}
      </AuthContext.Provider>
  );
}

export const useAuth = () => useContext(AuthContext);
