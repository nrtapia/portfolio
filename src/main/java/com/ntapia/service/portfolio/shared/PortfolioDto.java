package com.ntapia.service.portfolio.shared;

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
  private final String twitterUsername;

  public PortfolioDto(Long id, String title, String description, String imageUrl,
      String twitterUsername) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.imageUrl = imageUrl;
    this.twitterUsername = twitterUsername;
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

  public String getTwitterUsername() {
    return twitterUsername;
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
        imageUrl.equals(that.imageUrl) &&
        twitterUsername.equals(that.twitterUsername);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, imageUrl, twitterUsername);
  }

  @Override
  public String toString() {
    return "PortfolioDto{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", imageUrl='" + imageUrl + '\'' +
        ", twitterUsername='" + twitterUsername + '\'' +
        '}';
  }
}
