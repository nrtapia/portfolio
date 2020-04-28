package com.ntapia.profile.portfolio.application.finder;

import java.util.Optional;

public interface PortfolioFinder {

  Optional<PortfolioResponse> find(PortfolioFinderRequest finderRequest);
}
