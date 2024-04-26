package fsts.mrurepect.intellijant_sys.service;

import fsts.mrurepect.intellijant_sys.dao.ConversationDao;
import fsts.mrurepect.intellijant_sys.entity.Conversation;
import fsts.mrurepect.intellijant_sys.entity.Message;
import fsts.mrurepect.intellijant_sys.entity.User;
import fsts.mrurepect.intellijant_sys.exception.ConversationNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConversationServiceImpl implements ConversationService{
    private final ConversationDao conversationDao;

    public ConversationServiceImpl(ConversationDao conversationDao) {
        this.conversationDao = conversationDao;
    }

    @Override
    public List<Conversation> getConversation(User user) {
        List<Conversation> conversations=conversationDao.findAll();
        List<Conversation> filtredConversations=new ArrayList<>();
        for (Conversation conversation :conversations) {
            for (User u:conversation.getUsers()) {
                if (u.getId()== user.getId()){
                    filtredConversations.add(conversation);
                }
            }
        }
        return filtredConversations;
    }

    @Override
    public List<Message> getMessagesByConverId(int id) {
        Optional<Conversation> conversation =conversationDao.findById(id);
        if (conversation.isPresent()){
            return conversation.get().getMessages();
        }else{
            throw new ConversationNotFoundException("Conversation %d not found".formatted(id));
        }
    }

    @Override
    public Conversation getConversationById(int id) {
        return conversationDao.findById(id).
                orElseThrow(()->new ConversationNotFoundException("Conversation %d not found".formatted(id)));
    }

    @Override
    public Conversation addConversation(Conversation conversation) {
        return conversationDao.save(conversation);
    }
}
