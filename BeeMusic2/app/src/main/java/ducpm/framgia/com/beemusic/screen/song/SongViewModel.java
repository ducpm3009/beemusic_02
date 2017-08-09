package ducpm.framgia.com.beemusic.screen.song;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import ducpm.framgia.com.beemusic.data.model.Song;
import ducpm.framgia.com.beemusic.screen.BaseRecyclerViewAdapter;
import java.util.List;

/**
 * Exposes the data to be used in the Song screen.
 */

public class SongViewModel extends BaseObservable implements SongContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Song> {
    private static final String TAG = "SongViewModel";
    private SongContract.Presenter mPresenter;
    private SongAdapter mAdapter;
    private Context mContext;

    public SongViewModel(SongAdapter adapter, Context context) {

        mAdapter = adapter;
        mContext = context;
        mAdapter.setSongClickListener(this);
        mAdapter.setSongViewModel(this);
    }

    @Bindable
    public SongAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(SongContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemRecyclerViewClick(Song item) {
    }

    @Override
    public void onGetSongSuccess(List<Song> data) {
        mAdapter.setSongs(data);
    }

    @Override
    public void onGetSongFailed() {
    }

    @Override
    public void onAddToFavoriteSuccess(Song song, boolean isFavorite) {
        mPresenter.onFavoriteButtonClicked(song, !isFavorite);
    }

    @Override
    public void onAddToFavoriteFailed() {

    }
}
