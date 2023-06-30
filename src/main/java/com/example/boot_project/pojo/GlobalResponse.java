package com.example.boot_project.pojo;

import lombok.Data;

/**
 * @Author: Xenolies
 * @Date: 2023/6/30 9:46
 * @Version: 0.1
 */

@Data
// Api全局响应信息
public class GlobalResponse {
    private boolean Success;// 响应状态
    private int Code;// 响应码
    private String Message;// 响应消息
}
