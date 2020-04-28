package com.ntapia.profile.post.domain;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

  private static final String FIND_POSTS_LOG = "Find posts for userName: {}";

  @Override
  public Optional<List<Post>> findByUsername(String userName) {
    log.debug(FIND_POSTS_LOG, userName);

    return Optional.of(List.of(
        Post.builder()
            .id("11")
            .photo("url")
            .text("text text")
            .userName("twitName")
            .build()
    ));
  }
}
