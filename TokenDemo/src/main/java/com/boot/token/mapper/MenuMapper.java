package com.boot.token.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.token.domain.Menu;
import com.boot.token.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 尚智江
 * @Date 2023/5/18 23:01
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    // 查询权限信息
    List<String> selectPermsByUserId(Long userId);
}
