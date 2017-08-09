package ducpm.framgia.com.beemusic.data.model;

/**
 * Created by ducpm on 06/08/17.
 */

public class Artist extends BaseModel {
    private String mArtistName;
    private int mID;
    private int mTotalSongs;
    private String mArtwork;

    public Artist(String artistName, int id, int totalSongs, String artwork) {
        mArtistName = artistName;
        mID = id;
        mTotalSongs = totalSongs;
        mArtwork = artwork;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public void setArtistName(String artistName) {
        mArtistName = artistName;
    }

    public int getTotalSongs() {
        return mTotalSongs;
    }

    public void setTotalSongs(int totalSongs) {
        mTotalSongs = totalSongs;
    }

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public String getArtwork() {
        return mArtwork;
    }

    public void setArtwork(String artwork) {
        mArtwork = artwork;
    }
}
