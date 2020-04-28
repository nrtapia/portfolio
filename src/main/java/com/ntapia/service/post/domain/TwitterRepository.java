package com.ntapia.service.post.domain;

import java.util.List;

public interface TwitterRepository {

  List<TwitterPost> findByUserName(String userName);

}
