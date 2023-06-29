package com.example.boot_project.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("student_infodto")
public class StudentInfoDto {
    private String school;
    @TableField("str_Name")
    private String strName;
    @TableId("str_number")
    private long strNumber;
    private String department;
}
