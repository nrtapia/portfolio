package com.ntapia.profile.portfolio.infrastructure.persistence;

import com.ntapia.profile.portfolio.domain.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepositoryJpa extends JpaRepository<Portfolio, Long> {

}
