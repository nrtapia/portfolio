package com.ntapia.profile.portfolio.infrastructure.persistence;

import com.ntapia.profile.portfolio.domain.Portfolio;
import com.ntapia.profile.portfolio.domain.PortfolioRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PortfolioRepositoryImpl implements PortfolioRepository {

  private static final String ERROR_TO_QUERY_PORTFOLIO_LOG = "Error to query portfolio by id: {}";

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
}
