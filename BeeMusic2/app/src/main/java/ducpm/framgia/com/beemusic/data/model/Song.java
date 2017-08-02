package ducpm.framgia.com.beemusic.data.model;

import android.graphics.drawable.Drawable;

/**
 * Created by ducpm on 30/07/17.
 */

public class Song extends BaseModel {
    private String mSongTitle;
    private String mSongArtist;
    private String mSongAlbum;
    private int mSongDuration;
    private Drawable mSongArtwork;
    private int mID;


    public Song(String songTitle, String songArtist, String songAlbum, int songDuration,
                Drawable songArtwork, int id) {
        mSongTitle = songTitle;
        mSongArtist = songArtist;
        mSongAlbum = songAlbum;
        mSongDuration = songDuration;

        mSongArtwork = songArtwork;
        mID = id;
    }

    public String getSongTitle() {
        return mSongTitle;
    }

    public void setSongTitle(String mSongTitle) {
        this.mSongTitle = mSongTitle;
    }

    public String getSongArtist() {
        return mSongArtist;
    }

    public void setSongArtist(String mSongArtist) {
        this.mSongArtist = mSongArtist;
    }

    public String getSongAlbum() {
        return mSongAlbum;
    }

    public void setSongAlbum(String mSongAlbum) {
        this.mSongAlbum = mSongAlbum;
    }

    public int getSongDuration() {
        return mSongDuration;
    }

    public void setSongDuration(int mSongDuration) {
        this.mSongDuration = mSongDuration;
    }


    public Drawable getSongArtwork() {
        return mSongArtwork;
    }

    public void setSongArtwork(Drawable songArtwork) {
        mSongArtwork = songArtwork;
    }

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }
}
