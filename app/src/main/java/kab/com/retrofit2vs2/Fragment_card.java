package kab.com.retrofit2vs2;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Suxx on 31.05.16.
 */
public class Fragment_card extends Fragment implements MyCallback {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private RepositoryAdapter mAdapter;
    private GetData mData;
    Fragment mFragCardFull;
    FragmentTransaction mFTrans;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, null);
        createRecyclerView(view,GetData.mModel);
        mFragCardFull = new Fragment_full_card();
        return view;
    }

    public void createRecyclerView(View view,List<Airport> model){
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RepositoryAdapter(model,getActivity().getApplicationContext());
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void callbackCall(List<Airport> air) {
     Log.e("callbackCall1","callbackCall");
    }

    @Override
    public void callbackError(String err) {
        Log.e("callbackError","callbackError");
    }

    @Override
    public void callbackFragment(String sity) {
        Log.e("callbackFragment","callbackFragment "+sity);
        mData = new GetData(getActivity().getApplicationContext(),this,ServiceConnector.API_BASE_URL2);
        mData.getAuth(sity);
    }

    @Override
    public void callbackGetDataToFragment(List<Data> data) {
        Log.e("DataToFragment","callbackGetDataToFragment");
        createFragmentFullCard();
    }

    public void createFragmentFullCard() {
        mFTrans = getFragmentManager().beginTransaction();
        mFTrans.replace(R.id.fl1, mFragCardFull, "MY_FRAGMENT2");
        mFTrans.addToBackStack(null);
        mFTrans.commit();
    }

}