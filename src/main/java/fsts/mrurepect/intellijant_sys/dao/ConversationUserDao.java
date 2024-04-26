package fsts.mrurepect.intellijant_sys.dao;



import fsts.mrurepect.intellijant_sys.entity.Conversation;
import fsts.mrurepect.intellijant_sys.entity.ConversationUser;
import fsts.mrurepect.intellijant_sys.entity.Type;
import fsts.mrurepect.intellijant_sys.entity.User;

import java.util.List;

public interface ConversationUserDao {
     public void set(Conversation conversation, User user, Type type) ;

          ConversationUser find(Conversation conversation, User user) ;
          List<ConversationUser> find(Conversation conversation) ;
}
