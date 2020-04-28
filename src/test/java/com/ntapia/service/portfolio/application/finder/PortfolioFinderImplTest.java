package com.ntapia.service.portfolio.application.finder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

import com.ntapia.service.portfolio.domain.Portfolio;
import com.ntapia.service.portfolio.domain.PortfolioRepository;
import com.ntapia.service.portfolio.shared.PortfolioDto;
import com.ntapia.service.post.domain.TwitterPost;
import com.ntapia.service.post.domain.TwitterService;
import com.ntapia.service.post.infrastructure.client.TwitterClientException;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@Slf4j
public class PortfolioFinderImplTest {

  @Mock
  private PortfolioRepository portfolioRepository;

  @Mock
  private TwitterService twitterService;

  @InjectMocks
  private PortfolioFinderImpl portfolioFinder;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void givenNoIdWhenFindPortfolioThenMustThrowArgumentInvalidException() {

    final Long id = 1L;
    final var errorMessage = "id required";

    given(portfolioRepository.findById(id))
        .willThrow(new PortfolioArgumentInvalidException(errorMessage));

    final var finderRequest = new PortfolioFinderRequest(id);
    try {
      portfolioFinder.find(finderRequest);
      fail("PortfolioArgumentInvalidException expected!");
    } catch (PortfolioArgumentInvalidException e) {
      assertEquals(errorMessage, e.getMessage());
    }

    then(portfolioRepository)
        .should(times(1))
        .findById(id);

    then(twitterService)
        .should(never())
        .findByUsername(anyString());
  }

  @Test
  public void givenNotCreatedIdWhenFindPortfolioThenMustReturnEmptyOptional() {
    final Long id = 99L;

    given(portfolioRepository.findById(id)).willReturn(Optional.empty());

    final var finderRequest = new PortfolioFinderRequest(id);

    var portfolioFinderResponse = portfolioFinder.find(finderRequest);
    assertNotNull(portfolioFinderResponse);
    assertTrue(portfolioFinderResponse.isEmpty());

    then(portfolioRepository)
        .should(times(1))
        .findById(id);

    then(twitterService)
        .should(never())
        .findByUsername(anyString());
  }

  @Test
  public void givenIdWithoutTwitterTimelineWhenFindPortfolioThenMustReturnPortfolioWithoutTimeline() {
    final Long id = 1L;
    final var twitterUsername1 = "twitterUsername1";

    var portfolio = createPortfolio(id, twitterUsername1);

    given(portfolioRepository.findById(id)).willReturn(Optional.of(portfolio));
    given(twitterService.findByUsername(twitterUsername1)).willReturn(Optional.empty());

    final var finderRequest = new PortfolioFinderRequest(id);

    Optional<PortfolioFinderResponse> responseOptional = portfolioFinder.find(finderRequest);
    assertNotNull(responseOptional);
    assertFalse(responseOptional.isEmpty());

    var portfolioFinderResponse = responseOptional.get();
    PortfolioDto portfolioDto = portfolioFinderResponse.getPortfolioDto();
    assertEquals(portfolio.getId(), portfolioDto.getId());
    assertEquals(portfolio.getTwitterUsername(), portfolioDto.getTwitterUsername());
    assertEquals(portfolio.getDescription(), portfolioDto.getDescription());
    assertEquals(portfolio.getImageUrl(), portfolioDto.getImageUrl());
    assertEquals(portfolio.getTitle(), portfolioDto.getTitle());

    assertNotNull(portfolioFinderResponse.getTimelineItems());
    assertTrue(portfolioFinderResponse.getTimelineItems().isEmpty());

    then(portfolioRepository)
        .should(times(1))
        .findById(id);

    then(twitterService)
        .should(times(1))
        .findByUsername(twitterUsername1);
  }

  @Test
  public void givenIdTwitterClientFailWhenFindPortfolioThenMustReturnPortfolioWithoutTimeline() {
    final Long id = 1L;
    final var twitterUsername1 = "twitterUsername1";

    var portfolio = createPortfolio(id, twitterUsername1);

    given(portfolioRepository.findById(id)).willReturn(Optional.of(portfolio));
    given(twitterService.findByUsername(twitterUsername1)).willThrow(new TwitterClientException());

    final var finderRequest = new PortfolioFinderRequest(id);

    Optional<PortfolioFinderResponse> responseOptional = portfolioFinder.find(finderRequest);
    assertNotNull(responseOptional);
    assertFalse(responseOptional.isEmpty());

    var portfolioFinderResponse = responseOptional.get();
    PortfolioDto portfolioDto = portfolioFinderResponse.getPortfolioDto();
    assertEquals(portfolio.getId(), portfolioDto.getId());
    assertEquals(portfolio.getTwitterUsername(), portfolioDto.getTwitterUsername());
    assertEquals(portfolio.getDescription(), portfolioDto.getDescription());
    assertEquals(portfolio.getImageUrl(), portfolioDto.getImageUrl());
    assertEquals(portfolio.getTitle(), portfolioDto.getTitle());

    assertNotNull(portfolioFinderResponse.getTimelineItems());
    assertTrue(portfolioFinderResponse.getTimelineItems().isEmpty());

    then(portfolioRepository)
        .should(times(1))
        .findById(id);

    then(twitterService)
        .should(times(1))
        .findByUsername(twitterUsername1);
  }

  private Portfolio createPortfolio(Long id, String twitterUsername1) {
    var portfolio = new Portfolio();
    portfolio.setId(id);
    portfolio.setTitle("title1");
    portfolio.setImageUrl("imageUrl1");
    portfolio.setDescription("description1");
    portfolio.setTwitterUsername(twitterUsername1);
    portfolio.setTitle("title1");
    return portfolio;
  }

  @Test
  public void givenPersistedIdWithTwitterTimelineWhenFindPortfolioThenMustReturnPortfolioWithTimeline() {
    final Long id = 1L;
    final var twitterUsername1 = "twitterUsername1";

    var portfolio = createPortfolio(id, twitterUsername1);

    given(portfolioRepository.findById(id)).willReturn(Optional.of(portfolio));

    List<TwitterPost> timeline = List.of(
        createTwitterPost(11L),
        createTwitterPost(22L)
    );
    given(twitterService.findByUsername(twitterUsername1)).willReturn(Optional.of(timeline));

    final var finderRequest = new PortfolioFinderRequest(id);

    Optional<PortfolioFinderResponse> responseOptional = portfolioFinder.find(finderRequest);
    assertNotNull(responseOptional);
    assertFalse(responseOptional.isEmpty());

    var portfolioFinderResponse = responseOptional.get();
    PortfolioDto portfolioDto = portfolioFinderResponse.getPortfolioDto();
    assertEquals(portfolio.getId(), portfolioDto.getId());
    assertEquals(portfolio.getTwitterUsername(), portfolioDto.getTwitterUsername());
    assertEquals(portfolio.getDescription(), portfolioDto.getDescription());
    assertEquals(portfolio.getImageUrl(), portfolioDto.getImageUrl());
    assertEquals(portfolio.getTitle(), portfolioDto.getTitle());

    assertNotNull(portfolioFinderResponse.getTimelineItems());
    assertFalse(portfolioFinderResponse.getTimelineItems().isEmpty());
    assertEquals(2, portfolioFinderResponse.getTimelineItems().size());

    then(portfolioRepository)
        .should(times(1))
        .findById(id);

    then(twitterService)
        .should(times(1))
        .findByUsername(twitterUsername1);
  }

  private TwitterPost createTwitterPost(Long id) {
    return TwitterPost.builder()
        .id(id)
        .photo("photo" + id)
        .text("text" + id)
        .userName("username" + id)
        .build();
  }

}