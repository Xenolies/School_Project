package com.example.boot_project.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Users {
  @TableId("userId")
  private String userId;
  private long id;
  @TableField("userName")
  private String userName;
  @TableField("classId")
  private String classId;
  @TableField("phoneNumber")
  private String phoneNumber;
  @TableField("signature")
  private String signature;
  @TableField("fansNum")
  private long fansNum;
  @TableField("sex")
  private long sex;
  @TableField("avatar")
  private String avatar;
  @TableField("createTime")
  private long createTime;
  @TableField("updateTime")
  private long updateTime;
  @TableField("fansState")
  private String fansState;
  @TableField("userIdentity")
  private String userIdentity;
  @TableField("mutualConcern")
  private long mutualConcern;
  @TableField("badge")
  private long badge;



}
