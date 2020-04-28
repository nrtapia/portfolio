package com.ntapia.service.portfolio.infrastructure.persistence;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Error to query portfolio data")
public class PortfolioDatabaseException extends RuntimeException {

  public PortfolioDatabaseException() {
    super();
  }
}
