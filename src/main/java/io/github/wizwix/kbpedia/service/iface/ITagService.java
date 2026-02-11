package io.github.wizwix.kbpedia.service.iface;

import io.github.wizwix.kbpedia.dto.Tag;

public interface ITagService {
  Tag getOrCreate(String name);
}
