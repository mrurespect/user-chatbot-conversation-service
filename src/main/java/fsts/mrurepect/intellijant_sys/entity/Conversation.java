package fsts.mrurepect.intellijant_sys.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "conversation")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String nom ;

    @OneToMany(mappedBy = "conversation",fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Message> messages;
    @ManyToMany
    @JoinTable(name = "conversation_users",
                joinColumns = @JoinColumn(name = "conversations_id"),
                 inverseJoinColumns= @JoinColumn(name = "users_id")
    )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private  List<User> users;
    public void addUser(User user){
        if (users==null){
            users=new ArrayList<>();
        }
        users.add(user);
    }
    public void addMessage(Message message){
        if (messages==null){
            messages=new ArrayList<>();
        }
        messages.add(message);
    }
}
