package ducpm.framgia.com.beemusic.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import ducpm.framgia.com.beemusic.R;
import ducpm.framgia.com.beemusic.data.model.Song;
import ducpm.framgia.com.beemusic.screen.main.MainActivity;
import java.io.IOException;
import java.util.List;

/**
 * Created by ducpm on 04/08/17.
 */

public class PlayerService extends Service
        implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener,
        MediaPlayer.OnPreparedListener {

    public static final String ACTION_PAUSE = "ducpm.framga.action.PAUSE";
    public static final String ACTION_RESUME = "ducpm.framga.action.RESUME";
    public static final String ACTION_NEXT = "ducpm.framga.action.NEXT";
    public static final String ACTION_PREV = "ducpm.framga.action.PREV";

    public static final int REQUEST_PAUSE = 101;
    public static final int REQUEST_NEXT = 102;
    public static final int REQUEST_PREV = 103;
    public static final int REQUEST_RESUME = 104;
    private static final int NOTIFICATION_ID = 11;
    private final IBinder mBinder = new PlayerBinder();
    private int mSongPos = 0;
    private MediaPlayer mMediaPlayer;
    private List<Song> mSongList;
    private boolean mIsPlaying;
    private Notification.Builder mBuilder;
    private Notification mNotification;
    private Uri mUri;
    private Song mSong;
    private ListenerDetailMusic mListenerDetailMusic;
    private NotificationManager mNotificationManager;

    public void setListenerDetailMusic(ListenerDetailMusic listenerDetailMusic) {
        mListenerDetailMusic = listenerDetailMusic;
    }

    public MediaPlayer getMediaPlayer() {
        return mMediaPlayer;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void onCreate() {
        super.onCreate();
        mMediaPlayer = new MediaPlayer();
        initMusicPlayer();
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnErrorListener(this);
    }

    public void initMusicPlayer() {
        mMediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String action = intent.getAction();
            switch (action) {
                case ACTION_PAUSE:
                    pauseSong();
                    break;
                case ACTION_NEXT:
                    nextSong();
                    break;
                case ACTION_PREV:
                    previousSong();
                    break;
                case ACTION_RESUME:
                    resumeSong();
                    break;
                default:
                    break;
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public void resumeSong() {
        mMediaPlayer.seekTo(mMediaPlayer.getCurrentPosition() == mSong.getSongDuration() ? 0
                : mMediaPlayer.getCurrentPosition());
        mMediaPlayer.start();
        mIsPlaying = true;
        sendBroadcast(new Intent(ACTION_RESUME));
        mNotificationManager.notify(NOTIFICATION_ID,
                notifyNotification(R.drawable.ic_star, ACTION_PAUSE));
    }

    private PendingIntent createPendingIntentService(String action) {
        Intent intent = new Intent(this, PlayerService.class);
        intent.setAction(action);
        return PendingIntent.getService(this, 0, intent, 0);
    }

    private PendingIntent createPendingIntentActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return PendingIntent.getActivity(this, 0, intent, 0);
    }

    private Notification notifyNotification(int drawable, String action) {
        PendingIntent notifyPendingIntent, nextPendingIntent, previousPendingIntent;
        notifyPendingIntent = createPendingIntentService(action);
        nextPendingIntent = createPendingIntentService(ACTION_NEXT);
        previousPendingIntent = createPendingIntentService(ACTION_PREV);
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_star);
        Notification notification =
                new NotificationCompat.Builder(this).setContentIntent(createPendingIntentActivity())
                        .setContentText(mSong.getSongArtist())
                        .setContentTitle(mSong.getSongTitle())
                        .setStyle(new NotificationCompat.MediaStyle())
                        .setSmallIcon(R.drawable.ic_star)
                        .setLargeIcon(icon)
                        .addAction(R.drawable.ic_star, ACTION_PREV, previousPendingIntent)
                        .addAction(drawable, ACTION_RESUME, notifyPendingIntent)
                        .addAction(R.drawable.ic_star, ACTION_NEXT, nextPendingIntent)
                        .setAutoCancel(action.equals(ACTION_RESUME))
                        .build();
        notification.flags = Notification.DEFAULT_LIGHTS | Notification.FLAG_AUTO_CANCEL;
        return notification;
    }

    public void pauseSong() {
        mMediaPlayer.pause();
        mIsPlaying = false;
        sendBroadcast(new Intent(ACTION_PAUSE));
        mNotificationManager.notify(NOTIFICATION_ID,
                notifyNotification(R.drawable.ic_star, ACTION_RESUME));
        stopForeground(false);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mMediaPlayer.stop();
        mMediaPlayer.release();
        return false;
    }

    public void nextSong() {
        playSong(mSongList.get(mSongPos + 1));
        mSongPos++;
        sendBroadcast(new Intent(ACTION_NEXT));
    }

    public void previousSong() {
        playSong(mSongList.get(mSongPos - 1));
        mSongPos--;
        sendBroadcast(new Intent(ACTION_PREV));
    }

    private void setUpAsForeground() {
        startForeground(NOTIFICATION_ID, notifyNotification(R.drawable.ic_star, ACTION_PAUSE));
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        mediaPlayer.reset();
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mMediaPlayer.start();
    }

    public void setSongList(List<Song> songList) {
        mSongList = songList;
    }

    public void setSongPos(int pos) {
        mSongPos = pos;
    }

    public Song getSong() {
        return mSong;
    }

    public void setSong(Song song) {
        mSong = song;
    }

    public void playSong(Song song) {
        mMediaPlayer.reset();
        mIsPlaying = true;
        Uri uri = song.getUri();

        try {
            mMediaPlayer.setDataSource(getApplicationContext(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.prepareAsync();
    }

    public int getDuration() {
        return mMediaPlayer.getDuration();
    }

    public boolean isPlaying() {
        return mMediaPlayer.isPlaying();
    }

    @Override
    public void onDestroy() {
        stopForeground(true);
    }

    public interface ListenerDetailMusic {
        void updateSeekBar(int pos);

        void updateDuration(int duration);

        void updateDetailMusic();
    }

    public class PlayerBinder extends Binder {
        public PlayerService getService() {
            return PlayerService.this;
        }
    }
}
