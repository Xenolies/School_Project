package com.example.boot_project.pojo;

import lombok.Data;

/**
 * @Author: Xenolies
 * @Date: 2023/6/29 16:46
 * @Version: 0.1
 */

@Data
public class UserInfoDtoQuery extends UserInfoDto{
    private long queryId;
}
