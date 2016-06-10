package kab.com.retrofit2vs2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Suxx on 24.05.16.
 */
public class RepositoryAdapter  extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder>  {
    private List<Airport> mDataSet;
    MyCallback mCallback;
    Context mContext;

    public void setOnItemClickListener(MyCallback listener){
        this.mCallback = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder   {
        public TextView mTextView;
        public View contentLayout;
        public TextView mTextView2;
        public TextView mTextView3;

        public ViewHolder(View  v) {
            super(v);
            contentLayout = itemView.findViewById(R.id.layout_content);
            mTextView = (TextView) v.findViewById(R.id.text_Airport_name);
            mTextView2 = (TextView) itemView.findViewById(R.id.text_IATA);
            mTextView3 = (TextView) itemView.findViewById(R.id.text_name);
        }
    }

    public RepositoryAdapter(List<Airport> dataset,Context context) {
        mDataSet = dataset;
        mContext = context;
    }

    @Override
    public RepositoryAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent,int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_repo, parent, false);
        final ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTextView.setText(mContext.getResources().getString(R.string.airportName, mDataSet.get(position).getAirportName()));
        holder.mTextView2.setText(mContext.getResources().getString(R.string.iata, mDataSet.get(position).getIata()));
        holder.mTextView3.setText(mContext.getResources().getString(R.string.place, mDataSet.get(position).getName()));

        holder.contentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext().getApplicationContext(),
                        v.getContext().getApplicationContext().getResources().getString(R.string.txtLoadDataToast)
                        + mDataSet.get(position).getIata(),Toast.LENGTH_SHORT).show();
                mCallback.callbackFragment(mDataSet.get(position).getIata());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
