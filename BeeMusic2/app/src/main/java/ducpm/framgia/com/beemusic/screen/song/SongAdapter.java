package ducpm.framgia.com.beemusic.screen.song;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ducpm.framgia.com.beemusic.R;
import ducpm.framgia.com.beemusic.data.model.Song;
import ducpm.framgia.com.beemusic.databinding.ItemSongBinding;
import ducpm.framgia.com.beemusic.screen.BaseRecyclerViewAdapter;

/**
 * Created by ducpm on 30/07/17.
 */

public class SongAdapter extends BaseRecyclerViewAdapter<SongAdapter.ViewHolder> {
    private List<Song> mSongs;
    private SongViewModel mSongViewModel;
    private OnRecyclerViewItemClickListener<Song> mSongClickListener;

    public SongAdapter(@NonNull Context context, List<Song> songs) {
        super(context);
        mSongs = songs;
    }

    public void setSongs(List<Song> songs) {
        mSongs = songs;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemSongBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.item_song, parent, false);
        return new ViewHolder(binding, mSongClickListener, mSongViewModel);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mSongs.get(position));
    }

    @Override
    public int getItemCount() {
        return mSongs == null ? 0 : mSongs.size();
    }

    void setSongClickListener(OnRecyclerViewItemClickListener<Song> songClickListener) {
        mSongClickListener = songClickListener;
    }

    public SongViewModel getSongViewModel() {
        return mSongViewModel;
    }

    public void setSongViewModel(SongViewModel songViewModel) {
        mSongViewModel = songViewModel;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemSongBinding mBinding;
        private OnRecyclerViewItemClickListener<Song> mSongClickListener;
        private SongViewModel mSongViewModel;

        public ViewHolder(ItemSongBinding binding,
                OnRecyclerViewItemClickListener<Song> songClickListener,
                SongViewModel songViewModel) {
            super(binding.getRoot());
            mBinding = binding;
            mSongClickListener = songClickListener;

            mSongViewModel = songViewModel;
        }

        void bind(Song song) {
            mBinding.setViewModel(new ItemSongViewModel(song, mSongClickListener, mSongViewModel));
            mBinding.executePendingBindings();
        }
    }
}
