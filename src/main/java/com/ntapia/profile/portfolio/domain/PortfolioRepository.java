package com.ntapia.profile.portfolio.domain;

import java.util.Optional;

public interface PortfolioRepository {

  Optional<Portfolio> findById(Long id);
}
