package com.example.boot_project.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class StudentInfo {

  private String school;
  @TableField("class_id")
  private long classId;
  @TableId("str_attest_id")
  private String strAttestId;
  @TableField("str_Name")
  private String strName;
  @TableField("str_number")
  private long strNumber;
  private String avatar;
  private String department;
  private long parentId;


}
