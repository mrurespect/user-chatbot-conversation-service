package fsts.mrurepect.intellijant_sys.dao;


import fsts.mrurepect.intellijant_sys.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationDao extends JpaRepository<Conversation,Integer> {
}
