package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface RoleMapper {
    /*多条件查询所有*/
    public List<Role> findAllRole(Role role);
    /*添加角色*/
    public void saveRole(Role role);
    /*修改角色*/
    public void updateRole(Role role);
    /*查询所有的父子集菜单*/
    public List<Menu> findAllMenu(int pid);
    /*根据角色ID查询关联菜单ID*/
    public List<Integer> findMenuByRoleId(Integer roleId);
    /*删除角色关联菜单*/
    void deleteMenu(Integer roleId);
    /*角色菜单关联*/
    void RoleContextMenu(Role_menu_relation role_menu_relation);
    /*删除角色*/
    void deleteRole(Integer id);
     //查询当前角色拥有的资源分类信息
    public List<ResourceCategory> findResourceListByRoleId(Integer id);
    //查询当前角色拥有的资源信息
    public List<Resource> findResourceByRoleId(Integer id);
    //根据角色ID删除角色与资源的关联关系(操作中中间表 role_resource_relation)
    public void deleteRoleResourceRelationByRoleId(Integer roleId);
    //为角色分配菜单
    public void roleContextResource(Role_Resource_Relation role_resource_relation);
}
