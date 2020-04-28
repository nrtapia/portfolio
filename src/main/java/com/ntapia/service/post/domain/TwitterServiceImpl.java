package com.ntapia.service.post.domain;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TwitterServiceImpl implements TwitterService {

  private static final String FIND_POSTS_LOG = "Find posts for userName: {}";

  private final TwitterRepository twitterRepository;

  public TwitterServiceImpl(TwitterRepository twitterRepository) {
    this.twitterRepository = twitterRepository;
  }

  @Override
  public Optional<List<TwitterPost>> findByUsername(String userName) {
    log.debug(FIND_POSTS_LOG, userName);

    return Optional.of(twitterRepository.findByUserName(userName));
  }
}
