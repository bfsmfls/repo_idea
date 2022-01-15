package com.lagou.service;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuService {
    /*查询所有的父子集菜单*/
    public List<Menu> findAllMenu2(int pid);

    /*查询所有菜单列表*/
    public List<Menu> findAllMenu();

    /*根据id查询菜单信息*/
    public Menu findMenuById(Integer id);
}
