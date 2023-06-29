package com.example.boot_project;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.UUID;

/**
 * @Author: Xenolies
 * @Date: 2023/6/29 10:49
 * @Version: 0.1
 */


public class UUID_Test {

    @Test
    public void UUID_TEST(){
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
        UUID uuid2 = UUID.nameUUIDFromBytes("HELLO".getBytes());
        System.out.println(uuid2.toString());
    }

    @Test
    public void RandCode() {
        Random yzm = new Random();
        String string = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String code = "" ;
        for (int i = 0; i < 5; i++) {
            int a = yzm.nextInt(string.length()-1); //随机生成0-57之间的数，提供索引位置
            code += string.charAt(a);//用get 和提供的索引找到相应位置的数据给变量
        }
        System.out.println(code);
    }
}
