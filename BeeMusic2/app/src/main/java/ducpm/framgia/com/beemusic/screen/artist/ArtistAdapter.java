package ducpm.framgia.com.beemusic.screen.artist;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import ducpm.framgia.com.beemusic.R;
import ducpm.framgia.com.beemusic.data.model.Artist;
import ducpm.framgia.com.beemusic.databinding.ItemArtistBinding;
import ducpm.framgia.com.beemusic.screen.BaseRecyclerViewAdapter;
import java.util.List;

/**
 * Created by ducpm on 07/08/17.
 */

public class ArtistAdapter extends BaseRecyclerViewAdapter<ArtistAdapter.ViewHolder> {
    private List<Artist> mArtists;
    private ArtistViewModel mViewModel;
    private OnRecyclerViewItemClickListener<Artist> mArtistClickListener;

    protected ArtistAdapter(@NonNull Context context, List<Artist> artists) {
        super(context);
        mArtists = artists;
    }

    public void setArtists(List<Artist> artists) {
        mArtists = artists;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemArtistBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.item_artist, parent, false);
        return new ViewHolder(binding, mArtistClickListener, mViewModel);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mArtists.get(position));
    }

    void setArtistClickListener(OnRecyclerViewItemClickListener<Artist> songClickListener) {
        mArtistClickListener = songClickListener;
    }

    @Override
    public int getItemCount() {
        return mArtists == null ? 0 : mArtists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemArtistBinding mBinding;
        private OnRecyclerViewItemClickListener<Artist> mArtistClickListener;
        private ArtistViewModel mArtistViewModel;

        public ViewHolder(ItemArtistBinding binding,
                OnRecyclerViewItemClickListener<Artist> artistClickListener,
                ArtistViewModel artistViewModel) {
            super(binding.getRoot());
            mBinding = binding;
            mArtistClickListener = artistClickListener;

            mArtistViewModel = artistViewModel;
        }

        void bind(Artist artist) {
            mBinding.setViewModel(
                    new ItemArtistViewModel(artist, mArtistClickListener, mArtistViewModel));
            mBinding.executePendingBindings();
        }
    }
}

