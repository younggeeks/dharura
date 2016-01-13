package code.bytech.com.emergency.fragments;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import code.bytech.com.emergency.R;
import code.bytech.com.emergency.adapters.FirstAidAdapter;
import code.bytech.com.emergency.model.hospital.NavItemManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstAidFragment extends Fragment {

    private RecyclerView recyclerView;
    public FirstAidFragment() {
        // Required empty public constructor
    }

    public static FirstAidFragment getInstance(){
        FirstAidFragment fragment=new FirstAidFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_first_aid, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.first_aid_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new FirstAidAdapter(NavItemManager.getInstance().getAid(),R.layout.first_aid_row,getActivity()));
        return  view;
    }


}
