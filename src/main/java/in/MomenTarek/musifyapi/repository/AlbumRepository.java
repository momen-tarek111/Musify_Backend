package in.MomenTarek.musifyapi.repository;

import in.MomenTarek.musifyapi.document.Album;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlbumRepository extends MongoRepository<Album,String> {
}
