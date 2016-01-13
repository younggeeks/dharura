package code.bytech.com.emergency.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import code.bytech.com.emergency.R;
import code.bytech.com.emergency.adapters.PolisiAdapter;
import code.bytech.com.emergency.adapters.ZimamotoAdapter;
import code.bytech.com.emergency.model.hospital.NavItemManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class PolisiFragment extends Fragment {
    private RecyclerView recyclerView;
    private CardView cardView;

    public PolisiFragment() {
        // Required empty public constructor
    }

    public static PolisiFragment getInstance(){
        PolisiFragment fragment=new PolisiFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout=inflater.inflate(R.layout.fragment_polisi, container, false);

        recyclerView=(RecyclerView)layout.findViewById(R.id.recycler_zimamoto);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        PolisiAdapter adapter= new PolisiAdapter(NavItemManager.getInstance().getPolisi(),R.layout.row_item_backup,getActivity());
        recyclerView.setAdapter(adapter);
        return layout;
    }


}
