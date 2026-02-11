package io.github.wizwix.kbpedia.service;

import io.github.wizwix.kbpedia.dto.Tag;
import io.github.wizwix.kbpedia.repo.ITagRepository;
import io.github.wizwix.kbpedia.service.iface.ITagService;
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
