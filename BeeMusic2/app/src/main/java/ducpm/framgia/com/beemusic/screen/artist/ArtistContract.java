package ducpm.framgia.com.beemusic.screen.artist;

import ducpm.framgia.com.beemusic.screen.BasePresenter;
import ducpm.framgia.com.beemusic.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ArtistContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
