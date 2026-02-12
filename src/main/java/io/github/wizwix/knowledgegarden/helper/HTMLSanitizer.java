package io.github.wizwix.knowledgegarden.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;
import org.jsoup.safety.Safelist;

public class HTMLSanitizer {
  /// Relaxed Safelist with Ruby support
  private static final Safelist safelist = Safelist.relaxed().addTags("ruby", "rb", "rp", "rt", "rtc");
  private static final Document.OutputSettings settings = new Document.OutputSettings().prettyPrint(false).escapeMode(Entities.EscapeMode.xhtml);

  public static String sanitize(String input) {
    if (input == null) return null;
    return Jsoup.clean(input, "", safelist, settings);
  }
}
