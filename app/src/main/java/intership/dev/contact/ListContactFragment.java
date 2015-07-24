package intership.dev.contact;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by ubuntu on 24/07/2015.
 */
public class ListContactFragment extends Fragment {
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_contact, container, false);

        if (mArrayList.size() == 0) {
            addData();
        }
        mLoadMoreListView = (LoadMoreListView) v.findViewById(R.id.lvContact);

        mContactsAdapter = new ContactsAdapter(getActivity(), R.layout.item_list_contacts, mArrayList);

        mLoadMoreListView.setAdapter(mContactsAdapter);

        mContactsAdapter.setOnEditClick(new ContactsAdapter.onEditClick() {
            @Override
            public void onClick(View v, final int pos) {

                FragmentManager mFragmentManager = getFragmentManager();
                FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                ContactDetailFragment mContactDetailFragment = new ContactDetailFragment(mArrayList.get(pos));
                mFragmentTransaction.replace(R.id.rlDetailFragment, mContactDetailFragment);
                mFragmentTransaction.addToBackStack("edit");
                mFragmentTransaction.commit();

                mContactDetailFragment.setOnClickSave(new ContactDetailFragment.onClickSave() {
                    @Override
                    public void onClick(Contacts contact) {
                        mArrayList.get(pos).setmName(contact.getmName());
                        mArrayList.get(pos).setmDecription(contact.getmDecription());
                        mContactsAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

        mLoadMoreListView.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new LoadDataTask().execute();
            }
        });
        return v;
    }

    public void addData() {
        for (int i = 0; i < name.length; i++) {
            Contacts item = new Contacts();
            item.setmAvata(avata[i]);
            item.setmName(name[i]);
            item.setmDecription(decription[i]);
            mArrayList.add(item);
        }
    }

    @Override
    public void onResume() {
        Log.d("xx", "LOG");
        super.onResume();
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
            addData();
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
}
