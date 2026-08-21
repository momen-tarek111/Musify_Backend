package in.MomenTarek.musifyapi.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "songs")
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor
public class Song {
    @Id
    @JsonProperty("_id")
    private String id;
    private String name;
    private String desc;
    private String album;
    private String imageUrl;
    private String imagePublicId;
    private String file;
    private String filePublicId;
    private String duration;
}
