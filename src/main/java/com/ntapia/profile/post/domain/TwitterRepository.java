package com.ntapia.profile.post.domain;

import java.util.List;

public interface TwitterRepository {

  List<TwitterPost> findByUserName(String userName);

}
