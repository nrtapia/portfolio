package com.ntapia.service.portfolio.infrastructure.persistence;

import com.ntapia.service.portfolio.domain.Portfolio;
import com.ntapia.service.portfolio.domain.PortfolioRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PortfolioRepositoryImpl implements PortfolioRepository {

  private static final String ERROR_TO_QUERY_PORTFOLIO_LOG = "Error to query portfolio by id: {}";
  private static final String ERROR_TO_UPDATE_PORTFOLIO_LOG = "Error to update portfolio by id: {}";

  private final PortfolioRepositoryJpa portfolioRepositoryJpa;

  public PortfolioRepositoryImpl(
      PortfolioRepositoryJpa portfolioRepositoryJpa) {
    this.portfolioRepositoryJpa = portfolioRepositoryJpa;
  }

  @Override
  public Optional<Portfolio> findById(Long id) {
    try {
      return portfolioRepositoryJpa.findById(id);
    } catch (Exception e) {
      log.error(ERROR_TO_QUERY_PORTFOLIO_LOG, id, e);
      throw new PortfolioDatabaseException();
    }
  }

  @Override
  public Portfolio update(Portfolio portfolio) {

    Optional<Portfolio> optionalPortfolio = findById(portfolio.getId());

    if (optionalPortfolio.isPresent()) {
      try {
        var persistedPortfolio = optionalPortfolio.get();

        persistedPortfolio.setTitle(portfolio.getTitle());
        persistedPortfolio.setImageUrl(portfolio.getImageUrl());
        persistedPortfolio.setDescription(portfolio.getDescription());
        persistedPortfolio.setTwitterUsername(portfolio.getTwitterUsername());

        return portfolioRepositoryJpa.save(persistedPortfolio);

      } catch (Exception e) {
        log.error(ERROR_TO_UPDATE_PORTFOLIO_LOG, portfolio.getId(), e);
        throw new PortfolioDatabaseException();
      }
    }

    return null;
  }
}
