package ducpm.framgia.com.beemusic.screen;

/**
 * Created by ducpm on 30/07/17.
 */

public interface BaseViewModel<T> {
    void onStart();

    void onStop();

    void setPresenter(T presenter);
}
