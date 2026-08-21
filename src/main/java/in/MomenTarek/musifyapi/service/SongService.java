package in.MomenTarek.musifyapi.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import in.MomenTarek.musifyapi.document.Song;
import in.MomenTarek.musifyapi.dto.SongListResponse;
import in.MomenTarek.musifyapi.dto.SongRequest;
import in.MomenTarek.musifyapi.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;
    private final Cloudinary cloudinary;
    public Song addSong(SongRequest request) throws IOException {
        @SuppressWarnings("unchecked")
        Map<String,Object> audioUploadResult=cloudinary.uploader().upload(request.getAudioFile().getBytes(), ObjectUtils.asMap("resource_type","video","folder", "Musify_Project/songs","public_id",request.getAudioFile().getOriginalFilename()+"_song"));
        @SuppressWarnings("unchecked")
        Map<String,Object> imageUploadResult=cloudinary.uploader().upload(request.getImageFile().getBytes(), ObjectUtils.asMap("resource_type","image","folder", "Musify_Project/images/songs_images","public_id","image_of_"+request.getImageFile().getOriginalFilename()));
        Double durationSeconds=(Double) audioUploadResult.get("duration");
        String duration=formatDuration(durationSeconds);
        Song newSong=Song.builder()
                .name(request.getName())
                .desc(request.getDesc())
                .album(request.getAlbum())
                .duration(duration)
                .imageUrl(imageUploadResult.get("secure_url").toString())
                .file(audioUploadResult.get("secure_url").toString())
                .filePublicId(audioUploadResult.get("public_id").toString())
                .imagePublicId(imageUploadResult.get("public_id").toString())
                .build();
        return songRepository.save(newSong);
    }

    private String formatDuration(Double durationSeconds) {
        if(durationSeconds==null){
            return "0:00";
        }
        int minutes=(int)(durationSeconds/60);
        int seconds=(int)(durationSeconds%60);
        return String.format("%d:%02d",minutes,seconds);

    }
    public SongListResponse getAllSongs(){
        return new SongListResponse(true,songRepository.findAll());
    }
    public Boolean removeSong(String id){
        Song existingSong=songRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Song not found"));
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> result1 =cloudinary.uploader().destroy(existingSong.getImagePublicId(), Map.of());
            @SuppressWarnings("unchecked")
            Map<String, Object> result2 =cloudinary.uploader().destroy(existingSong.getFilePublicId(), Map.of());
            if(!"ok".equals(result1.get("result"))&&!"ok".equals(result2.get("result"))){
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"An error occured while Deleted the file");
            }
        }catch (IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"An error occured while Deleted the file");
        }
        songRepository.delete(existingSong);
        return true;
    }
}
