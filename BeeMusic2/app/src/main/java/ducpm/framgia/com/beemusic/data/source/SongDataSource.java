package ducpm.framgia.com.beemusic.data.source;

import ducpm.framgia.com.beemusic.data.model.Song;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by ducpm on 30/07/17.
 */

public interface SongDataSource {

    void favSong(boolean isFavorite);

    void findSongByID(int id);

    Observable<List<Song>> getAllSong();
}
