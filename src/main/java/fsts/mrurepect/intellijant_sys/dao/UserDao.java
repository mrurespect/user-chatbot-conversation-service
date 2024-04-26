package fsts.mrurepect.intellijant_sys.dao;

import fsts.mrurepect.intellijant_sys.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {

}
