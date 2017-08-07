package ducpm.framgia.com.beemusic.data.model;

import java.util.List;

/**
 * Created by ducpm on 06/08/17.
 */

public class Artist extends BaseModel {
    private String mArtistName;
    private List<Song> mSongs;

    public Artist(String artistName, List<Song> songs) {
        mArtistName = artistName;
        mSongs = songs;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public void setArtistName(String artistName) {
        mArtistName = artistName;
    }

    public List<Song> getSongs() {
        return mSongs;
    }

    public void setSongs(List<Song> songs) {
        mSongs = songs;
    }
}
