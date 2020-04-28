package com.ntapia.service.portfolio.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "portfolio")
@Data
@EqualsAndHashCode
public class Portfolio implements Serializable {

  private static final long serialVersionUID = 20200427L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idportfolio", nullable = false)
  private Long id;

  @Column(name = "description", length = 255, nullable = true)
  private String description;

  @Column(name = "image_url", length = 255, nullable = true)
  private String imageUrl;

  @Column(name = "twitter_user_name", length = 255, nullable = true)
  private String twitterUsername;

  @Column(name = "title", length = 255, nullable = true)
  private String title;

}
