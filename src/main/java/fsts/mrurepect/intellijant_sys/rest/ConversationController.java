package fsts.mrurepect.intellijant_sys.rest;


import fsts.mrurepect.intellijant_sys.dto.UserResponse;
import fsts.mrurepect.intellijant_sys.entity.*;
import fsts.mrurepect.intellijant_sys.mapper.UserMapper;
import fsts.mrurepect.intellijant_sys.service.ConversationService;
import fsts.mrurepect.intellijant_sys.service.ConversationUserService;
import fsts.mrurepect.intellijant_sys.service.MessageService;
import fsts.mrurepect.intellijant_sys.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

@Controller
public class ConversationController {
    private final UserService userService ;
    private final ConversationService conversationService;
    private final MessageService messageService;
    private final ConversationUserService conversationUserService;

    private Conversation conversation=null;

    private String role = Type.normal.toString();

    @Autowired
    ConversationController(UserService service, ConversationService conversationService, MessageService messageService, ConversationUserService conversationUserService) {
        this.userService = service;
        this.conversationService = conversationService;
        this.messageService = messageService;
        this.conversationUserService = conversationUserService;
    }

    @GetMapping("/login")
    public ResponseEntity<String> status(Model model, HttpSession session){
        if (session.getAttribute("user")!=null){
            return new ResponseEntity<>("connected", HttpStatus.OK);
        }

        model.addAttribute("theDate",new Date());
        return new ResponseEntity<>("login required",HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
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
            System.out.println("n existe pas");
        return new ResponseEntity<>("echec d'authentification ...",HttpStatus.NOT_FOUND);
    }
    @GetMapping("/conversations")
    public ResponseEntity<List<Conversation>> conversations(Model model,HttpSession session){
        if (session.getAttribute("user")==null){
            return new ResponseEntity<>(List.of(),HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
        }

        User current=(User) session.getAttribute("user");
        List<Conversation> conversations=conversationService.getConversation(current);
        return new ResponseEntity<>(conversations,HttpStatus.OK);
    }
    @GetMapping("/conversation/{id}")
    public ResponseEntity<List<Message>> messagesByConversId(@PathVariable("id") int id,HttpSession session){
        if (session.getAttribute("user")==null){
            return new ResponseEntity<>(List.of(),HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
        }

        List<Message> messages =conversationService.getMessagesByConverId(id);
        return new ResponseEntity<>(messages,HttpStatus.OK);
    }
    @GetMapping("/remove")
    public  ResponseEntity<String> remove(HttpSession session){
        session.invalidate();
        return new ResponseEntity<>("deconnected succesfully",HttpStatus.OK);
    }
    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message,HttpSession session) {
        if (session.getAttribute("user")==null){
            return new ResponseEntity<>(null,HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
        }

        User sender =userService.getUser(message.getSender().getId());
        Conversation currentConversation =conversationService.getConversationById(message.getConversation().getId());
        message.setSender(sender);                          //
        message.setTime(new Date());
        message.setConversation(currentConversation);       //

        Message dbMessage =messageService.addMessage(message);
        return new ResponseEntity<>(dbMessage,HttpStatus.OK);
    }
    @GetMapping("/info")
    public ResponseEntity<UserResponse> getInfo(HttpSession session){

        if (session.getAttribute("user")==null){
            return new ResponseEntity<>(null,HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
        }

        User user = (User) session.getAttribute("user");
        UserResponse userResponse = UserMapper.mappeToUserResponse(user);
        return new ResponseEntity<>(userResponse,HttpStatus.OK);
    }




    @GetMapping("/")
    public String redirect(){
        return "redirect:/login";
    }
    @GetMapping("/users")
    public String getUsers(Model model){
        //ConversationUser conversationUser =conversationUserService.find(conversation,user);
        List<ConversationUser> conversationUsers =conversationUserService.find(conversation);
        System.out.println("conv user ="+conversationUsers);
        model.addAttribute("conversationUsers",conversationUsers);

        return "conversationUsers" ;
    }

}
