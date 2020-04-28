package com.ntapia.profile.portfolio.application.finder;

import java.io.Serializable;
import java.util.Objects;
import lombok.Builder;

@Builder
public class PortfolioDto implements Serializable {

  private static final long serialVersionUID = 20200427L;

  private final Long id;
  private final String title;
  private final String description;
  private final String imageUrl;

  public PortfolioDto(Long id, String title, String description, String imageUrl) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.imageUrl = imageUrl;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
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
    PortfolioDto that = (PortfolioDto) o;
    return Objects.equals(id, that.id) &&
        title.equals(that.title) &&
        description.equals(that.description) &&
        imageUrl.equals(that.imageUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, imageUrl);
  }
}
