package in.MomenTarek.musifyapi.dto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlbumRequest {
    private String name;
    private String desc;
    private String bgColor;
    private MultipartFile imageFile;
}
