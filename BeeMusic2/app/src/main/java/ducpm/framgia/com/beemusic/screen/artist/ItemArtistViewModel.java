package ducpm.framgia.com.beemusic.screen.artist;

import android.databinding.BaseObservable;
import android.view.View;
import ducpm.framgia.com.beemusic.data.model.Artist;
import ducpm.framgia.com.beemusic.screen.BaseRecyclerViewAdapter;

/**
 * Created by ducpm on 06/08/17.
 */

public class ItemArtistViewModel extends BaseObservable {
    private Artist mArtist;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Artist> mArtistClickListener;

    public ItemArtistViewModel(Artist artist,
            BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Artist> artistClickListener,
            ArtistViewModel artistViewModel) {
        mArtist = artist;
        mArtistClickListener = artistClickListener;
    }

    public String getArtistTitle() {
        return mArtist.getArtistName();
    }

    public void onArtistClicked(View view) {
        if (mArtistClickListener == null) {
            return;
        }
        mArtistClickListener.onItemRecyclerViewClick(mArtist);
    }
}
