package com.ntapia.profile.post.infrastructure.client;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class PostTwitterClientException extends RuntimeException {

  public PostTwitterClientException(String message) {
    super(message);
  }
}
