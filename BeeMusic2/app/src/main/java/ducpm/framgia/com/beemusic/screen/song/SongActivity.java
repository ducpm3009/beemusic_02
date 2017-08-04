package ducpm.framgia.com.beemusic.screen.song;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import ducpm.framgia.com.beemusic.R;
import ducpm.framgia.com.beemusic.data.model.Song;
import ducpm.framgia.com.beemusic.data.source.SongRepository;
import ducpm.framgia.com.beemusic.data.source.local.SongLocalDataSource;
import ducpm.framgia.com.beemusic.databinding.ActivitySongBinding;
import ducpm.framgia.com.beemusic.screen.BaseActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * Song Screen.
 */
public class SongActivity extends BaseActivity {

    private SongContract.ViewModel mViewModel;
    private static final String TAG = "TAG";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySongBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_song);

        List<Song> songList = new ArrayList<>();
        SongAdapter songAdapter = new SongAdapter(this, songList);
        mViewModel = new SongViewModel(songAdapter, this);

        SongContract.Presenter presenter = new SongPresenter(mViewModel,
                new SongRepository(new SongLocalDataSource(getContentResolver())));
        mViewModel.setPresenter(presenter);
        binding.setViewModel((SongViewModel) mViewModel);
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    public void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
