package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> findAllRole(Role role) {
        return roleMapper.findAllRole(role);
    }

    @Override
    public void saveRole(Role role) {
        Date date=new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);
        role.setCreatedBy("system");
        role.setUpdatedBy("system");
        roleMapper.saveRole(role);
    }

    @Override
    public void updateRole(Role role) {
        role.setUpdatedTime(new Date());
        roleMapper.updateRole(role);
    }

    @Override
    public List<Menu> findAllMenu() {
        return roleMapper.findAllMenu(-1);
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {
        List<Integer> list = roleMapper.findMenuByRoleId(roleId);
        return list;
    }

    @Override
    public void RoleContextMenu(RoleMenuVo roleMenuVo) {
        //先删除级联关系
        roleMapper.deleteMenu(roleMenuVo.getRoleId());
        //添加菜单关联信息
        List<Integer> list = roleMenuVo.getMenuIdList();
        for (Integer mid : list) {
            Role_menu_relation role_menu_relation=new Role_menu_relation();
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setCreatedTime(new Date());
            role_menu_relation.setUpdatedTime(new Date());
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");
            roleMapper.RoleContextMenu(role_menu_relation);
        }
    }

    @Override
    public void deleteRole(Integer id) {
        //先删除关联的菜单信息
        roleMapper.deleteMenu(id);
        //在删除角色
        roleMapper.deleteRole(id);
    }

    @Override
    public List<ResourceCategory> findResourceListByRoleId(Integer roleId) {
        List<Resource> resource = roleMapper.findResourceByRoleId(roleId);
        if (resource.isEmpty()){//判断是否查询到资源信息，为空则为没查询到，直接返回null值
            return null;
        }
        List<ResourceCategory> list = roleMapper.findResourceListByRoleId(roleId);
        //将资源信息分类到对应资源分类下
        for (Resource resource1 : resource) {
            for (ResourceCategory category : list) {
                //循坏遍历，id相等将对应的资源信息分类到对应资源分类上
                if(resource1.getCategoryId().equals(category.getId()))
                {
                    category.setResourceList(resource);
                    break;
                }
            }
        }
        return list;
    }

    /*分配资源*/
    @Override
    public void roleContextResource(RoleResourceVo roleResourceVo) {
        //删除之前的联系
        roleMapper.deleteRoleResourceRelationByRoleId(roleResourceVo.getRoleId());
        List<Integer> list = roleResourceVo.getResourceIdList();
        for (Integer integer : list) {
            //封装信息
            Role_Resource_Relation role_resource_relation=new Role_Resource_Relation();
            role_resource_relation.setResourceId(integer);
            role_resource_relation.setRoleId(roleResourceVo.getRoleId());
            role_resource_relation.setCreatedTime(new Date());
            role_resource_relation.setUpdatedTime(new Date());
            role_resource_relation.setCreatedBy("system");
            role_resource_relation.setUpdatedBy("system");
            roleMapper.roleContextResource(role_resource_relation);
        }
    }
}
