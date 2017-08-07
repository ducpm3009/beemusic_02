package ducpm.framgia.com.beemusic.screen.artist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import ducpm.framgia.com.beemusic.R;
import ducpm.framgia.com.beemusic.databinding.ActivityArtistBinding;
import ducpm.framgia.com.beemusic.screen.BaseActivity;

/**
 * Artist Screen.
 */
public class ArtistActivity extends BaseActivity {

    private ArtistContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ArtistViewModel();

        ArtistContract.Presenter presenter = new ArtistPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        ActivityArtistBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_artist);
        binding.setViewModel((ArtistViewModel) mViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
