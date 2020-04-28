package com.ntapia.service.portfolio.domain;

import java.util.Optional;

public interface PortfolioRepository {

  Optional<Portfolio> findById(Long id);

  Portfolio update(Portfolio portfolio);
}
