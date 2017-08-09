package ducpm.framgia.com.beemusic.screen.song;

import ducpm.framgia.com.beemusic.data.model.Song;
import ducpm.framgia.com.beemusic.data.source.SongRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/**
 * Listens to user actions from the UI ({@link SongActivity}), retrieves the data and updates
 * the UI as required.
 */
final class SongPresenter implements SongContract.Presenter {

    private final SongContract.ViewModel mViewModel;
    private SongRepository mSongRepository;
    private CompositeDisposable mCompositeDisposable;

    public SongPresenter(SongContract.ViewModel viewModel, SongRepository songRepository) {

        mViewModel = viewModel;
        mSongRepository = songRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onStart() {
        Disposable disposable = mSongRepository.getAllSong()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Song>>() {
                    @Override
                    public void onNext(@NonNull List<Song> songs) {
                        mViewModel.onGetSongSuccess(songs);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mViewModel.onGetSongFailed();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void onSongClicked(Song song) {

    }

    @Override
    public void onFavoriteButtonClicked(Song song, boolean isFavorite) {
        song.setFavorite(!isFavorite);
    }
}
