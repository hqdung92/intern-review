package intership.dev.contact;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ubuntu on 22/07/2015.
 */
public class ContactDetailFragment extends Fragment {
    public final static String EXTRA_CONTACT = "mContact";
    private Contacts mContact;
    private int position;
    private ImageView imgAvata;
    private TextView tvName, tvSave, tvCancel;
    private EditText edtName, edtDecription;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_contact, container, false);
        mContact = (Contacts) getArguments().getSerializable(ContactDetailFragment.EXTRA_CONTACT);

        imgAvata = (ImageView) v.findViewById(R.id.imgAvata);
        tvName = (TextView) v.findViewById(R.id.tvName);
        edtName = (EditText) v.findViewById(R.id.edtName);
        edtDecription = (EditText) v.findViewById(R.id.edtDecription);
        tvSave = (TextView) v.findViewById(R.id.tvSave);
        tvCancel = (TextView) v.findViewById(R.id.tvCancel);

        imgAvata.setImageResource(mContact.getmAvata());
        tvName.setText(mContact.getmName());
        edtName.setText(mContact.getmName());
        edtDecription.setText(mContact.getmDecription());

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog mDialog = new Dialog(getActivity(), R.style.DialogTheme);
                mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                mDialog.setContentView(R.layout.dialog_delete);

                TextView tvMessenger = (TextView) mDialog.findViewById(R.id.tvMessage);
                TextView tvOk = (TextView) mDialog.findViewById(R.id.tvOk);
                TextView tvCancel = (TextView) mDialog.findViewById(R.id.tvCancel);

                tvMessenger.setText(Html.fromHtml("Are you sure you want to edit this contact?"));
                tvOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), ListContactFragment.class);
                        Bundle bundle = new Bundle();
                        position = bundle.getInt("position");
                        mContact.setmName(edtName.getText().toString());
                        mContact.setmDecription(edtDecription.getText().toString());
                        intent.putExtra("position", position);
                        intent.putExtra("mContact", mContact);
                        getActivity().setResult(Activity.RESULT_OK, intent);
                        mDialog.cancel();
                        getActivity().onBackPressed();
                    }
                });
                tvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDialog.cancel();
                    }
                });
                mDialog.show();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtName.setText(mContact.getmName());
                edtDecription.setText(mContact.getmDecription());
            }
        });
        return v;
    }
}
