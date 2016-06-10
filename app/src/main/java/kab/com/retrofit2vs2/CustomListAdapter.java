package kab.com.retrofit2vs2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Suxx on 31.05.16.
 */
public class CustomListAdapter extends BaseAdapter {

    private final Activity mContext;
    private final List<Data> mItemname;
    private static final String TAG = "CustomListAdapter";

    public CustomListAdapter(Activity context, ArrayList<Data> itemname) {
        this.mContext = context;
        this.mItemname = itemname;
    }

    @Override
    public int getCount() {
        return mItemname.size();
    }

    @Override
    public Object getItem(int i) {
        return mItemname.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mItemname.get(i).hashCode();
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=mContext.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.mylist, null, true);

        TextView txtDestination = (TextView) rowView.findViewById(R.id.txtDestination);
        TextView txtPrice = (TextView) rowView.findViewById(R.id.txtPrice);

        txtDestination.setText(mItemname.get(position).getDestination());
        txtPrice.setText(mItemname.get(position).getPrice()+"");

        return rowView;

    };
}
