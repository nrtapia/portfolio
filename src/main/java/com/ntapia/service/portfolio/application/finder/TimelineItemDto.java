package com.ntapia.service.portfolio.application.finder;

import java.io.Serializable;
import java.util.Objects;
import lombok.Builder;

@Builder
public class TimelineItemDto implements Serializable {

  private static final long serialVersionUID = 20200427L;

  private final Long id;
  private final String name;
  private final String text;
  private final String imageUrl;

  public TimelineItemDto(Long id, String name, String text, String imageUrl) {
    this.id = id;
    this.name = name;
    this.text = text;
    this.imageUrl = imageUrl;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getText() {
    return text;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TimelineItemDto that = (TimelineItemDto) o;
    return Objects.equals(id, that.id) &&
        name.equals(that.name) &&
        text.equals(that.text) &&
        imageUrl.equals(that.imageUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, text, imageUrl);
  }
}
