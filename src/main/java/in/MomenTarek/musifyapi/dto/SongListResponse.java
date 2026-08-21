package in.MomenTarek.musifyapi.dto;


import in.MomenTarek.musifyapi.document.Song;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SongListResponse {
    private Boolean success;
    private List<Song> songs;
}
