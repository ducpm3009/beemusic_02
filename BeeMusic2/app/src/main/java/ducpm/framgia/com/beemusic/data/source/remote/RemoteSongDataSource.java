package ducpm.framgia.com.beemusic.data.source.remote;

import ducpm.framgia.com.beemusic.data.model.Song;
import ducpm.framgia.com.beemusic.data.source.SongDataSource;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by ducpm on 30/07/17.
 */

public class RemoteSongDataSource implements SongDataSource{

    @Override
    public void favSong(boolean isFavorite) {

    }

    @Override
    public void findSongByID(int id) {

    }

    @Override
    public Observable<List<Song>> getAllSong() {
        return null;
    }
}
