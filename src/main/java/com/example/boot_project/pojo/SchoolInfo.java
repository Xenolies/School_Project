package com.example.boot_project.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("schoolInfo")
public class SchoolInfo {
@TableId("classid")
  private long classid;
  private String title;

}
