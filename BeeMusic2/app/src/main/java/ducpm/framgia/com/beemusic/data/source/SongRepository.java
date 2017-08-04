package ducpm.framgia.com.beemusic.data.source;

import ducpm.framgia.com.beemusic.data.model.Song;
import ducpm.framgia.com.beemusic.data.source.local.SongLocalDataSource;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by ducpm on 30/07/17.
 */

public class SongRepository implements SongDataSource {
    private SongDataSource mLocalDataSource;

    public SongRepository(SongLocalDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    @Override
    public void favSong(boolean isFavorite) {
        mLocalDataSource.favSong(isFavorite);
    }

    @Override
    public void findSongByID(int id) {
        mLocalDataSource.findSongByID(id);
    }

    @Override
    public Observable<List<Song>> getAllSong() {
        return mLocalDataSource.getAllSong();
    }
}
