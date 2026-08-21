package in.MomenTarek.musifyapi.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    private String email;
    private String password;
    private Role role=Role.USER;
    public enum Role{
        USER,ADMIN
    }
}
