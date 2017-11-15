package kstn.game.view.thang.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import kstn.game.R;
import kstn.game.logic.model.Player;

public class MutiPlayFragment extends Fragment {


    public MutiPlayFragment() {
        // Required empty public constructor
    }
    public static MutiPlayFragment newObj (Player user){
        MutiPlayFragment fragment = new MutiPlayFragment();
        Bundle bd = new Bundle();
//        bd.putSerializable("user",user);
        fragment.setArguments(bd); // luu gia tri
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_muti_play, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GridView gv = (GridView) view.findViewById(R.id.gv);
        Player user = (Player) getArguments().getSerializable("user");
        ArrayList<Player> data = new ArrayList<>();
        data.add(user);
        // Player user2= new Player("thang",R.drawable.index3);
        // Player user3 = new Player("Quý",R.drawable.index4);
        // data.add(user2);
        // data.add(user3);
        // UserAdapter adapter = new UserAdapter(data,getActivity());
        // gv.setAdapter(adapter);
    }
}
