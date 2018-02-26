package droidmentor.PoliticTeens_Client.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import co.ceryle.segmentedbutton.SegmentedButtonGroup;
import droidmentor.PoliticTeens_Client.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyJungdangFragment extends Fragment {

    Button makingJundang;
    FragmentManager manager;
    FragmentTransaction transaction;
    JundangFormFragment fragment;

    public MyJungdangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_myjundangs, container, false);

        SegmentedButtonGroup myjundang_buttonGroup = (SegmentedButtonGroup) view.findViewById(R.id.myjundang_buttonGroup);
        /*myjundang_buttonGroup.setOnClickListener( new SegmentedButtonGroup.OnClickedButtonPosition(){
            @Override
            public  void onClickedButtonPosition(){

            }
        });*/
        manager=getFragmentManager();
        transaction=manager.beginTransaction();

       // SegmentedButtonGroup myjundang_buttonGroup = (SegmentedButtonGroup) view.findViewById(R.id.myjundang_buttonGroup);
        /*myjundang_buttonGroup.setOnClickListener( new SegmentedButtonGroup.OnClickedButtonPosition(){
            @Override
            public  void onClickedButtonPosition(){

            }
        });*/
        fragment=new JundangFormFragment();
        makingJundang=(Button)view.findViewById(R.id.makingJundang);
        makingJundang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.fragment_myjundang,fragment);
                transaction.commit();
            }
        });

        return view;

    }

}
