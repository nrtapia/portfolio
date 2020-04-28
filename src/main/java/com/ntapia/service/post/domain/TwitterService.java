package com.ntapia.service.post.domain;

import java.util.List;
import java.util.Optional;

public interface TwitterService {

  Optional<List<TwitterPost>> findByUsername(String userName);

}
