package com.ntapia.profile.portfolio.infrastructure.rest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ntapia.profile.portfolio.application.finder.PortfolioDto;
import com.ntapia.profile.portfolio.application.finder.PortfolioFinder;
import com.ntapia.profile.portfolio.application.finder.PortfolioFinderRequest;
import com.ntapia.profile.portfolio.application.finder.PortfolioResponse;
import com.ntapia.profile.portfolio.application.finder.TimelineItemDto;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(PortfolioGetController.class)
public class PortfolioGetControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private PortfolioFinder portfolioFinder;

  @Test
  public void givenOneIdWhenGetPortfolioThenReturnPortfolioData() throws Exception {
    final var id = 1L;
    final var finderRequest = new PortfolioFinderRequest(id);

    final var portfolioDto = PortfolioDto.builder()
        .id(id)
        .description("the description")
        .title("Title")
        .imageUrl("http://images/user1.png")
        .build();

    final var timelineItemDto = TimelineItemDto.builder()
        .id("11")
        .name("Iron Man")
        .text("Man with armor")
        .imageUrl("http://images/user10.png")
        .build();

    final var timelineItemsDto = List.of(timelineItemDto);

    final var response = new PortfolioResponse(portfolioDto, timelineItemsDto);

    given(this.portfolioFinder.find(finderRequest)).willReturn(Optional.of(response));

    mvc.perform(
        get(PortfolioGetController.ENDPOINT, id))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.id").value(portfolioDto.getId()))
        .andExpect(jsonPath("$.photo").value(portfolioDto.getImageUrl()))
        .andExpect(jsonPath("$.title").value(portfolioDto.getTitle()))
        .andExpect(jsonPath("$.description").value(portfolioDto.getDescription()))
        .andExpect(jsonPath("$.timeline", hasSize(1)))
        .andExpect(jsonPath("$.timeline[0].id").value(timelineItemDto.getId()))
        .andExpect(jsonPath("$.timeline[0].photo").value(timelineItemDto.getImageUrl()))
        .andExpect(jsonPath("$.timeline[0].name").value(timelineItemDto.getName()))
        .andExpect(jsonPath("$.timeline[0].description").value(timelineItemDto.getText()))
    ;

    then(portfolioFinder)
        .should(times(1))
        .find(finderRequest);
  }

  @Test
  public void givenOneInvalidIdWhenGetPortfolioThenReturnNotFound() throws Exception {
    final var id = 1L;
    final var finderRequest = new PortfolioFinderRequest(id);

    given(this.portfolioFinder.find(finderRequest)).willReturn(Optional.empty());

    mvc.perform(
        get(PortfolioGetController.ENDPOINT, id))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(status().reason(containsString("Portfolio not found")));

    then(portfolioFinder)
        .should(times(1))
        .find(finderRequest);
  }
}