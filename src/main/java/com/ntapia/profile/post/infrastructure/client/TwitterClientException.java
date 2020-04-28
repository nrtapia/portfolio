package com.ntapia.profile.post.infrastructure.client;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Error to request twitter timeline")
public class TwitterClientException extends RuntimeException {

  public TwitterClientException() {
    super();
  }
}
