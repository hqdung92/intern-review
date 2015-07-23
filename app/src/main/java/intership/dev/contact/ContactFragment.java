package intership.dev.contact;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

        ImageView imgAvata = (ImageView) v.findViewById(R.id.imgAvata);
        TextView tvName = (TextView) v.findViewById(R.id.tvName);

        imgAvata.setImageResource(mContact.getmAvata());
        tvName.setText(mContact.getmName());

        return v;
    }
}
