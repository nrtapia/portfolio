package com.ntapia.service.portfolio.application.finder;

import com.ntapia.service.portfolio.shared.PortfolioDto;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class PortfolioFinderResponse implements Serializable {

  private static final long serialVersionUID = 20200427L;

  private final PortfolioDto portfolioDto;

  private final List<TimelineItemDto> timelineItemsDto;

  public PortfolioFinderResponse(PortfolioDto portfolioDto,
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
    PortfolioFinderResponse that = (PortfolioFinderResponse) o;
    return Objects.equals(portfolioDto, that.portfolioDto) &&
        Objects.equals(timelineItemsDto, that.timelineItemsDto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(portfolioDto, timelineItemsDto);
  }
}
