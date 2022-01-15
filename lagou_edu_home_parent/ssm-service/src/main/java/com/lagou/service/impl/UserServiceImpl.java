package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.domain.User_Role_relation;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> findAllUserByPage(UserVo userVo) {

        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());
        List<User> userList = userMapper.findAllUserByPage(userVo);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    @Override
    public void updateUserStatus(Integer id, String status) {
        User user=new User();
        user.setId(id);
        user.setStatus(status);
        userMapper.updateUserStatus(user);
    }

    @Override
    public User login(User user) throws Exception {
        User user1 = userMapper.login(user);
        //将接收到的的密文进行md5加密并验证
        if (user1 !=null&& Md5.verify(user.getPassword(), "lagou", user1.getPassword())){
            return user1;
        }else {
            return null ;
        }
    }

    @Override
    public List<Role> findUserRoleById(Integer userId) {
        return userMapper.findUserRoleById(userId);
    }

    @Override
    public void userContextRole(UserVo userVo) {
        //清空关联关系
        userMapper.deleteUserContextRole(userVo.getUserId());
        List<Integer> list = userVo.getRoleIdList();
        for (Integer roleId : list) {
            //封装数据
            User_Role_relation user_role_relation=new User_Role_relation();
            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(roleId);
            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedBy("system");
            userMapper.userContextRole(user_role_relation);
        }
    }
}
