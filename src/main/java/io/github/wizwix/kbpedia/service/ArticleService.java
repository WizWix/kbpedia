package io.github.wizwix.kbpedia.service;

import io.github.wizwix.kbpedia.dto.Article;
import io.github.wizwix.kbpedia.dto.Tag;
import io.github.wizwix.kbpedia.dto.User;
import io.github.wizwix.kbpedia.dto.message.ResponseArticleDetail;
import io.github.wizwix.kbpedia.dto.message.ResponseArticleList;
import io.github.wizwix.kbpedia.helper.SearchCriteria;
import io.github.wizwix.kbpedia.repo.IArticleRepository;
import io.github.wizwix.kbpedia.service.iface.IArticleService;
import io.github.wizwix.kbpedia.service.iface.ITagService;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService implements IArticleService {
  private final IArticleRepository repo;
  private final ITagService tagService;

  @Override
  @Transactional
  public Article createArticle(String title, String content, Set<String> tagNames, User author) {
    Article article = new Article();
    article.setTitle(title);
    article.setContent(content);
    article.setAuthor(author);
    Set<Tag> tags = tagNames.stream().map(tagService::getOrCreate).collect(Collectors.toSet());
    article.setTags(tags);

    return repo.save(article);
  }

  @Override
  public ResponseArticleDetail getArticle(Long id) {
    Article article = repo.findById(id).orElseThrow(() -> new RuntimeException("Article not found"));
    return new ResponseArticleDetail(
        article.getId(),
        article.getTitle(),
        article.getContent(),
        article.getAuthor().getUsername(),
        article.getCreatedAt(),
        article.getUpdatedAt()
    );
  }

  @Override
  public Page<ResponseArticleList> getArticles(String username, Pageable pageable) {
    Page<Article> page;

    if (username == null || username.isBlank()) {
      page = repo.findAll(pageable);
    } else {
      page = repo.findByAuthorUsername(username, pageable);
    }

    return page.map(this::toListDto);
  }

  @Override
  public Page<ResponseArticleList> searchArticles(String keyword, Pageable pageable) {
    if (keyword == null || keyword.isBlank()) {
      return repo.findAll(pageable).map(this::toListDto);
    }
    SearchCriteria criteria = parseSearch(keyword);
    Specification<Article> spec = buildSpecification(criteria);
    return repo.findAll(spec, pageable).map(this::toListDto);
  }

  private Specification<Article> buildSpecification(SearchCriteria criteria) {
    return (root, query, cb) -> {
      Objects.requireNonNull(query).distinct(true);
      Join<Object, Object> tagJoin = root.join("tags", JoinType.LEFT);
      List<Predicate> predicates = new ArrayList<>();
      if (!criteria.include().isEmpty()) {
        List<Predicate> includePredicates = new ArrayList<>();
        for (String token : criteria.include()) {
          String pattern = "%" + token + "%";
          includePredicates.add(cb.or(
              cb.like(cb.lower(root.get("title")), pattern),
              cb.like(cb.lower(root.get("content")), pattern),
              cb.like(cb.lower(tagJoin.get("name")), pattern)
          ));
        }
        predicates.add(cb.or(includePredicates.toArray(new Predicate[0])));
      }
      for (String term : criteria.exclude()) {
        String pattern = "%" + term + "%";
        predicates.add(cb.not(cb.or(
            cb.like(cb.lower(root.get("title")), pattern),
            cb.like(cb.lower(root.get("content")), pattern),
            cb.like(cb.lower(tagJoin.get("name")), pattern)
        )));
      }
      return cb.and(predicates.toArray(new Predicate[0]));
    };
  }

  private SearchCriteria parseSearch(String keyword) {
    Set<String> include = new HashSet<>();
    Set<String> exclude = new HashSet<>();

    for (String token : keyword.split("\\s+")) {
      if (token.startsWith("-") && token.length() > 1) {
        exclude.add(token.substring(1).toLowerCase());
      } else {
        include.add(token.toLowerCase());
      }
    }

    return new SearchCriteria(include, exclude);
  }

  private ResponseArticleList toListDto(Article article) {
    return new ResponseArticleList(
        article.getId(),
        article.getTitle(),
        article.getAuthor().getUsername(),
        article.getCreatedAt(),
        article.getUpdatedAt()
    );
  }
}
