package com.ntapia.service.portfolio.application.updater;

import com.ntapia.service.portfolio.application.finder.PortfolioArgumentInvalidException;
import com.ntapia.service.portfolio.domain.Portfolio;
import com.ntapia.service.portfolio.domain.PortfolioRepository;
import com.ntapia.service.portfolio.shared.PortfolioDto;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class PortfolioUpdaterImpl implements PortfolioUpdater {

  private static final String UPDATE_PORTFOLIO_LOG = "Update portfolio: {}";
  private final PortfolioRepository portfolioRepository;

  public PortfolioUpdaterImpl(
      PortfolioRepository portfolioRepository) {
    this.portfolioRepository = portfolioRepository;
  }

  @Transactional
  @Override
  public Optional<PortfolioUpdaterResponse> update(
      PortfolioUpdaterRequest portfolioUpdaterRequest) {
    log.debug(UPDATE_PORTFOLIO_LOG, portfolioUpdaterRequest);

    validate(portfolioUpdaterRequest);

    var updatedPortfolio = portfolioRepository.update(
        mapToPortfolio(portfolioUpdaterRequest));

    return mapToPortfolioDto(updatedPortfolio);
  }

  private void validate(PortfolioUpdaterRequest portfolioUpdaterRequest) {

    if (Objects.isNull(portfolioUpdaterRequest) || Objects
        .isNull(portfolioUpdaterRequest.getPortfolioDto())) {
      throw new PortfolioArgumentInvalidException("Data to update is required");
    }

    var portfolioDto = portfolioUpdaterRequest.getPortfolioDto();
    if (Objects.isNull(portfolioDto.getId())) {
      throw new PortfolioArgumentInvalidException("Id is required");
    }

    if (isEmpty(portfolioDto.getTwitterUsername())) {
      throw new PortfolioArgumentInvalidException("Twitter user name is required");
    }

    if (isInvalidLength(portfolioDto.getTwitterUsername(), 255)) {
      throw new PortfolioArgumentInvalidException("Twitter user name is invalid");
    }

    if (isEmpty(portfolioDto.getTitle())) {
      throw new PortfolioArgumentInvalidException("Title is required");
    } else {
      if (isInvalidLength(portfolioDto.getTwitterUsername(), 255)) {
        throw new PortfolioArgumentInvalidException("Twitter user name is invalid");
      }
    }

    if (Objects.nonNull(portfolioDto.getDescription()) && isInvalidLength(
        portfolioDto.getDescription(), 255)) {
      throw new PortfolioArgumentInvalidException("Description is invalid");
    }

    if (Objects.nonNull(portfolioDto.getImageUrl()) && isInvalidLength(portfolioDto.getImageUrl(),
        255)) {
      throw new PortfolioArgumentInvalidException("Photo URL is invalid");
    }

  }

  private boolean isEmpty(String value) {
    return Objects.isNull(value) || value.trim().length() == 0;
  }

  private boolean isInvalidLength(String value, int maxLength) {
    return value.length() > maxLength;
  }


  private Optional<PortfolioUpdaterResponse> mapToPortfolioDto(Portfolio updatedPortfolio) {
    PortfolioDto portfolioDto = PortfolioDto.builder()
        .id(updatedPortfolio.getId())
        .twitterUsername(updatedPortfolio.getTwitterUsername())
        .title(updatedPortfolio.getTitle())
        .description(updatedPortfolio.getDescription())
        .imageUrl(updatedPortfolio.getImageUrl())
        .build();

    return Optional.of(new PortfolioUpdaterResponse(portfolioDto));
  }

  private Portfolio mapToPortfolio(PortfolioUpdaterRequest portfolioUpdaterRequest) {
    Portfolio portfolio = new Portfolio();
    PortfolioDto portfolioDto = portfolioUpdaterRequest.getPortfolioDto();
    portfolio.setId(portfolioDto.getId());
    portfolio.setImageUrl(portfolioDto.getImageUrl());
    portfolio.setTitle(portfolioDto.getTitle());
    portfolio.setDescription(portfolioDto.getDescription());
    portfolio.setTwitterUsername(portfolioDto.getTwitterUsername());
    return portfolio;
  }
}
