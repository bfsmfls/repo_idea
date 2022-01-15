package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;

import java.util.List;

public interface UserService {
    /*分页多条件查询用户*/
    public PageInfo<User> findAllUserByPage(UserVo userVo);
    /*用户状态设置*/
    public void updateUserStatus(Integer id,String status);
    /*验证登录*/
    public User login(User user) throws Exception;

    /*根据用户id查询角色相关信息*/
    List<Role> findUserRoleById(Integer userId);

    /*用户关联角色*/
    void userContextRole(UserVo userVo);

}
