package ducpm.framgia.com.beemusic.screen.main;

import android.content.Context;
import android.content.Intent;
import ducpm.framgia.com.beemusic.screen.song.SongActivity;

/**
 * Listens to user actions from the UI ({@link MainActivity}), retrieves the data and updates
 * the UI as required.
 */
final class MainPresenter implements MainContract.Presenter {
    private static final String TAG = MainPresenter.class.getName();

    private final MainContract.ViewModel mViewModel;
    private Context mContext;

    MainPresenter(MainContract.ViewModel viewModel, Context context) {
        mViewModel = viewModel;
        mContext = context;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void showAllSong() {
        Intent intent = new Intent(mContext, SongActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public void showAllArtist() {

    }

    @Override
    public void showMyPlaylist() {
    }
}
