package com.ntapia.profile.post.infrastructure.client;

import com.ntapia.profile.post.domain.TwitterPost;
import com.ntapia.profile.post.domain.TwitterRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Slf4j
@Component
public class TwitterClientImpl implements TwitterRepository {

  private static final String TWITTER_TIMELINE_LOG = "Twitter timeline for: {}";
  private static final String ERROR_TO_REQUEST_LOG = "Error to request twitter timeline: {}";

  @Value("${post.timeline.size}")
  private int timelineSize;

  @Override
  public List<TwitterPost> findByUserName(String userName) {
    log.debug(TWITTER_TIMELINE_LOG, userName);

    try {
      var statusList = TwitterFactory.getSingleton()
          .getUserTimeline(userName, new Paging(1, timelineSize));

      return statusList.stream()
          .map(this::mapToPost)
          .collect(Collectors.toList());

    } catch (TwitterException e) {
      log.error(ERROR_TO_REQUEST_LOG, userName, e);
      throw new TwitterClientException();
    }
  }

  private TwitterPost mapToPost(Status status) {
    return TwitterPost.builder()
        .userName(status.getUser().getName())
        .text(status.getText())
        .photo(status.getUser().getMiniProfileImageURL())
        .id(status.getId())
        .build();
  }
}
