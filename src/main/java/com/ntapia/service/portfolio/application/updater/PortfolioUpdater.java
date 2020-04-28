package com.ntapia.service.portfolio.application.updater;

import java.util.Optional;

public interface PortfolioUpdater {

  Optional<PortfolioUpdaterResponse> update(PortfolioUpdaterRequest portfolioUpdaterRequest);
}
