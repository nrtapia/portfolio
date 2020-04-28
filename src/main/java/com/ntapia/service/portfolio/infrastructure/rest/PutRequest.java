package com.ntapia.service.portfolio.infrastructure.rest;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PutRequest {

  private String photo;
  private String title;
  private String description;
  private String twitterUsername;
}
