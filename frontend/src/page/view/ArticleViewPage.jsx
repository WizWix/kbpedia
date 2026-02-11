import {Link, useParams} from 'react-router-dom';
import {useEffect, useState} from 'react';
import {Translate} from '../../util/translator.js';

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

  const segments = category.split('-');
  const breadcrumbItems = segments.map((seg, idx) => {
    const urlPath = segments.slice(0, idx + 1).join('-');
    const displayName = Translate(seg) || seg;

    return (
        <span key={urlPath}>
          <Link to={`/${urlPath}`}>{displayName}</Link>
          {idx < segments.length - 1 && ' > '}
        </span>
    );
  });

  return (
      <article className="kb-content">
        <nav className="breadcrumb">
          {breadcrumbItems} &gt; {id}
        </nav>
        <h1>{article.title}</h1>
        <div className="metadata">
          By: {article.author} | Last updated: {article.updatedAt}
        </div>
        <hr/>
        <div className="content-body">
          {article.content}
        </div>
        <Link to={`/${category}/${id}/edit`}>이 페이지 수정하기</Link>
      </article>
  );
}
