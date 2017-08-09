package ducpm.framgia.com.beemusic.data.source.local;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import ducpm.framgia.com.beemusic.data.model.Song;
import ducpm.framgia.com.beemusic.data.source.SongDataSource;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ducpm on 01/08/17.
 */

public class SongLocalDataSource implements SongDataSource {

    private ContentResolver mContentResolver;

    public SongLocalDataSource(ContentResolver contentResolver) {
        mContentResolver = contentResolver;
    }

    private String createSongArtworkPathFromAlbumID(String currentAlbumID) {
        String result = null;
        Uri songUri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
        Cursor cursor = mContentResolver.query(songUri,
                new String[] { MediaStore.Audio.Albums._ID, MediaStore.Audio.Albums.ALBUM_ART },
                MediaStore.Audio.Albums._ID + "=?", new String[] { String.valueOf(currentAlbumID) },
                null);
        if (cursor.moveToFirst()) {
            result = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART));
        }
        cursor.close();
        return result;
    }

    @Override
    public void favSong(boolean isFavorite) {

    }

    @Override
    public void findSongByID(int id) {

    }

    @Override
    public Observable<List<Song>> getAllSong() {
        return Observable.create(new ObservableOnSubscribe<List<Song>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Song>> e) throws Exception {
                List<Song> songList = new ArrayList<>();

                String[] projection = new String[] {
                        MediaStore.Audio.Media.ALBUM_ID, MediaStore.Audio.Media.TITLE,
                        MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.ALBUM,
                        MediaStore.Audio.Media.DURATION, MediaStore.Audio.Media._ID
                };
                String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";
                Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                Cursor songCursor =
                        mContentResolver.query(songUri, projection, selection, null, null, null);

                if (songCursor != null && songCursor.moveToFirst()) {

                    int songAlbumID = songCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
                    int songId = songCursor.getColumnIndex(MediaStore.Audio.Media._ID);
                    int songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                    int songArtist = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
                    int songAlbum = songCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
                    int songDuration = songCursor.getColumnIndex(MediaStore.Audio.Media.DURATION);

                    do {

                        String currentAlbumID = songCursor.getString(songAlbumID);
                        int currentID = songCursor.getInt(songId);
                        String currentTitle = songCursor.getString(songTitle);
                        String currentArtist = songCursor.getString(songArtist);
                        String currentAlbum = songCursor.getString(songAlbum);
                        int currentDuration = songCursor.getInt(songDuration);
                        String currentAlbumArtPath =
                                createSongArtworkPathFromAlbumID(currentAlbumID);
                        Uri currentUri = ContentUris.withAppendedId(
                                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, songCursor.getInt(
                                        songCursor.getColumnIndex(MediaStore.Audio.Media._ID)));
                        songList.add(
                                new Song(currentTitle, currentArtist, currentAlbum, currentDuration,
                                        currentAlbumArtPath, currentID, currentUri));
                    } while (songCursor.moveToNext());
                    e.onNext(songList);
                } else {
                    e.onError(new NullPointerException());
                }
                songCursor.close();
                e.onComplete();
            }
        });
    }
}
