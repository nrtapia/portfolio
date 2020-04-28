package com.ntapia.service.portfolio.application.updater;

import com.ntapia.service.portfolio.shared.PortfolioDto;
import java.io.Serializable;
import java.util.Objects;

public class PortfolioUpdaterResponse implements Serializable {

  private static final long serialVersionUID = 20200428L;

  private final PortfolioDto portfolioDto;

  public PortfolioUpdaterResponse(PortfolioDto portfolioDto) {
    this.portfolioDto = portfolioDto;
  }

  public PortfolioDto getPortfolioDto() {
    return portfolioDto;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PortfolioUpdaterResponse that = (PortfolioUpdaterResponse) o;
    return portfolioDto.equals(that.portfolioDto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(portfolioDto);
  }

  @Override
  public String toString() {
    return "PortfolioUpdaterResponse{" +
        "portfolioDto=" + portfolioDto +
        '}';
  }

}
