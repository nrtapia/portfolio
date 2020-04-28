package com.ntapia.service.portfolio.infrastructure.rest;

import static com.ntapia.service.portfolio.shared.Util.PORTFOLIO_ENDPOINT;

import com.ntapia.service.portfolio.application.finder.PortfolioFinder;
import com.ntapia.service.portfolio.application.finder.PortfolioFinderRequest;
import com.ntapia.service.portfolio.application.finder.PortfolioFinderResponse;
import com.ntapia.service.portfolio.application.finder.TimelineItemDto;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PortfolioGetController {

  private static final String GET_PORTFOLIO_LOG = "Get portfolio id: {}";

  private final PortfolioFinder portfolioFinder;

  public PortfolioGetController(
      PortfolioFinder portfolioFinder) {
    this.portfolioFinder = portfolioFinder;
  }

  @GetMapping(PORTFOLIO_ENDPOINT)
  public Response get(@PathVariable Long id) {
    log.info(GET_PORTFOLIO_LOG, id);

    return portfolioFinder.find(new PortfolioFinderRequest(id))
        .map(this::mapToResponse)
        .orElseThrow(PortfolioNotFoundException::new);
  }

  private Response mapToResponse(PortfolioFinderResponse portfolioFinderResponse) {
    var portfolioDto = portfolioFinderResponse.getPortfolioDto();

    var timeline = portfolioFinderResponse.getTimelineItems().stream()
        .map(this::mapToItem)
        .collect(Collectors.toList());

    return Response.builder()
        .id(portfolioDto.getId())
        .photo(portfolioDto.getImageUrl())
        .title(portfolioDto.getTitle())
        .description(portfolioDto.getDescription())
        .timeline(timeline)
        .twitterUsername(portfolioDto.getTwitterUsername())
        .build();
  }

  private Item mapToItem(TimelineItemDto itemDto) {
    return Item.builder()
        .id(itemDto.getId())
        .description(itemDto.getText())
        .name(itemDto.getName())
        .photo(itemDto.getImageUrl())
        .build();
  }

}
