package com.ntapia.service.portfolio.application.finder;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PortfolioArgumentInvalidException extends RuntimeException {

  public PortfolioArgumentInvalidException(String message) {
    super(message);
  }
}
