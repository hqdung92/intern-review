package intership.dev.contact;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ArrayList<Contacts> mArrayList = new ArrayList<Contacts>();
    private LoadMoreListView mLoadMoreListView;
    private ContactsAdapter mContactsAdapter;

    public static final String[] name = new String[]{
            "Title 1", "Title 2", "Title 3", "Title 4",
            "Title 5", "Title 6", "Title 7", "Title 8",
            "Title 9", "Title 10", "Title 11", "Title 12",
            "Title 13", "Title 14", "Title 15", "Title 16",
            "Title 17", "Title 18", "Title 19", "Title 20"
    };
    public static final String[] decription = new String[]{
            "Title 11", "Title 22", "Title 33", "Title 44",
            "Title 55", "Title 66", "Title 77", "Title 88",
            "Title 99", "Title 100", "Title 111", "Title 122",
            "Title 133", "Title 144", "Title 155", "Title 166",
            "Title 177", "Title 188", "Title 199", "Title 200"};
    public static final int[] avata = new int[]{
            R.drawable.img_avata1, R.drawable.img_avata2, R.drawable.img_avata3, R.drawable.img_avata1,
            R.drawable.img_avata2, R.drawable.img_avata3, R.drawable.img_avata1, R.drawable.img_avata2,
            R.drawable.img_avata2, R.drawable.img_avata3, R.drawable.img_avata1, R.drawable.img_avata2,
            R.drawable.img_avata3, R.drawable.img_avata1, R.drawable.img_avata2, R.drawable.img_avata3,
            R.drawable.img_avata1, R.drawable.img_avata2, R.drawable.img_avata3, R.drawable.img_avata2
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < name.length; i++) {
            Contacts item = new Contacts(name[i], decription[i], avata[i]);
            mArrayList.add(item);
        }

        mLoadMoreListView = (LoadMoreListView) findViewById(R.id.lvContact);

        mContactsAdapter = new ContactsAdapter(this, R.layout.item_list_contacts, mArrayList);

        mLoadMoreListView.setAdapter(mContactsAdapter);

        mLoadMoreListView.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new LoadDataTask().execute();
            }
        });
    }

    private class LoadDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            if (isCancelled()) {
                return null;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            for (int i = 0; i < name.length; i++) {
                Contacts item = new Contacts(name[i], decription[i], avata[i]);
                mArrayList.add(item);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadMoreListView.onLoadMoreComplete();
            mContactsAdapter.notifyDataSetChanged();
            Log.d("xxx", "onLoadMore");
            super.onPostExecute(result);
        }

        @Override
        protected void onCancelled() {
            mLoadMoreListView.onLoadMoreComplete();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Contacts contact = (Contacts) data.getSerializableExtra(ContactFragment.EXTRA_CONTACT);
            int position = data.getIntExtra("position", -1);
            mArrayList.set(position, contact);
            mContactsAdapter.notifyDataSetChanged();
        }
    }
}
