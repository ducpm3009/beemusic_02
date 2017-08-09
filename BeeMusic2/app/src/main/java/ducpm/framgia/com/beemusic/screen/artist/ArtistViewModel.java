package ducpm.framgia.com.beemusic.screen.artist;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import ducpm.framgia.com.beemusic.data.model.Artist;
import ducpm.framgia.com.beemusic.screen.BaseRecyclerViewAdapter;
import java.util.List;

/**
 * Exposes the data to be used in the Artist screen.
 */

public class ArtistViewModel extends BaseObservable implements ArtistContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Artist> {

    private Context mContext;
    private ArtistAdapter mAdapter;
    private ArtistContract.Presenter mPresenter;

    public ArtistViewModel(ArtistAdapter adapter, Context context) {
        mAdapter = adapter;
        mContext = context;
        mAdapter.setArtistClickListener(this);
        mAdapter.setViewModel(this);
    }

    @Bindable
    public ArtistAdapter getAdapter() {
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
    public void setPresenter(ArtistContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetArtistSuccess(List<Artist> data) {
        mAdapter.setArtists(data);
    }

    @Override
    public void onGetArtistFailed() {
        // TODO: 09/08/17  
    }

    @Override
    public void onItemRecyclerViewClick(Artist item) {
        // TODO: 09/08/17
    }
}
