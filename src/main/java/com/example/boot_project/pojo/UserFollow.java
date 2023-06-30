package com.example.boot_project.pojo;

import lombok.Data;

@Data
public class UserFollow {

  private String fansId;
  private String answerUserId; // 被关注者id
  private String userId; //关注者id
  private String isDeleted; //关注状态 0关注 1取消
  private String createTime;
  private String updateTime;

}
