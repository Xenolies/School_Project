package com.example.boot_project.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: Xenolies
 * @Date: 2023/6/30 10:40
 * @Version: 0.1
 */
@Data
@TableName("users")
public class UserDataDto  {
    //private String userId ; // 用户唯一标识
    private long fansNum; // 粉丝数
    private long mutualConcern; // 关注数
}
