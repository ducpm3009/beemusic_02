package ducpm.framgia.com.beemusic.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

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
}
