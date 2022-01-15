package com.lagou.controller;

import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> list = roleService.findAllRole(role);
        return new ResponseResult(true,200,"角色列表查询成功",list);
    }

    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role){
        if (role.getId()==null){
            roleService.saveRole(role);
            return new ResponseResult(true,200,"角色添加成功",null);
        }else{
            roleService.updateRole(role);
            return  new ResponseResult(true,200,"角色修改成功",null);
        }
    }

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> list = roleService.findAllMenu();
        //响应数据
        Map<String,Object> map=new HashMap<>();
        map.put("parentMenuList",list);
        return  new ResponseResult(true,200,"菜单列表查询成功",map);
    }
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){
        List<Integer> list = roleService.findMenuByRoleId(roleId);
        return  new ResponseResult(true,200,"根据角色ID查询关联菜单ID查询成功",list);
    }
    /*为角色分配菜单*/
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleService.RoleContextMenu(roleMenuVo);
        return  new ResponseResult(true,200,"为角色分配菜单成功","");
    }
    /*删除角色*/
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){
        roleService.deleteRole(id);
        return new ResponseResult(true,200,"删除角色成功","");
    }

    /* 获取当前角色拥有的资源信息*/
    @RequestMapping("/findResourceListByRoleId")
    public ResponseResult findResourceListByRoleId(Integer roleId){
        List<ResourceCategory> categoryList = roleService.findResourceListByRoleId(roleId);
        return new ResponseResult(true,200,"查询当前角色拥有资源信息成功",categoryList);
    }

    /*为角色分配资源*/
    @RequestMapping("/roleContextResource")
    public ResponseResult roleContextResource(@RequestBody RoleResourceVo roleResourceVo){
        roleService.roleContextResource(roleResourceVo);
        return new ResponseResult(true,200,"分配资源成功成功",null);
    }

}
