package in.MomenTarek.musifyapi.document;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "albums")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    @Id
    @JsonProperty("_id")
    private String id;
    private String name;
    private String desc;
    private String bgColor;
    private String imageUrl;
    private String imagePublicId;
}
