package com.ntapia.service.portfolio.infrastructure.rest;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
class Item {

  private Long id;
  private String photo;
  private String name;
  private String description;
}