package ducpm.framgia.com.beemusic.screen.song;

import android.databinding.BaseObservable;
import android.graphics.drawable.Drawable;
import android.view.View;

import ducpm.framgia.com.beemusic.data.model.Song;
import ducpm.framgia.com.beemusic.screen.BaseRecyclerViewAdapter;

/**
 * Created by ducpm on 30/07/17.
 */

public class ItemSongViewModel extends BaseObservable {
    private Song mSong;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Song> mSongClickListener;
    private SongViewModel mSongViewModel;

    public ItemSongViewModel(Song song,
            BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Song> songClickListener,
            SongViewModel songViewModel) {
        mSong = song;
        mSongClickListener = songClickListener;
        mSongViewModel = songViewModel;
    }

    public String getSongTitle() {
        return mSong.getSongTitle();
    }

    public String getSongArtist() {
        return mSong.getSongArtist();
    }

    public String getSongAlbum() {
        return mSong.getSongAlbum();
    }

    public int getSongDuration() {
        return mSong.getSongDuration();
    }

    public boolean getSongFav() {
        return mSong.isFavorite();
    }

    public Drawable getSongArtwork() {
        return mSong.getSongArtwork();
    }

    public void onSongClicked(View view) {
        if (mSongClickListener == null) {
            return;
        }
        mSongClickListener.onItemRecyclerViewClick(mSong);
    }
}
