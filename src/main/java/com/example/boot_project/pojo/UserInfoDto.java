package com.example.boot_project.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: Xenolies
 * @Date: 2023/6/29 14:44
 * @Version: 0.1
 */

@Data
@TableName("Users")
public class UserInfoDto {
    private String phoneNumber;
    private String signature;
    private int sex;
    private String userName;
    private String userId;
    private String Id;
}
