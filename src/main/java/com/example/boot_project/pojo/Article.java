package com.example.boot_project.pojo;

import lombok.Data;

@Data
public class Article {

  private String articleId;
  private String title;
  private String content;
  private long createTime;
  private long commentNum;
  private long viewsNum;
  private long shareNum;
  private long likeNum;

}
