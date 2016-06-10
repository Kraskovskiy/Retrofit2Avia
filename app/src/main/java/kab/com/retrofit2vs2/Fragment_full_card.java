package kab.com.retrofit2vs2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Suxx on 28.05.2016.
 */
public class Fragment_full_card extends Fragment {
    ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_full_card, null);
        createListView(view,(ArrayList<Data>) GetData.mData);
        return view;
    }

    public void createListView(View view, ArrayList<Data> data)
    {
        CustomListAdapter adapter = new CustomListAdapter(getActivity(), data);
        mListView = (ListView) view.findViewById(R.id.listView);
        mListView.setAdapter(adapter);
    }

}
