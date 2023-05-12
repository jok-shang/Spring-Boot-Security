package com.boot.token.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.token.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @auther 尚智江
 * @Date 2023/5/12 22:51
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
