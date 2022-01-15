package com.lagou.service;

import com.lagou.domain.*;

import java.util.List;

public interface RoleService {

    /*多条件查询所有*/
    public List<Role> findAllRole(Role role);
    /*添加角色*/
    public void saveRole(Role role);
    /*修改角色*/
    public void updateRole(Role role);
    /*查询所有的父子集菜单*/
    public List<Menu> findAllMenu();
    /*根据角色ID查询关联菜单ID*/
    public List<Integer> findMenuByRoleId(Integer roleId);
    /*角色菜单关联*/
    void RoleContextMenu(RoleMenuVo roleMenuVo);
    /*删除角色*/
    void deleteRole(Integer id);
    //查询当前角色拥有的资源分类信息
    public List<ResourceCategory> findResourceListByRoleId(Integer id);
    //为角色分配菜单
    public void roleContextResource(RoleResourceVo roleResourceVo);
}
