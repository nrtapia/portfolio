package com.ntapia.profile.portfolio.application.finder;

import com.ntapia.profile.portfolio.domain.Portfolio;
import com.ntapia.profile.portfolio.domain.PortfolioRepository;
import com.ntapia.profile.post.domain.Post;
import com.ntapia.profile.post.domain.PostService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PortfolioFinderImpl implements PortfolioFinder {

  private final PortfolioRepository portfolioRepository;
  private final PostService postService;

  public PortfolioFinderImpl(PortfolioRepository portfolioRepository,
      PostService postService) {
    this.portfolioRepository = portfolioRepository;
    this.postService = postService;
  }

  @Override
  public Optional<PortfolioResponse> find(PortfolioFinderRequest finderRequest) {
    var optionalPortfolio = portfolioRepository.findById(finderRequest.getId());

    if (optionalPortfolio.isPresent()) {
      final var portfolio = optionalPortfolio.get();
      return postService.findByUsername(portfolio.getTwitterUsername())
          .map(postList -> mapToFinderResponse(portfolio, postList));
    } else {
      return Optional.empty();
    }
  }


  private static PortfolioResponse mapToFinderResponse(Portfolio portfolio, List<Post> postList) {

    var portfolioDto = PortfolioDto.builder()
        .id(portfolio.getId())
        .title(portfolio.getTitle())
        .description(portfolio.getDescription())
        .imageUrl(portfolio.getImageUrl())
        .build();

    var timelineItemsDto = postList.stream()
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
