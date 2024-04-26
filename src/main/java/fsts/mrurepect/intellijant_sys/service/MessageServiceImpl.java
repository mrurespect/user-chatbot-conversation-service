package fsts.mrurepect.intellijant_sys.service;

import fsts.mrurepect.intellijant_sys.dao.MessageDao;
import fsts.mrurepect.intellijant_sys.entity.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{
    private final MessageDao messageDao ;

    public MessageServiceImpl(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public List<Message> getMessages(int conversationId) {
        List<Message> allMessages =messageDao.findAll();
        List<Message> messages=new ArrayList<>();
        for (Message message :allMessages) {
            if (message.getConversation().getId()==conversationId){
                messages.add(message);
            }
        }
        return messages;
    }

    @Override
    public Message addMessage(Message message) {
        return messageDao.save(message);
    }
}
