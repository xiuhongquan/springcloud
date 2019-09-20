package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuDao {

    /**
     * 查找用户的菜单
     *
     * @return
     */
    public List<Menu> queryMenuList();
}
