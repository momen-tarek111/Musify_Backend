package in.MomenTarek.musifyapi.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import in.MomenTarek.musifyapi.document.Album;
import in.MomenTarek.musifyapi.dto.AlbumListResponse;
import in.MomenTarek.musifyapi.dto.AlbumRequest;
import in.MomenTarek.musifyapi.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final Cloudinary cloudinary;
    public Album addAlbum(AlbumRequest request) throws IOException {
        @SuppressWarnings("unchecked")
        Map<String,Object> imageUploadResult=cloudinary.uploader().upload(request.getImageFile().getBytes(), ObjectUtils.asMap("resource_type","image","folder", "Musify_Project/images/albums_images","public_id","image_of_"+request.getImageFile().getOriginalFilename()));
        Album newAlbum=Album.builder()
                .name(request.getName())
                .desc(request.getDesc())
                .bgColor(request.getBgColor())
                .imageUrl(imageUploadResult.get("secure_url").toString())
                .imagePublicId(imageUploadResult.get("public_id").toString())
                .build();
        return albumRepository.save(newAlbum);
    }
    public AlbumListResponse getAllAlbums(){
        return new AlbumListResponse(true,albumRepository.findAll());
    }

    public Boolean removeAlbum(String id){
        Album existingAlbum=albumRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Album not found"));
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> result =cloudinary.uploader().destroy(existingAlbum.getImagePublicId(), Map.of());
            if(!"ok".equals(result.get("result"))){
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"An error occured while Deleted the file");
            }
        }catch (IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"An error occured while Deleted the file");
        }
        albumRepository.delete(existingAlbum);
        return true;
    }
}
