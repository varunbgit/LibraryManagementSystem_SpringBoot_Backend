package com.varun.LibraryManagment.service;

import com.varun.LibraryManagment.DAO.UserDAOImpl;
import com.varun.LibraryManagment.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@Component
@Service
public class UserService {

    @Autowired
    private UserDAOImpl userDAO;
    UserService(UserDAOImpl theUserDAO){
        userDAO = theUserDAO;
    }
    @Transactional
    public User addUser(User user) {
        try{
            return userDAO.addUser(user);
        }catch (RuntimeException e){
            throw e;
        }
    }

    @Transactional
    @DeleteMapping("/user/{userId}")
    public  boolean deleteUser(@PathVariable long userId) {
        try{
            return userDAO.deleteUser(userId);
        }catch (RuntimeException ex){
            throw ex;
        }
    }
}
