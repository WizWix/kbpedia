// TODO: Replace this with actual REST API result
import {useEffect, useState} from 'react';

const _articles = [
  {id: 1, title: '다운로드 폴더를 삭제했을 때 복구하는 방법', category: 'it', createdAt: '2026-02-01', views: 42},
  {id: 2, title: '옷에 묻은 기름 자국을 지우는 방법', category: 'life', createdAt: '2026-02-01', views: 42},
  {id: 3, title: '다운로드 폴더를 삭제했을 때 복구하는 방법', category: 'it-internet', createdAt: '2026-02-01', views: 42},
]

export function MyPageArticlesPage() {
  const [articles, setArticles] = useState([]);
  const [page, setPage] = useState(0);

  useEffect(() => {
    // TODO: GET /api/
  }, []);
}
