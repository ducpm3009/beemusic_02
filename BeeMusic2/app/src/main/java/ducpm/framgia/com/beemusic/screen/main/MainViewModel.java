package ducpm.framgia.com.beemusic.screen.main;

import android.databinding.BaseObservable;
import android.view.View;

/**
 * Exposes the data to be used in the Main screen.
 */

public class MainViewModel extends BaseObservable implements MainContract.ViewModel {

    private MainContract.Presenter mPresenter;

    public MainViewModel() {
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
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onAllSongClicked(View view) {
        mPresenter.showAllSong();
    }

    @Override
    public void onArtistClicked(View view) {
        mPresenter.showAllArtist();
    }

    @Override
    public void onMyPlaylistClicked(View view) {
        mPresenter.showMyPlaylist();
    }
}
