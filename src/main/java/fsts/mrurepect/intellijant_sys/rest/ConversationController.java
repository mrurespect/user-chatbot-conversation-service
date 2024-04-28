package fsts.mrurepect.intellijant_sys.rest;


import fsts.mrurepect.intellijant_sys.dto.UserResponse;
import fsts.mrurepect.intellijant_sys.entity.Conversation;
import fsts.mrurepect.intellijant_sys.entity.Message;
import fsts.mrurepect.intellijant_sys.entity.User;
import fsts.mrurepect.intellijant_sys.exception.UserNotConnectedException;
import fsts.mrurepect.intellijant_sys.exception.UserNotFoundException;
import fsts.mrurepect.intellijant_sys.mapper.UserMapper;
import fsts.mrurepect.intellijant_sys.service.ConversationService;
import fsts.mrurepect.intellijant_sys.service.MessageService;
import fsts.mrurepect.intellijant_sys.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class ConversationController {
    private final UserService userService ;
    private final ConversationService conversationService;
    private final MessageService messageService;
    private final User root ;


    @Autowired
    ConversationController(UserService service, ConversationService conversationService, MessageService messageService) {
        this.userService = service;
        this.conversationService = conversationService;
        this.messageService = messageService;
        root=userService.getUser(1000);
    }

    @GetMapping("/login")
    public ResponseEntity<String> status(HttpSession session){
        if (session.getAttribute("user")!=null){
            return new ResponseEntity<>("connected", HttpStatus.OK);
        }

        throw  new UserNotConnectedException("login required");
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user,HttpSession session){
        User userx = userService.getUser(user);
        if (userx !=null){
            System.out.println("exist");
            session.setAttribute("user",userx);
            System.out.println(session.getAttribute("user"));
            return new ResponseEntity<>("connected succesfully",HttpStatus.OK);
        }
            throw new UserNotFoundException("echec d'echec d'authentification ..., User not found");
    }
    @GetMapping("/conversations")
    public ResponseEntity<List<Conversation>> conversations(HttpSession session){
        if (session.getAttribute("user")==null){
            throw  new UserNotConnectedException("Not connected ,login required");
        }

        User current=(User) session.getAttribute("user");
        List<Conversation> conversations=conversationService.getConversation(current);
        return new ResponseEntity<>(conversations,HttpStatus.OK);
    }
    @GetMapping("/conversation/{id}")
    public ResponseEntity<List<Message>> messagesByConversId(@PathVariable("id") int id,HttpSession session){
        if (session.getAttribute("user")==null){
            throw  new UserNotConnectedException("Not connected ,login required");
        }

        List<Message> messages =conversationService.getMessagesByConverId(id);
        return new ResponseEntity<>(messages,HttpStatus.OK);
    }
    @GetMapping("/remove")
    public  ResponseEntity<String> remove(HttpSession session){
        session.invalidate();
        return new ResponseEntity<>("deconnected succesfully",HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity<UserResponse> getInfo(HttpSession session){

        if (session.getAttribute("user")==null){
            throw  new UserNotConnectedException("Not connected ,login required");
        }

        User user = (User) session.getAttribute("user");
        UserResponse userResponse = UserMapper.mappeToUserResponse(user);
        return new ResponseEntity<>(userResponse,HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        if (userService.addUser(user)){
            return new ResponseEntity<>("user registred successfuly",HttpStatus.OK);
        }
        return new ResponseEntity<>("An error occured ,user not added",HttpStatus.NOT_FOUND);
    }

    @PostMapping("/conversation")
    public ResponseEntity<Conversation> createConversation(@RequestBody Conversation conversation,HttpSession session){
        if (session.getAttribute("user")==null){
            throw  new UserNotConnectedException("Not connected ,login required");
        }

        User user = (User) session.getAttribute("user");
        conversation.addUser(user);
        conversation.addUser(root);
        Conversation dbConversation =conversationService.addConversation(conversation);
        return new ResponseEntity<>(dbConversation,HttpStatus.OK);
    }
    @PostMapping("/message")
    public ResponseEntity<Message> addUserToConversation(@RequestBody Message message,HttpSession session){
        if (session.getAttribute("user")==null){
            throw  new UserNotConnectedException("Not connected ,login required");
        }

        Conversation conversation = conversationService.getConversationById(message.getConversation().getId());
        User user = userService.getUser(message.getSender().getId());
        message.setTime(new Date());
        message.setConversation(conversation);
        message.setSender(user);

        Message dbMessage =messageService.addMessage(message);
        return new ResponseEntity<>(dbMessage,HttpStatus.OK);
    }
}
