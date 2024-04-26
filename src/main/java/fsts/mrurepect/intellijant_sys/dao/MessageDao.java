package fsts.mrurepect.intellijant_sys.dao;

import fsts.mrurepect.intellijant_sys.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageDao extends JpaRepository<Message,Integer> {
}
