package intership.dev.contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends ArrayAdapter<Contacts> {
    Context mContext;
    ArrayList<Contacts> mList = new ArrayList<Contacts>();

    public ContactsAdapter(Context context, int resource, List<Contacts> objects) {
        super(context, resource, objects);
        // TODO Auto-generated constructor stub

        this.mContext = context;
        this.mList = new ArrayList<Contacts>(objects);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Contacts getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

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
        Contacts contact = mList.get(position);
        mViewHolder.tvName.setText(contact.getmName());
        mViewHolder.imgAvata.setImageResource(contact.getmAvata());

        return convertView;
    }

    static class ViewHolder {
        TextView tvName;
        ImageView imgAvata, imgEdit, imgDelete;
    }
}
