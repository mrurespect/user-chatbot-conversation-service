package fsts.mrurepect.intellijant_sys.mapper;

import fsts.mrurepect.intellijant_sys.dto.UserResponse;
import fsts.mrurepect.intellijant_sys.entity.User;

public class UserMapper {
    public static UserResponse mappeToUserResponse(User user) {
        return UserResponse.builder().
                id(user.getId()).
                username(user.getUsername()).
                password(user.getPassword()).
                name(user.getName()).
                lastName(user.getLastName()).
                email(user.getEmail()).
                build();
    }

}
