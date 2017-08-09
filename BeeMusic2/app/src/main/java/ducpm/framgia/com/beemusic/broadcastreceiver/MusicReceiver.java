package ducpm.framgia.com.beemusic.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import ducpm.framgia.com.beemusic.service.PlayerService;

/**
 * Created by ducpm on 09/08/17.
 */

public class MusicReceiver extends BroadcastReceiver {
    private Listener mListener;

    public MusicReceiver(Listener listener) {
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent == null || mListener == null) return;
        switch (intent.getAction()) {
            case PlayerService.ACTION_NEXT:
                mListener.onUpdateBottomBar();
                break;
            case PlayerService.ACTION_PREV:
                mListener.onUpdateBottomBar();
                break;
            case PlayerService.ACTION_PAUSE:
                mListener.onPauseReceiver();
                break;
            case PlayerService.ACTION_RESUME:
                mListener.onPlayReceiver();
                break;
            default:
                break;
        }
    }

    public interface Listener {
        void onUpdateBottomBar();

        void onPauseReceiver();

        void onPlayReceiver();
    }
}
