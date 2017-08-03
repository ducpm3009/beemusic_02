package ducpm.framgia.com.beemusic.screen.main;

import android.view.View;

/**
 * This specifies the contract between the view and the presenter.
 */
interface MainContract {
    /**
     * View.
     */
    interface ViewModel {
        void onStart();

        void onStop();

        void setPresenter(MainContract.Presenter presenter);

        void onAllSongClicked(View view);

        void onArtistClicked(View view);

        void onMyPlaylistClicked(View view);
    }

    /**
     * Presenter.
     */
    interface Presenter {
        void onStart();

        void onStop();

        void showAllSong();

        void showAllArtist();

        void showMyPlaylist();
    }
}
