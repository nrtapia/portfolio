package com.ntapia.profile.portfolio.infrastructure.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Portfolio not found")
class PortfolioNotFoundException extends RuntimeException {

  public PortfolioNotFoundException() {
  }
}
