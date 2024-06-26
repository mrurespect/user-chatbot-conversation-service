package fsts.mrurepect.intellijant_sys.service;

import fsts.mrurepect.intellijant_sys.dao.UserDao;
import fsts.mrurepect.intellijant_sys.entity.User;
import fsts.mrurepect.intellijant_sys.exception.UserAlreadyExistException;
import fsts.mrurepect.intellijant_sys.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao ;
    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUser(User user) {
         List<User> users =userDao.findAll();
        for (User tempUser:users) {
            if (Objects.equals(tempUser.getUsername(), user.getUsername())&&
                    Objects.equals(tempUser.getPassword(), user.getPassword())
            ){
                return tempUser ;
            }
        }
        return null ;
    }

    @Override
    public User getUser(int id) {
        Optional<User> optionalUser= userDao.findById(id);
        return optionalUser.
                orElseThrow(()->new UserNotFoundException(
                        "User with id %d not found".formatted(id)
                ));
    }

    @Override
    public boolean addUser(User user) {
        userDao.findAll().forEach(tempUser -> {
            if (
                    Objects.equals(tempUser.getUsername(), user.getUsername())
                    ||
                    Objects.equals(tempUser.getEmail(), user.getEmail())
            ){
                throw new UserAlreadyExistException("User already exist");
            }
        });
        userDao.save(user);
        return true;
    }
}
