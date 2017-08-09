package ducpm.framgia.com.beemusic.screen.artist;

import ducpm.framgia.com.beemusic.data.model.Artist;
import ducpm.framgia.com.beemusic.screen.BasePresenter;
import ducpm.framgia.com.beemusic.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ArtistContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetArtistSuccess(List<Artist> data);

        void onGetArtistFailed();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void onArtistClicked(Artist artist);
    }
}
