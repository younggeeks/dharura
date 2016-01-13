package code.bytech.com.emergency.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import code.bytech.com.emergency.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class contentFragment extends Fragment {


    public contentFragment() {
        // Required empty public constructor
    }

    public static contentFragment getInstance(){
        contentFragment fragment=new contentFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_content, container, false);
    }


}
