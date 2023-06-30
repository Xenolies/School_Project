package com.example.boot_project.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("article_user")
public class ArticleUser {

  private String articleId;
  @TableField("user_id")
  private String userId;
  private long likeStatus;
  private long fansState;
  private String privateState;
  private long mutualConcern;

}
