package com.example.boot_project.helper;

/*
  @Author: Xenolies
 * @Date: 2023/6/29 10:30
 * @Version: 0.1
 */


import java.util.Random;
import java.util.UUID;

/*
 * 常用工具类
 */
public class helper {

    // GenerateUUID 生成UUID
    public static String GenerateUUID(){
        UUID uuid = UUID.nameUUIDFromBytes("School_project".getBytes());
        return uuid.toString();
    }


    // RandCode 验证码生成
    public static String RandCode() {
        Random yzm = new Random();
        String string = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String code = "" ;
        for (int i = 0; i < 5; i++) {
            int a = yzm.nextInt(string.length()-1); //随机生成随机数，提供索引位置
            code += string.charAt(a);//用get 和提供的索引找到相应位置的数据给变量
        }
        return code;
    }

    // 生成用户Token

}
