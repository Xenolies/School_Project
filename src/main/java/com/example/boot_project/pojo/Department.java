package com.example.boot_project.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Department {
@TableId("classId")
  private long classId;
  private String title;


}
