import {Link, useParams} from 'react-router-dom';
import {useEffect, useState} from 'react';

export function ArticleViewPage() {
  const {category, id} = useParams();
  const [article, setArticle] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch(`/api/articles/${id}`).then(res => {
      if (!res.ok) throw new Error('Article not found');
      return res.json();
    }).then(data => setArticle(data)).catch(err => setError(err.message));
  }, [id]);

  if (error) return <div>오류: {error}</div>;
  if (!article) return <div>로딩 중...</div>;

  return (
      <article className="kb-content">
        <nav className="breadcrumb">
          <Link to={`/${category}`}>{category}</Link> &gt; {id}
        </nav>
        <h1>{article.title}</h1>
        <div className="metadata">
          By: {article.author} | Last updated: {article.updatedAt}
        </div>
        <hr/>
        <div className="content-body">
          {article.content}
        </div>
        <Link to={`/${category}/${id}/edit`}>Edit this page</Link>
      </article>
  );
}
