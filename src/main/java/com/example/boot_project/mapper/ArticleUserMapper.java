package com.example.boot_project.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot_project.pojo.ArticleUser;
import com.example.boot_project.pojo.UserDataDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Xenolies
 * @Date: 2023/6/30 11:00
 * @Version: 0.1
 */
@Mapper
public interface ArticleUserMapper  extends BaseMapper<ArticleUser> {
}
