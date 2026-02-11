import {StrictMode} from 'react';
import {createRoot} from 'react-dom/client';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import './styles/normalize.css';
import './styles/index.css';
import {Header} from './components/shared/Header.jsx';
import {ProtectedRoute} from './components/shared/ProtectedRoute.jsx';
import {AuthProvider} from './context/AuthContext.jsx';
import {AdminDashboardPage} from './page/admin/AdminDashboardPage.jsx';
import {EditorPage} from './page/article/EditorPage.jsx';
import {HomePage} from './page/HomePage.jsx';
import {LoginPage} from './page/LoginPage.jsx';
import {RegisterPage} from './page/RegisterPage.jsx';
import {SearchPage} from './page/SearchPage.jsx';
import {MyPageLayoutPage} from './page/user/my/MyPageLayoutPage.jsx';
import {UserProfilePage} from './page/user/UserProfilePage.jsx';
import {ArticleViewPage} from './page/view/ArticleViewPage.jsx';
import {CategoryListPage} from './page/view/CategoryListPage.jsx';
import {DiscussionViewPage} from './page/view/DiscussionViewPage.jsx';

createRoot(document.getElementById('root')).render(
    <StrictMode>
      <AuthProvider>
        <BrowserRouter>
          <Header/>
          <main>
            <Routes>
              {/* Public */}
              <Route path="/" element={<HomePage/>}/>
              <Route path="/login" element={<LoginPage/>}/>
              <Route path="/register" element={<RegisterPage/>}/>
              <Route path="/search" element={<SearchPage/>}/>
              <Route path="/user/:userId" element={<UserProfilePage/>}/>
              {/* Dynamic Category Routes */}
              <Route path="/:category" element={<CategoryListPage/>}/>
              <Route path="/:category/:id" element={<ArticleViewPage/>}/>
              <Route path="/:category/:id/discussion" element={<DiscussionViewPage/>}/>
              {/* Protected (User) */}
              <Route path="/:category/:id/edit" element={<ProtectedRoute><EditorPage/></ProtectedRoute>}/>
              <Route path="/my/*" element={<ProtectedRoute><MyPageLayoutPage/></ProtectedRoute>}/>
              <Route path="/new" element={<ProtectedRoute><EditorPage/></ProtectedRoute>}/>
              {/* Protected (Admin) */}
              <Route path="/manage" element={<ProtectedRoute><AdminDashboardPage/></ProtectedRoute>}/>
            </Routes>
          </main>
        </BrowserRouter>
      </AuthProvider>
    </StrictMode>,
);
