package fsts.mrurepect.intellijant_sys.service;


import fsts.mrurepect.intellijant_sys.entity.Conversation;
import fsts.mrurepect.intellijant_sys.entity.Message;
import fsts.mrurepect.intellijant_sys.entity.User;

import java.util.List;

public interface ConversationService {
    List<Conversation> getConversation(User user);

    List<Message> getMessagesByConverId(int id);
    Conversation getConversationById(int id);

    Conversation addConversation(Conversation conversation);
}
