package com.ntapia.profile.portfolio.infrastructure.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.ntapia.profile.portfolio.domain.Portfolio;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class PortfolioRepositoryJpaIntegrationTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private PortfolioRepositoryJpa portfolioRepositoryJpa;

  @Test
  public void givenTwoPortfolioWhenFindOnePortfolioThenMustReturnTheData() {

    final var portfolio1 = createPortfolio("1");
    final var portfolio2 = createPortfolio("2");

    final var persisted1 = entityManager.persist(portfolio1);
    final var persisted2 = entityManager.persist(portfolio2);

    assertNotNull(persisted1);
    assertNotNull(persisted1.getId());
    assertNotNull(persisted2);
    assertNotNull(persisted2.getId());

    var portfolioOptional = portfolioRepositoryJpa.findById(persisted2.getId());
    assertTrue(portfolioOptional.isPresent());

    var portfolio = portfolioOptional.get();

    assertNotNull(portfolio);
    assertEquals(Long.valueOf(2), portfolio.getId());
    assertEquals(portfolio2.getTitle(), portfolio.getTitle());
    assertEquals(portfolio2.getDescription(), portfolio.getDescription());
    assertEquals(portfolio2.getImageUrl(), portfolio.getImageUrl());
    assertEquals(portfolio2.getTwitterUsername(), portfolio.getTwitterUsername());
  }

  private Portfolio createPortfolio(String value) {
    var portfolio = new Portfolio();
    portfolio.setTitle("title" + value);
    portfolio.setDescription("description" + value);
    portfolio.setImageUrl("url" + value);
    portfolio.setTwitterUsername("userName" + value);
    return portfolio;
  }

  @Test
  public void givenOnePortfolioWhenFindAnotherPortfolioThenMustReturnTheEmpty() {

    final var portfolio1 = createPortfolio("1");

    final var persisted1 = entityManager.persist(portfolio1);

    assertNotNull(persisted1);
    assertNotNull(persisted1.getId());

    var portfolioOptional = portfolioRepositoryJpa.findById(99L);
    assertFalse(portfolioOptional.isPresent());
  }

}