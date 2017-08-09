package ducpm.framgia.com.beemusic.screen.artist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import ducpm.framgia.com.beemusic.R;
import ducpm.framgia.com.beemusic.data.model.Artist;
import ducpm.framgia.com.beemusic.data.source.ArtistRepository;
import ducpm.framgia.com.beemusic.data.source.local.ArtistLocalDataSource;
import ducpm.framgia.com.beemusic.databinding.ActivityArtistBinding;
import ducpm.framgia.com.beemusic.screen.BaseActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * Artist Screen.
 */
public class ArtistActivity extends BaseActivity {

    private ArtistContract.ViewModel mViewModel;
    private ArtistAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityArtistBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_artist);
        List<Artist> artistList = new ArrayList<>();
        ArtistAdapter adapter = new ArtistAdapter(this, artistList);
        mViewModel = new ArtistViewModel(adapter, this);
        ArtistContract.Presenter presenter = new ArtistPresenter(
                new ArtistRepository(new ArtistLocalDataSource(getContentResolver())), mViewModel);
        mViewModel.setPresenter(presenter);

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
