package com.example.boot_project.pojo;

import lombok.Data;

@Data
public class UserNotification {

  private String id;
  private String msg;
  private String image;
  private long signFlag;
  private String acceptUserId;
  private String sendUserId;
  private String sendUserName;
  private long updateTime;
  private long type;
  private String content;
  private String tips;
  private long createTime;
  private String commonId;
  private long commonIdType;
  private String articleLikeId;

}
