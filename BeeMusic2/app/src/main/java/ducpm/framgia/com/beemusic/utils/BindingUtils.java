package ducpm.framgia.com.beemusic.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.adapters.SeekBarBindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.SeekBar;
import com.bumptech.glide.Glide;
import ducpm.framgia.com.beemusic.R;

/**
 * Created by ducpm on 31/07/17.
 */

public final class BindingUtils {

    public BindingUtils() {
    }

    @BindingAdapter({ "recyclerAdapter" })
    public static void setAdapterForRecyclerView(RecyclerView recyclerView,
            RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("android:onProgressChanged")
    public static void setListener(SeekBar view, SeekBarBindingAdapter.OnProgressChanged listener) {
        setListener(view, listener);
    }

    @BindingAdapter({ "imageResource" })
    public static void setImageDrawable(final ImageView imageView, String path) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(path)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into(imageView);
    }

    @BindingAdapter("layoutManagerGrid")
    public static void setLayoutManager(RecyclerView view,
            LayoutManagers.LayoutManagerFactory factory) {
        view.setLayoutManager(factory.create(view));
    }
}
