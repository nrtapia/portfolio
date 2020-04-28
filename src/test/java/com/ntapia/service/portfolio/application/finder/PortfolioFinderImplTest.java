package com.ntapia.service.portfolio.application.finder;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.ntapia.service.portfolio.domain.PortfolioRepository;
import com.ntapia.service.post.domain.TwitterService;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class PortfolioFinderImplTest extends TestCase {

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
/*
    given(portfolioRepository.findById(anyLong()))
        .willThrow(new PortfolioArgumentInvalidException(errorMessage));
*/
    when(portfolioRepository.findById(id))
        .thenThrow(new PortfolioArgumentInvalidException(errorMessage));

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

}