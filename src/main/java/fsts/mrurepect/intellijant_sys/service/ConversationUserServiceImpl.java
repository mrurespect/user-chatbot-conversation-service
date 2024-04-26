package fsts.mrurepect.intellijant_sys.service;

import fsts.mrurepect.intellijant_sys.dao.ConversationUserDao;
import fsts.mrurepect.intellijant_sys.entity.Conversation;
import fsts.mrurepect.intellijant_sys.entity.ConversationUser;
import fsts.mrurepect.intellijant_sys.entity.Type;
import fsts.mrurepect.intellijant_sys.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConversationUserServiceImpl implements ConversationUserService {
    private final ConversationUserDao conversationUserDao;

    public ConversationUserServiceImpl(ConversationUserDao conversationUserDao) {
        this.conversationUserDao = conversationUserDao;
    }

    @Override
    public ConversationUser find(Conversation conversation, User user) {
        return conversationUserDao.find(conversation,user);
    }

    @Override
    public List<ConversationUser> find(Conversation conversation) {
        return conversationUserDao.find(conversation);
    }
    @Override
    @Transactional
    public void set(Conversation conversation, User user, Type type) {
        conversationUserDao.set(conversation,user,type);
    }

    }
