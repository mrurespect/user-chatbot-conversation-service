package fsts.mrurepect.intellijant_sys.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "conversation_users")
@IdClass(ConversationUserId.class)
@Data @NoArgsConstructor @AllArgsConstructor
public class ConversationUser {

    @Id
    @ManyToOne
    @JoinColumn(name = "conversations_id")
    private Conversation conversation;

    @Id
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private Type type;

}

