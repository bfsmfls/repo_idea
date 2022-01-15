package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> list = menuService.findAllMenu();
        return new ResponseResult(true,200,"查询菜单列表成功",list);
    }

    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){
        if (id==-1){
            //说明是新增操作
            List<Menu> list = menuService.findAllMenu2(id);
            //封装数据
            Map<String,Object> map=new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",list);
            return new ResponseResult(true,200,"新建菜单回显成功",map);
        }else{
            Menu menu = menuService.findMenuById(id);
            //说明是修改操作
            List<Menu> list = menuService.findAllMenu2(id);
            //封装数据
            Map<String,Object> map=new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",list);
            return new ResponseResult(true,200,"修改菜单回显成功",map);
        }
    }

}
