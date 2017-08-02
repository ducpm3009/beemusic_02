package ducpm.framgia.com.beemusic.screen.main;

import android.view.View;

import ducpm.framgia.com.beemusic.screen.BasePresenter;
import ducpm.framgia.com.beemusic.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface MainContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {

        void onAllSongClicked(View view);

        void onArtistClicked(View view);

        void onMyPlaylistClicked(View view);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {

        void showAllSong();

        void showAllArtist();

        void showMyPlaylist();
    }
}
