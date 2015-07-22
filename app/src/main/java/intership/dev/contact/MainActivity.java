package intership.dev.contact;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Contacts> mArrayList = new ArrayList<Contacts>();
    private ListView mList;
    private ContactsAdapter mContactsAdapter;

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
    }

    private void getDataContacts() {
        for (int i = 0; i < 20; i++) {
            Contacts contact = new Contacts();
            contact.setmName(name[i]);
            contact.setmAvata(avata[i]);
            mArrayList.add(contact);
        }
    }
}
