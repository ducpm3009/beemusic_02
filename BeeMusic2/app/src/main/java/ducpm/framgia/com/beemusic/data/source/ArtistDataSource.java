package ducpm.framgia.com.beemusic.data.source;

import ducpm.framgia.com.beemusic.data.model.Artist;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by ducpm on 09/08/17.
 */

public interface ArtistDataSource {
    Observable<List<Artist>> getAllArtist();
}
