package in.MomenTarek.musifyapi.repository;

import in.MomenTarek.musifyapi.document.Song;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SongRepository extends MongoRepository<Song,String> {
}
