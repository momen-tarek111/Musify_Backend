package in.MomenTarek.musifyapi.dto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SongRequest {
    private String name;
    private String desc;
    private String album;
    private MultipartFile audioFile;
    private MultipartFile imageFile;
}
