package ducpm.framgia.com.beemusic.data.source.local;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import ducpm.framgia.com.beemusic.data.model.Artist;
import ducpm.framgia.com.beemusic.data.source.ArtistDataSource;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ducpm on 09/08/17.
 */

public class ArtistLocalDataSource implements ArtistDataSource {

    private ContentResolver mContentResolver;

    public ArtistLocalDataSource(ContentResolver contentResolver) {
        mContentResolver = contentResolver;
    }

    @Override
    public Observable<List<Artist>> getAllArtist() {
        return Observable.create(new ObservableOnSubscribe<List<Artist>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Artist>> e) throws Exception {
                List<Artist> artistList = new ArrayList<>();

                String[] projection = new String[] {
                        MediaStore.Audio.Artists.ARTIST, MediaStore.Audio.Artists._ID,
                        MediaStore.Audio.Artists.NUMBER_OF_TRACKS
                };
                Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                Cursor cursor = mContentResolver.query(uri, projection, null, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    int artistID = cursor.getColumnIndex(MediaStore.Audio.Artists._ID);
                    int artistName = cursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST);
                    int artistTotal =
                            cursor.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_TRACKS);
                    int artistArtwork = cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART);
                    do {
                        int currentID = cursor.getInt(artistID);
                        String currentName = cursor.getString(artistName);
                        int currentTotal = cursor.getInt(artistTotal);
                        String currentArtwork = cursor.getString(artistArtwork);
                        artistList.add(new Artist(currentName, currentID, currentTotal, currentArtwork));
                    } while (cursor.moveToNext());
                    e.onNext(artistList);
                } else {
                    e.onError(new NullPointerException());
                }
                e.onComplete();
            }
        });
    }
}
