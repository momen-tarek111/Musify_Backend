package in.MomenTarek.musifyapi.dto;

import in.MomenTarek.musifyapi.document.Album;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlbumListResponse {
    private boolean success;
    private List<Album> albums;
}
