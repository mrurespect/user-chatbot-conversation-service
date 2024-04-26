package fsts.mrurepect.intellijant_sys.service;


import fsts.mrurepect.intellijant_sys.entity.Message;

import java.util.List;

public interface MessageService {
    List<Message>  getMessages(int conversationId);
    Message addMessage(Message message);
}
