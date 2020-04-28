package com.ntapia.service.portfolio.infrastructure.persistence;

import com.ntapia.service.portfolio.domain.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepositoryJpa extends JpaRepository<Portfolio, Long> {

}
