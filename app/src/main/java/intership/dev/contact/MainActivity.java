package intership.dev.contact;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager mFragmentManager = getFragmentManager();
        ListContactFragment mListContactFragment = new ListContactFragment();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.rlListFragment, mListContactFragment);
        mFragmentTransaction.addToBackStack("main");
        mFragmentTransaction.commit();
    }
}