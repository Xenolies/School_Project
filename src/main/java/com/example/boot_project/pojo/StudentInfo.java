package com.example.boot_project.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class StudentInfo {

  private long id;
  private String school;
  @TableField("class_id")
  private long classId;
  @TableField("str_attest_id")
  private String strAttestId;
  @TableField("str_Name")
  private String strName;
  @TableField("str_number")
  private long strNumber;
  private String avatar;
  private String department;
  private long parentId;


}
