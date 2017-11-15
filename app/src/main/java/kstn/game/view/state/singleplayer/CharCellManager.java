package kstn.game.view.state.singleplayer;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import kstn.game.R;
import kstn.game.view.state.ViewStateManager;

public class CharCellManager {
    private ViewStateManager stateManager;
    ArrayList<TextView> charCells = new ArrayList<>();

    public CharCellManager(ViewStateManager stateManager) {
        this.stateManager = stateManager;
    }

    public void onViewCreated(View view) {
        charCells.add((TextView) view.findViewById(R.id.txt0));
        charCells.add((TextView) view.findViewById(R.id.txt1));
        charCells.add((TextView) view.findViewById(R.id.txt2));
        charCells.add((TextView) view.findViewById(R.id.txt3));
        charCells.add((TextView) view.findViewById(R.id.txt4));
        charCells.add((TextView) view.findViewById(R.id.txt5));
        charCells.add((TextView) view.findViewById(R.id.txt6));
        charCells.add((TextView) view.findViewById(R.id.txt7));
        charCells.add((TextView) view.findViewById(R.id.txt8));
        charCells.add((TextView) view.findViewById(R.id.txt9));
        charCells.add((TextView) view.findViewById(R.id.txt10));
        charCells.add((TextView) view.findViewById(R.id.txt11));
        charCells.add((TextView) view.findViewById(R.id.txt12));
        charCells.add((TextView) view.findViewById(R.id.txt13));
        charCells.add((TextView) view.findViewById(R.id.txt14));
        charCells.add((TextView) view.findViewById(R.id.txt15));
        charCells.add((TextView) view.findViewById(R.id.txt16));
        charCells.add((TextView) view.findViewById(R.id.txt17));
        charCells.add((TextView) view.findViewById(R.id.txt18));
        charCells.add((TextView) view.findViewById(R.id.txt19));
        charCells.add((TextView) view.findViewById(R.id.txt20));
        charCells.add((TextView) view.findViewById(R.id.txt21));
        charCells.add((TextView) view.findViewById(R.id.txt22));
        charCells.add((TextView) view.findViewById(R.id.txt23));
        charCells.add((TextView) view.findViewById(R.id.txt24));
        charCells.add((TextView) view.findViewById(R.id.txt25));
        charCells.add((TextView) view.findViewById(R.id.txt26));
    }

    public void entry() {

    }

    public void exit() {

    }
}
