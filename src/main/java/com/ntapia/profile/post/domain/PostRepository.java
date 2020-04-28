package com.ntapia.profile.post.domain;

import java.util.List;

public interface PostRepository {

  List<Post> findByUserName(String userName);

}
