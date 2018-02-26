package droidmentor.PoliticTeens_Client.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import droidmentor.PoliticTeens_Client.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CongressFragment extends Fragment {


    public CongressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_congress, container, false);
    }

}
