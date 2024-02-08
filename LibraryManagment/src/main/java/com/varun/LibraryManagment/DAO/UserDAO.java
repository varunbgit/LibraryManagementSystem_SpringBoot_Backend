package com.varun.LibraryManagment.DAO;

import com.varun.LibraryManagment.entity.User;

public interface UserDAO {

    public  User addUser(User user);
    public  boolean deleteUser(long userId);
}
