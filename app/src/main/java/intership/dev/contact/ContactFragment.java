package intership.dev.contact;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ubuntu on 22/07/2015.
 */
public class ContactFragment extends Fragment {

    public final static String EXTRA_CONTACT = "mContact";
    private Contacts mContact;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact, container, false);
        mContact = (Contacts) getArguments().getSerializable(ContactFragment.EXTRA_CONTACT);
        return v;
    }
}
