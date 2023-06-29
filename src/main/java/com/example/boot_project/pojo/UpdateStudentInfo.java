package com.example.boot_project.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class UpdateStudentInfo {
    private String strName;
    private long strNumber;
    private long classId;
}
