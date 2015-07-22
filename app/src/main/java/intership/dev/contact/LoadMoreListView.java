package intership.dev.contact;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

/**
 * Created by ubuntu on 22/07/2015.
 */
public class LoadMoreListView extends ListView implements OnScrollListener {

    private static final String TAG = "LoadMoreListView";
    private OnScrollListener mOnScrollListener;
    private LayoutInflater mInflater;
    private RelativeLayout mFooterView;
    private ProgressBar mProgressBarLoading;
    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean mIsLoadingMore = false;
    private int mCurrentScrollState;

    public LoadMoreListView(Context context) {
        super(context);
        init(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public void init(Context context) {
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mFooterView = (RelativeLayout) mInflater.inflate(
                R.layout.item_list_loadmore, this, false);
        mProgressBarLoading = (ProgressBar) mFooterView
                .findViewById(R.id.progressBarLoading);
        addFooterView(mFooterView);
        super.setOnScrollListener(this);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
    }

    /**
     * Set the listener that will receive notifications every time the list
     * scrolls.
     *
     * @param l The scroll listener.
     */
    @Override
    public void setOnScrollListener(AbsListView.OnScrollListener l) {
        mOnScrollListener = l;
    }

    /**
     * Register a callback to be invoked when this list reaches the end (last
     * item be visible)
     *
     * @param onLoadMoreListener The callback to run.
     */
    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        mOnLoadMoreListener = onLoadMoreListener;
    }

    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        if (mOnScrollListener != null) {
            mOnScrollListener.onScroll(view, firstVisibleItem,
                    visibleItemCount, totalItemCount);
        }
        if (mOnLoadMoreListener != null) {
            if (visibleItemCount == totalItemCount) {
                mProgressBarLoading.setVisibility(View.GONE);
                return;
            }
            boolean loadMore = firstVisibleItem + visibleItemCount >= totalItemCount;
            if (!mIsLoadingMore && loadMore
                    && mCurrentScrollState != SCROLL_STATE_IDLE) {
                mProgressBarLoading.setVisibility(View.VISIBLE);
                mIsLoadingMore = true;
                onLoadMore();
            }
        }

    }

    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
            view.invalidateViews();
        }
        mCurrentScrollState = scrollState;
        if (mOnScrollListener != null) {
            mOnScrollListener.onScrollStateChanged(view, scrollState);
        }

    }

    public void onLoadMore() {
        Log.d(TAG, "onLoadMore");
        if (mOnLoadMoreListener != null) {
            mOnLoadMoreListener.onLoadMore();
        }
    }

    public void onLoadMoreComplete() {
        mIsLoadingMore = false;
        mProgressBarLoading.setVisibility(View.GONE);
    }

    public interface OnLoadMoreListener {
        public void onLoadMore();
    }

}
