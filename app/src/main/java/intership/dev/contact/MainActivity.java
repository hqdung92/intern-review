package intership.dev.contact;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements ContactsAdapter.OnClickContacts {

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
                    ;
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
            Contacts contact = new Contacts(name[i], avata[i]);
            contact.setmName(name[i]);
            contact.setmAvata(avata[i]);
            mArrayList.add(contact);
        }
    }

    @Override
    public void onClickEdit(int position) {

        ContactFragment contactFragment = new ContactFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ContactFragment.EXTRA_CONTACT, mArrayList.get(position));
        contactFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.rlContactFragment, contactFragment);
        fragmentTransaction.addToBackStack("ContactFragment");
        fragmentTransaction.commit();
    }

    private class LoadDataTask extends AsyncTask<Void, Void, ArrayList<Contacts>> {

        @Override
        protected ArrayList<Contacts> doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            getDataContacts();
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
}
