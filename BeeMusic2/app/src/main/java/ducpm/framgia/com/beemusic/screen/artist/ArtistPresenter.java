package ducpm.framgia.com.beemusic.screen.artist;

/**
 * Listens to user actions from the UI ({@link ArtistActivity}), retrieves the data and updates
 * the UI as required.
 */
final class ArtistPresenter implements ArtistContract.Presenter {
    private static final String TAG = ArtistPresenter.class.getName();

    private final ArtistContract.ViewModel mViewModel;

    public ArtistPresenter(ArtistContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
