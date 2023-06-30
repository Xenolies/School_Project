package com.example.boot_project.pojo;

import lombok.Data;

@Data
public class Users {

  private String userId;  // 用户唯一字段
  private long id; // user的ID
  private String userName; // 用户名
  private String classId; //
  private String phoneNumber; // 手机号
  private String signature; // 简介
  private long fansNum; // 粉丝数
  private long sex; //性别
  private String avatar; // 头像路径
  private long createTime; // 创建时间
  private long updateTime; // 更新时间
  private String fansState; //
  private String userIdentity;
  private long mutualConcern; // 关注数
  private long badge;  // 徽章
}
