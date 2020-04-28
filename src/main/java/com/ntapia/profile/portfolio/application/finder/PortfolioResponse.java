package com.ntapia.profile.portfolio.application.finder;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class PortfolioResponse implements Serializable {

  private static final long serialVersionUID = 20200427L;

  private final PortfolioDto portfolioDto;

  private final List<TimelineItemDto> timelineItemsDto;

  public PortfolioResponse(PortfolioDto portfolioDto,
      List<TimelineItemDto> timelineItemsDto) {
    this.portfolioDto = portfolioDto;
    this.timelineItemsDto = timelineItemsDto;
  }

  public PortfolioDto getPortfolioDto() {
    return portfolioDto;
  }

  public List<TimelineItemDto> getTimelineItems() {
    return timelineItemsDto;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PortfolioResponse that = (PortfolioResponse) o;
    return Objects.equals(portfolioDto, that.portfolioDto) &&
        Objects.equals(timelineItemsDto, that.timelineItemsDto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(portfolioDto, timelineItemsDto);
  }
}
