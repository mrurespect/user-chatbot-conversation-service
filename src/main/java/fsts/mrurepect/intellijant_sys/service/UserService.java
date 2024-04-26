package fsts.mrurepect.intellijant_sys.service;


import fsts.mrurepect.intellijant_sys.entity.User;

public interface UserService {
    User getUser(User user);
    User getUser(int id);
}
