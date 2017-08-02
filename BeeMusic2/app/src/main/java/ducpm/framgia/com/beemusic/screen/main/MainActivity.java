package ducpm.framgia.com.beemusic.screen.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import ducpm.framgia.com.beemusic.R;
import ducpm.framgia.com.beemusic.databinding.ActivityMainBinding;
import ducpm.framgia.com.beemusic.screen.BaseActivity;


public class MainActivity extends BaseActivity {
    private MainContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new MainViewModel();

        MainContract.Presenter presenter = new MainPresenter(mViewModel, this);
        mViewModel.setPresenter(presenter);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel((MainViewModel) mViewModel);
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
