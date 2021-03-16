package com.lagou.dao;

import com.lagou.pojo.User;

import java.util.List;

/**
 * @author lijian
 * @create 2021-03-10 15:27
 */
public interface IUserDao {
    public List<User> findAll();
    public User findByCondition(User user);
    public int insertUser(User user);
    public int deleteUser(User user);
    public int updateUser(User user);
}
