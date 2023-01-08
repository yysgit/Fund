package com.yys.fund.mapper;

import com.yys.fund.entity.DbUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DbUserMapper {


    /**
     * 添加管理员用户
     *
     * @param user
     * @return
     */
    int addUser(DbUser user);

    /**
     * 登陆查询
     *
     * @param map
     * @return
     */
    List<DbUser> findUserByUsernamePassword(Map map);

    /**
     * 更新管理员用户
     *
     * @param user
     * @return
     */
    int updateUser(DbUser user);

    int updateUserLoginTime(DbUser user);

    int deleteUser(DbUser user);

    /**
     * 通过登陆名称查询单个管理员用户
     *
     * @param userId
     * @param username
     * @return
     */
    List<DbUser> findUserByUserName(@Param("userId") Integer userId, @Param("username") String username);


}