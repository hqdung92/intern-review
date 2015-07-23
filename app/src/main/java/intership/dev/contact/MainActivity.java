package intership.dev.contact;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Contacts> mArrayList = new ArrayList<Contacts>();
    private ListView mList;
    private ContactsAdapter mContactsAdapter;
    private ProgressDialog mProgressDialog;

    String[] name = new String[]{
            "Title 1", "Title 2", "Title 3", "Title 4",
            "Title 5", "Title 6", "Title 7", "Title 8",
            "Title 9", "Title 10", "Title 11", "Title 12",
            "Title 13", "Title 14", "Title 15", "Title 16",
            "Title 17", "Title 18", "Title 19", "Title 20"
    };
    String[] decription = new String[]{"Title 11", "Title 22", "Title 33", "Title 44",
            "Title 55", "Title 66", "Title 77", "Title 88",
            "Title 99", "Title 100", "Title 111", "Title 122",
            "Title 133", "Title 144", "Title 155", "Title 166",
            "Title 177", "Title 188", "Title 199", "Title 200"};
    int[] avata = new int[]{
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

        getDataContacts();

        mList = (ListView) findViewById(R.id.lvContact);

        mContactsAdapter = new ContactsAdapter(this, R.layout.item_list_contacts, mArrayList);

        mList.setAdapter(mContactsAdapter);

        mList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (i == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                        && (mList.getLastVisiblePosition() - mList.getHeaderViewsCount() -
                        mList.getFooterViewsCount()) >= (mContactsAdapter.getCount() - 1)) {
                    new LoadDataTask().execute();
                    mContactsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
            }
        });
    }

    private void getDataContacts() {
        for (int i = 0; i < name.length; i++) {
            Contacts contact = new Contacts(name[i], decription[i], avata[i]);
            contact.setmName(name[i]);
            contact.setmDecription(decription[i]);
            contact.setmAvata(avata[i]);
            mArrayList.add(contact);
        }
    }

    private class LoadDataTask extends AsyncTask<Void, Void, ArrayList<Contacts>> {

        @Override
        protected ArrayList<Contacts> doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setMessage("Loading data...");
            mProgressDialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<Contacts> contact) {
            super.onPostExecute(contact);
            mProgressDialog.dismiss();
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
