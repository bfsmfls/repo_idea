package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.domain.User_Role_relation;

import java.util.List;

public interface UserMapper {

    /*分页多条件查询用户*/
    public List<User> findAllUserByPage(UserVo userVo);
    /*用户状态设置*/
    public void updateUserStatus(User user);

    /*用户登录(根据用户名查询相关的用户信息)*/
    public User login(User user);

    /*根据用户id查询角色相关信息*/
    List<Role> findUserRoleById(Integer userId);

    /*
根据用户ID清空中间表
*/
    void deleteUserContextRole(Integer userId);
    /*
    分配角色
    */
    void userContextRole(User_Role_relation user_role_relation);

    /*获取当前角色拥有的 资源信息 */
    void userContextRole2(User_Role_relation user_role_relation);



    /*为角色分配菜单*/

}
