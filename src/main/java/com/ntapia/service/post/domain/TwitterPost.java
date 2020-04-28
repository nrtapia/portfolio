package com.ntapia.service.post.domain;

import java.io.Serializable;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TwitterPost implements Serializable {

  private static final long serialVersionUID = 20200427L;

  private Long id;
  private String userName;
  private String text;
  private String photo;

}
