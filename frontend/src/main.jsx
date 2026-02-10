import {StrictMode} from 'react';
import {createRoot} from 'react-dom/client';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import './styles/normalize.css';
import './styles/index.css';
import {Header} from './components/shared/Header.jsx';
import {LoginPage} from './page/LoginPage.jsx';
import {HomePage} from './page/HomePage.jsx';
import {SearchPage} from './page/SearchPage.jsx';
import {RegisterPage} from './page/RegisterPage.jsx';
import {MyPageLayoutPage} from './page/MyPageLayoutPage.jsx';
import {UserProfilePage} from './page/UserProfilePage.jsx';
import {DiscussionViewPage} from './page/DiscussionViewPage.jsx';
import {ArticleViewPage} from './page/ArticleViewPage.jsx';
import {EditorPage} from './page/EditorPage.jsx';
import {CategoryListPage} from './page/CategoryListPage.jsx';
import {AdminDashboardPage} from './page/AdminDashboardPage.jsx';

createRoot(document.getElementById('root')).render(
    <StrictMode>
      <BrowserRouter>
        <Header/>
        <main>
          <Routes>
            {/* Public */}
            <Route path="/" element={<HomePage/>}/>
            <Route path="/search" element={<SearchPage/>}/>
            <Route path="/login" element={<LoginPage/>}/>
            <Route path="/register" element={<RegisterPage/>}/>
            <Route path="/user/:userId" element={<UserProfilePage/>}/>

            {/* Dynamic Category Routes */}
            <Route path="/:category" element={<CategoryListPage/>}/>
            <Route path="/:category/:id" element={<ArticleViewPage/>}/>
            <Route path="/:category/:id/discussion" element={<DiscussionViewPage/>}/>

            {/* Protected (User) */}
            <Route path="/my/*" element={<MyPageLayoutPage/>}/>
            <Route path="/new" element={<EditorPage/>}/>
            <Route path="/:category/:id/edit" element={<EditorPage/>}/>

            {/* Protected (Admin) */}
            <Route path="/manage" element={<AdminDashboardPage/>}/>
          </Routes>
        </main>
      </BrowserRouter>
    </StrictMode>,
);
