package com.ntapia.service.portfolio.infrastructure.rest;

import static com.ntapia.service.portfolio.shared.Util.PORTFOLIO_ENDPOINT;

import com.ntapia.service.portfolio.application.updater.PortfolioUpdater;
import com.ntapia.service.portfolio.application.updater.PortfolioUpdaterRequest;
import com.ntapia.service.portfolio.application.updater.PortfolioUpdaterResponse;
import com.ntapia.service.portfolio.shared.PortfolioDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PortfolioPutController {

  private static final String PUT_PORTFOLIO_LOG = "Put portfolio id: {} data: {}";

  private final PortfolioUpdater portfolioUpdater;

  public PortfolioPutController(PortfolioUpdater portfolioUpdater) {
    this.portfolioUpdater = portfolioUpdater;
  }

  @PutMapping(PORTFOLIO_ENDPOINT)
  public Response put(@PathVariable Long id, @RequestBody PutRequest putRequest) {
    log.info(PUT_PORTFOLIO_LOG, id, putRequest);

    return portfolioUpdater
        .update(mapToUpdateRequest(id, putRequest))
        .map(this::mapToResponse)
        .orElseThrow(PortfolioNotFoundException::new);

  }

  private Response mapToResponse(PortfolioUpdaterResponse portfolioUpdaterResponse) {
    var portfolioDto = portfolioUpdaterResponse.getPortfolioDto();
    return Response.builder()
        .id(portfolioDto.getId())
        .description(portfolioDto.getDescription())
        .photo(portfolioDto.getImageUrl())
        .title(portfolioDto.getTitle())
        .twitterUsername(portfolioDto.getTwitterUsername())
        .build();
  }

  private PortfolioUpdaterRequest mapToUpdateRequest(Long id, PutRequest putRequest) {
    return new PortfolioUpdaterRequest(
        PortfolioDto.builder()
            .id(id)
            .description(putRequest.getDescription())
            .imageUrl(putRequest.getPhoto())
            .title(putRequest.getTitle())
            .twitterUsername(putRequest.getTwitterUsername())
            .build());
  }
}
