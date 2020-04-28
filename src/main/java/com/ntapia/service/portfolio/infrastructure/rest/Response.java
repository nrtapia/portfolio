package com.ntapia.service.portfolio.infrastructure.rest;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
class Response {

  private Long id;
  private String photo;
  private String title;
  private String description;
  private String twitterUsername;
  private List<Item> timeline;
}
