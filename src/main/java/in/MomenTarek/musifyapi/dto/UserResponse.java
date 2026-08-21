package in.MomenTarek.musifyapi.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String id;
    private String email;
    private Role role;
    public enum Role{
        USER,ADMIN
    }
}
