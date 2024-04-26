package fsts.mrurepect.intellijant_sys.service;


import fsts.mrurepect.intellijant_sys.entity.Conversation;
import fsts.mrurepect.intellijant_sys.entity.ConversationUser;
import fsts.mrurepect.intellijant_sys.entity.Type;
import fsts.mrurepect.intellijant_sys.entity.User;

import java.util.List;

public interface ConversationUserService {
    ConversationUser find(Conversation conversation, User user) ;
    List<ConversationUser> find(Conversation conversation) ;
    void set(Conversation conversation, User user, Type type);

}
