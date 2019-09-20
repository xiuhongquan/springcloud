package com.atguigu.springcloud.service;

import com.atguigu.springcloud.dao.MenuDao;
import com.atguigu.springcloud.entities.Menu;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class Menuservice {
    @Autowired
    private MenuDao menuDao;

    public List<Menu>  testMenuTree(){
        // 原始的数据
        List<Menu> rootMenu = menuDao.queryMenuList();
        // 查看结果
        for (Menu menu : rootMenu) {
            System.out.println(menu);
        }
        // 最后的结果
        List<Menu> menuList = new ArrayList<Menu>();
        // 先找到所有的一级菜单
        for (int i = 0; i < rootMenu.size(); i++) {
            // 一级菜单没有parentId
            if (StringUtils.isBlank(rootMenu.get(i).getParentId())) {
                menuList.add(rootMenu.get(i));
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (Menu menu : menuList) {
            menu.setChildMenus(getChild(menu.getId(), rootMenu));
        }


        return  menuList;
    }

/*
        1.先得到所有的菜单列表
        2.对所有的菜单列表进行遍历，得到所有的菜单列表rootMenuList
        3.对菜单进行遍历，找到父菜单列表 menuList
        4.通过遍历菜单列表找到父菜单的子菜单 childrenList
        5.使用递归找子菜单的子菜单：递归条件：当菜单的childrenList为0的时候结束递归
 */

    /**
     * 递归查找子菜单
     *
     * @param id
     *            当前菜单id
     * @param rootMenu
     *            要查找的列表
     * @return
     */
    private List<Menu> getChild(String id, List<Menu> rootMenu) {
        // 子菜单
        List<Menu> childList = new ArrayList<>();
        for (Menu menu : rootMenu) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (StringUtils.isNotBlank(menu.getParentId())) {
                if (menu.getParentId().equals(id)) {
                    childList.add(menu);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (Menu menu : childList) {// 没有url子菜单还有子菜单

                // 递归
                menu.setChildMenus(getChild(menu.getId(), rootMenu));

        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }


}






