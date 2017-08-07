package ducpm.framgia.com.beemusic.screen.artist;

/**
 * Exposes the data to be used in the Artist screen.
 */

public class ArtistViewModel implements ArtistContract.ViewModel {

    private ArtistContract.Presenter mPresenter;

    public ArtistViewModel() {
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
}
