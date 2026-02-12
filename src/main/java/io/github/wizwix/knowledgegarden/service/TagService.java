package io.github.wizwix.knowledgegarden.service;

import io.github.wizwix.knowledgegarden.model.Tag;
import io.github.wizwix.knowledgegarden.repo.ITagRepository;
import io.github.wizwix.knowledgegarden.service.iface.ITagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TagService implements ITagService {
  private final ITagRepository repo;

  @Override
  public Tag getOrCreate(String name) {
    String normalized = normalize(name);
    return repo.findByName(normalized).orElseGet(() -> repo.save(new Tag(null, normalized)));
  }

  private String normalize(String name) {
    return name.trim().toLowerCase();
  }
}
