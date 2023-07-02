package com.example.boot_project.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class ArticleUser {

  private String articleId;
  @TableField("user_id")
  private String userId;
  @TableField("like_status")
  private long likeStatus;
  @TableField("fans_state")
  private long fansState;
  @TableField("private_state")
  private String privateState;
  @TableField("mutual_concern")
  private long mutualConcern;

}
