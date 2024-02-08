package com.varun.LibraryManagment.DAO;

import com.varun.LibraryManagment.entity.User;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO  {
    private EntityManager entityManager;

    @Autowired
    UserDAOImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    public  User addUser(User user) {
        try {
            entityManager.persist(user);
            return user;
        }catch (RuntimeException ex){
            throw ex;
        }
    }

    public  boolean deleteUser(long userId) {
        try{
            User user = entityManager.find(User.class, userId);
            if(user == null){
                throw new RuntimeException("No User witb user_id found. Please Retry");
            }
            entityManager.remove(user);
            return true;
        }catch (RuntimeException ex){
            throw ex;
        }
    }
}
