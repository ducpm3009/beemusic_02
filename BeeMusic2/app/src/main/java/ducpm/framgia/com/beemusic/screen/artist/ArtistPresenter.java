package ducpm.framgia.com.beemusic.screen.artist;

import ducpm.framgia.com.beemusic.data.model.Artist;
import ducpm.framgia.com.beemusic.data.source.ArtistRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/**
 * Listens to user actions from the UI ({@link ArtistActivity}), retrieves the data and updates
 * the UI as required.
 */
final class ArtistPresenter implements ArtistContract.Presenter {
    private static final String TAG = ArtistPresenter.class.getName();
    private final ArtistContract.ViewModel mViewModel;
    private ArtistRepository mRepository;
    private CompositeDisposable mCompositeDisposable;

    public ArtistPresenter(ArtistRepository repository, ArtistContract.ViewModel viewModel) {
        mRepository = repository;
        mViewModel = viewModel;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onStart() {
        Disposable disposable = mRepository.getAllArtist()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Artist>>() {
                    @Override
                    public void onNext(@NonNull List<Artist> artists) {
                        mViewModel.onGetArtistSuccess(artists);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mViewModel.onGetArtistFailed();
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
    public void onArtistClicked(Artist artist) {

    }
}
