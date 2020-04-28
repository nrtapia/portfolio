package com.ntapia.profile.post.domain;

import java.util.List;
import java.util.Optional;

public interface PostService {

  Optional<List<Post>> findByUsername(String userName);

}
