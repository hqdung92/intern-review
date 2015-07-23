package intership.dev.contact;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends ArrayAdapter<Contacts> {

    private Context mContext;
    private ArrayList<Contacts> mArrayList = new ArrayList<Contacts>();

    public ContactsAdapter(Context context, int resource, List<Contacts> objects) {
        super(context, resource, objects);
        // TODO Auto-generated constructor stub

        this.mContext = context;
        this.mArrayList = new ArrayList<Contacts>(objects);
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Contacts getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final ViewHolder mViewHolder;

        if (convertView == null) {
            LayoutInflater inflate = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflate.inflate(R.layout.item_list_contacts, null);

            mViewHolder = new ViewHolder();

            mViewHolder.imgAvata = (ImageView) convertView
                    .findViewById(R.id.imgAvata);

            mViewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);

            mViewHolder.imgEdit = (ImageView) convertView.findViewById(R.id.imgEdit);

            mViewHolder.imgDelete = (ImageView) convertView.findViewById(R.id.imgDelete);

            convertView.setTag(mViewHolder);
        } else {

            mViewHolder = (ViewHolder) convertView.getTag();
        }
        final Contacts contact = mArrayList.get(position);
        mViewHolder.tvName.setText(contact.getmName());
        mViewHolder.imgAvata.setImageResource(contact.getmAvata());
        mViewHolder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContactFragment mContactFragment = new ContactFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable(ContactFragment.EXTRA_CONTACT, mArrayList.get(position));
                mContactFragment.setArguments(bundle);

                FragmentManager mFragmentManager = ((Activity) mContext).getFragmentManager();
                FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.rlContactFragment, mContactFragment);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();
            }
        });

        mViewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog mDialog = new Dialog(mContext,R.style.DialogTheme);
                mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                mDialog.setContentView(R.layout.dialog_delete);

                TextView tvMessage = (TextView) mDialog.findViewById(R.id.tvMessage);
                TextView tvOk = (TextView) mDialog.findViewById(R.id.tvOk);
                TextView tvCancel = (TextView) mDialog.findViewById(R.id.tvCancel);

                tvMessage.setText(Html.fromHtml("Are you sure you want to delete " + "<b>" + contact.getmName().toString() + "</b>" + "?"));

                tvOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mArrayList.remove(position);
                        notifyDataSetChanged();
                        mDialog.cancel();
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

        return convertView;
    }

    static class ViewHolder {
        TextView tvName;
        ImageView imgAvata, imgEdit, imgDelete;
    }
}
