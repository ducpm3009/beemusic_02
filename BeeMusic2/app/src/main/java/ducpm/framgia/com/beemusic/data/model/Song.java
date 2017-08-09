package ducpm.framgia.com.beemusic.data.model;

import android.net.Uri;

/**
 * Created by ducpm on 30/07/17.
 */

public class Song extends BaseModel {
    private String mSongTitle;
    private String mSongArtist;
    private String mSongAlbum;
    private int mSongDuration;
    private String mSongArtwork;
    private int mID;
    private Uri mUri;

    public Song(String songTitle, String songArtist, String songAlbum, int songDuration,
            String songArtwork, int id, Uri uri) {
        mSongTitle = songTitle;
        mSongArtist = songArtist;
        mSongAlbum = songAlbum;
        mSongDuration = songDuration;
        mSongArtwork = songArtwork;
        mID = id;
        mUri = uri;
    }

    public String getSongTitle() {
        return mSongTitle;
    }

    public void setSongTitle(String songTitle) {
        mSongTitle = songTitle;
    }

    public String getSongArtist() {
        return mSongArtist;
    }

    public void setSongArtist(String songArtist) {
        mSongArtist = songArtist;
    }

    public String getSongAlbum() {
        return mSongAlbum;
    }

    public void setSongAlbum(String songAlbum) {
        mSongAlbum = songAlbum;
    }

    public int getSongDuration() {
        return mSongDuration;
    }

    public void setSongDuration(int songDuration) {
        mSongDuration = songDuration;
    }

    public String getSongArtwork() {
        return mSongArtwork;
    }

    public void setSongArtwork(String songArtwork) {
        mSongArtwork = songArtwork;
    }

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public Uri getUri() {
        return mUri;
    }

    public void setUri(Uri uri) {
        mUri = uri;
    }
}
