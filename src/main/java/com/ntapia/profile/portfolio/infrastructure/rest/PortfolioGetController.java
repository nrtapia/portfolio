package com.ntapia.profile.portfolio.infrastructure.rest;

import com.ntapia.profile.portfolio.application.finder.PortfolioFinder;
import com.ntapia.profile.portfolio.application.finder.PortfolioFinderRequest;
import com.ntapia.profile.portfolio.application.finder.PortfolioResponse;
import com.ntapia.profile.portfolio.application.finder.TimelineItemDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PortfolioGetController {

  static final String ENDPOINT = "/portfolio/{id}";
  private static final String GET_PORTFOLIO_LOG = "Get portfolio id: {}";

  private final PortfolioFinder portfolioFinder;

  public PortfolioGetController(
      PortfolioFinder portfolioFinder) {
    this.portfolioFinder = portfolioFinder;
  }

  @GetMapping(ENDPOINT)
  public Response get(@PathVariable Long id) {
    log.info(GET_PORTFOLIO_LOG, id);

    return portfolioFinder.find(new PortfolioFinderRequest(id))
        .map(this::mapToResponse)
        .orElseThrow(PortfolioNotFoundException::new);
  }

  private Response mapToResponse(PortfolioResponse portfolioResponse) {
    var portfolioDto = portfolioResponse.getPortfolioDto();

    var timeline = portfolioResponse.getTimelineItems().stream()
        .map(this::mapToItem)
        .collect(Collectors.toList());

    return Response.builder()
        .id(portfolioDto.getId())
        .photo(portfolioDto.getImageUrl())
        .title(portfolioDto.getTitle())
        .description(portfolioDto.getDescription())
        .timeline(timeline)
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

  @Value
  @Builder
  private static class Response {

    private Long id;
    private String photo;
    private String title;
    private String description;
    private List<Item> timeline;
  }

  @Value
  @Builder
  private static class Item {

    private String id;
    private String photo;
    private String name;
    private String description;
  }
}
