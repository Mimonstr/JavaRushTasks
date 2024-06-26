package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.model.service.UserService;
import com.javarush.task.task36.task3608.model.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class MainModel implements Model
{
    private UserService userService = new UserServiceImpl();
    private ModelData modelData = new ModelData();
    @Override
    public ModelData getModelData()
    {
        return modelData;
    }

    @Override
    public void loadUsers()
    {
        List<User> users = getAllUsers();
        modelData.setUsers(users);
        modelData.setDisplayDeletedUserList(false);
    }
    public void loadDeletedUsers()
    {
        List<User> users = userService.getAllDeletedUsers();
        modelData.setUsers(users);
        modelData.setDisplayDeletedUserList(true);
    }
    public void loadUserById(long userId)
    {
        User user = userService.getUsersById(userId);
        modelData.setActiveUser(user);
    }
    public void deleteUserById(long id)
    {
        User user = userService.deleteUser(id);
        List<User> users = getAllUsers();
        //refresh model data
        modelData.setUsers(users);
    }
    public void changeUserData(String name, long id, int level)
    {
        User updateUser = userService.createOrUpdateUser(name, id, level);
        List<User> users = getAllUsers();
        //refresh model data
        modelData.setUsers(users);
    }
    private List<User> getAllUsers()
    {
        //model should contain all business logic in the methods
        List<User> allUsers = userService.getUsersBetweenLevels(1, 100);
        allUsers = userService.filterOnlyActiveUsers(allUsers);
        return allUsers;
    }
}
