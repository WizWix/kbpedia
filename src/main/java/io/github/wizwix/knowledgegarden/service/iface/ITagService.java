package io.github.wizwix.knowledgegarden.service.iface;

import io.github.wizwix.knowledgegarden.dto.Tag;

public interface ITagService {
  Tag getOrCreate(String name);
}
