package com.ntapia.profile.portfolio.application.finder;

import java.io.Serializable;
import java.util.Objects;

public class PortfolioFinderRequest implements Serializable {

  private static final long serialVersionUID = 20200427L;

  private final Long id;

  public PortfolioFinderRequest(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PortfolioFinderRequest that = (PortfolioFinderRequest) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "PortfolioFinderRequest{" + "id=" + id + '}';
  }
}
