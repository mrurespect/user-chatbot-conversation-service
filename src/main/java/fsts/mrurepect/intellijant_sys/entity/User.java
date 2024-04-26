package fsts.mrurepect.intellijant_sys.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String username;
    @Column
    private String password ;

    @Column
    private String name ;
    @Column
    private String lastName ;
    @Column
    private String email ;
    @Setter
    @ManyToMany(mappedBy = "users")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Conversation> conversations;
    public void addConversation(Conversation conversation){
        if (conversations==null){
            conversations=new ArrayList<>();
        }
        conversations.add(conversation);
    }


    public User( String username, String password, String name, String lastName, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
