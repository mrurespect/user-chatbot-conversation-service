package fsts.mrurepect.intellijant_sys.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class UserResponse {
    private int id;

    private String username;

    private String password ;

    private String name ;

    private String lastName ;

    private String email ;

}
