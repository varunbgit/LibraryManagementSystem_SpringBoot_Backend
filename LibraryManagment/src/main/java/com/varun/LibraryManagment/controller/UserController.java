package com.varun.LibraryManagment.controller;

import com.varun.LibraryManagment.entity.User;
import com.varun.LibraryManagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private UserService userService ;

    @Autowired
    UserController(UserService theUserService){
        userService = theUserService;
    }

    @GetMapping("/user-test")
    public String user(){
        return "Hi";
    }

//    //for adding new user to database
    @PostMapping("/users")
    public User addUser(@RequestBody User user){
        try{
            return userService.addUser(user);
        }catch (RuntimeException e){
            throw e;
        }
    }
//
//    //for deletng user from database
    @DeleteMapping(  "/users/{user_id}")
    public String deleteUser(@PathVariable long user_id){
        try {
            boolean userDeleted = false;
            if(userService.deleteUser(user_id)){
                return "User Deleted Successfull";
            }
            else throw new RuntimeException("Something went wrong in deleting User");
        }catch (RuntimeException ex){

            throw ex;
        }
    }



}
