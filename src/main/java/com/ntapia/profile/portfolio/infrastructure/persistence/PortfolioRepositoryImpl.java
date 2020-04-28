package com.ntapia.profile.portfolio.infrastructure.persistence;

import com.ntapia.profile.portfolio.domain.Portfolio;
import com.ntapia.profile.portfolio.domain.PortfolioRepository;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class PortfolioRepositoryImpl implements PortfolioRepository {

  private final PortfolioRepositoryJpa portfolioRepositoryJpa;

  public PortfolioRepositoryImpl(
      PortfolioRepositoryJpa portfolioRepositoryJpa) {
    this.portfolioRepositoryJpa = portfolioRepositoryJpa;
  }

  @Override
  public Optional<Portfolio> findById(Long id) {
    return portfolioRepositoryJpa.findById(id);
  }
}
