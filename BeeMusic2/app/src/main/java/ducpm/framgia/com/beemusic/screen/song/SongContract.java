package ducpm.framgia.com.beemusic.screen.song;

import ducpm.framgia.com.beemusic.data.model.Song;
import ducpm.framgia.com.beemusic.screen.BasePresenter;
import ducpm.framgia.com.beemusic.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface SongContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetSongSuccess(List<Song> data);

        void onGetSongFailed();

        void onAddToFavoriteSuccess(Song song, boolean isFavorite);

        void onAddToFavoriteFailed();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void onSongClicked(Song song);

        void onFavoriteButtonClicked(Song song, boolean isFavorite);
    }
}
