package com.lagou.controller;


import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/findAllUserByPage")
    public ResponseResult  findAllUserByPage(@RequestBody UserVo userVo){
        PageInfo<User> pageInfo = userService.findAllUserByPage(userVo);
        return new ResponseResult(true,200,"查询成功",pageInfo);
    }
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(Integer id,String status){
        userService.updateUserStatus(id, status);
        Map<String,String> map=new HashMap<>();
        map.put("status",status);
        return new ResponseResult(true,200,"状态更改成功",map);
    }

    @RequestMapping("/login")
    public ResponseResult login(HttpServletRequest request,User user) throws Exception {
        User user1 = userService.login(user);
        if (user1!=null){
            //将用户信息存入session
            String access_token = UUID.randomUUID().toString();
            request.getSession().setAttribute("access_token",access_token);
            request.getSession().setAttribute("user_id",user1.getId());
            //将信息封装进map返回给前端
            Map<String,Object> map=new HashMap<>();
            map.put("access_token",access_token);
            map.put("user_id",user1.getId());
            map.put("user",user1);
            return new ResponseResult(true,1,"success",map);
        }else {
            return new ResponseResult(true,400,"error",null);
        }
    }

    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(Integer id){
        List<Role> list = userService.findUserRoleById(id);
        return new ResponseResult(true,200,"分配角色回显成功",list);
    }

    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){
        userService.userContextRole(userVo);
        return new ResponseResult(true,200,"分配角色成功",null);
    }

}
