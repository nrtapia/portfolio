package com.ntapia.profile.portfolio.application.finder;

import com.ntapia.profile.portfolio.domain.Portfolio;
import com.ntapia.profile.portfolio.domain.PortfolioRepository;
import com.ntapia.profile.post.domain.TwitterPost;
import com.ntapia.profile.post.domain.TwitterService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class PortfolioFinderImpl implements PortfolioFinder {

  private static final String FIND_PORTFOLIO_LOG = "Find portfolio: {}";

  private final PortfolioRepository portfolioRepository;
  private final TwitterService twitterService;

  public PortfolioFinderImpl(PortfolioRepository portfolioRepository,
      TwitterService twitterService) {
    this.portfolioRepository = portfolioRepository;
    this.twitterService = twitterService;
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<PortfolioResponse> find(PortfolioFinderRequest finderRequest) {
    log.debug(FIND_PORTFOLIO_LOG, finderRequest);
    var optionalPortfolio = portfolioRepository.findById(finderRequest.getId());

    if (optionalPortfolio.isPresent()) {
      final var portfolio = optionalPortfolio.get();
      return twitterService.findByUsername(portfolio.getTwitterUsername())
          .map(postList -> mapToFinderResponse(portfolio, postList));
    } else {
      return Optional.empty();
    }
  }


  private static PortfolioResponse mapToFinderResponse(Portfolio portfolio, List<TwitterPost> twitterPostList) {

    var portfolioDto = PortfolioDto.builder()
        .id(portfolio.getId())
        .title(portfolio.getTitle())
        .description(portfolio.getDescription())
        .imageUrl(portfolio.getImageUrl())
        .twitterUsername(portfolio.getTwitterUsername())
        .build();

    var timelineItemsDto = twitterPostList.stream()
        .map(post -> TimelineItemDto.builder()
            .id(post.getId())
            .imageUrl(post.getPhoto())
            .name(post.getUserName())
            .text(post.getText())
            .build()
        ).collect(Collectors.toList());

    return new PortfolioResponse(portfolioDto, timelineItemsDto);
  }
}
