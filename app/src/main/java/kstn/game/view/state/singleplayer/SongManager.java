package kstn.game.view.state.singleplayer;

import android.media.MediaPlayer;
import android.util.Log;

import kstn.game.R;
import kstn.game.logic.cone.ConeEventType;
import kstn.game.logic.event.EventData;
import kstn.game.logic.event.EventListener;
import kstn.game.logic.state.LogicStateManager;
import kstn.game.view.state.ViewStateManager;

public class SongManager {
    private ViewStateManager stateManager;
    private MediaPlayer coneRotation;
    private MediaPlayer fail;
    private MediaPlayer tingTing;
    private EventListener coneAccelListener;

    public SongManager(ViewStateManager stateManager) {
        this.stateManager = stateManager;
        coneAccelListener = new EventListener() {
            @Override
            public void onEvent(EventData event) {
                startConeRotation();
                Log.i(this.toString(), "Nhac dang phat");
            }
        };
    }

    public void onViewCreated(android.view.View view) {

    }

    public void startConeRotation() {
        coneRotation = MediaPlayer.create(stateManager.activity, R.raw.nhac_hieu);
        coneRotation.start();
    }
    public void endConeRotation() {
        coneRotation.stop();
    }
    public void startTingTing() {
        tingTing = MediaPlayer.create(stateManager.activity, R.raw.tingting);
        tingTing.start();
    }
    public void startFail() {
        fail = MediaPlayer.create(stateManager.activity, R.raw.failure);
        fail.start();
    }

    public void entry() {
        stateManager.eventManager.addListener(ConeEventType.ACCELERATE, coneAccelListener);
    }
    public void exit() {
        stateManager.eventManager.removeListener(ConeEventType.ACCELERATE, coneAccelListener);
    }

}