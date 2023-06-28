package com.example.boot_project.pojo;

import lombok.Data;

@Data
public class ArticleUser {

  private String articleId;
  private String userId;
  private long likeStatus;
  private long fansState;
  private String privateState;
  private long mutualConcern;

}
