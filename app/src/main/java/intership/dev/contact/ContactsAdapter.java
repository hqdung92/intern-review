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

            mViewHolder.img_avata = (ImageView) convertView
                    .findViewById(R.id.img_avata);

            mViewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);

            convertView.setTag(mViewHolder);
        } else {

            mViewHolder = (ViewHolder) convertView.getTag();
        }
        Contacts contact = mList.get(position);
        mViewHolder.tv_name.setText(contact.getmName());
        mViewHolder.img_avata.setImageResource(contact.getmAvata());

        return convertView;
    }

    static class ViewHolder {
        TextView tv_name;
        ImageView img_avata;
    }
}
