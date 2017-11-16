package kstn.game.logic.state;

import android.graphics.Bitmap;

import java.io.IOException;

import kstn.game.logic.cone.Cone;
import kstn.game.logic.event.EventData;
import kstn.game.logic.event.EventListener;
import kstn.game.logic.model.SinglePlayerManager;
import kstn.game.view.screen.ImageView;

public class LogicSinglePlayerState extends LogicGameState {
    private int score;
    private int life;
    private String question, answer;
    private EventListener overCellListener;
    private Cone cone;
    private ImageView backgroundView;
    private SinglePlayerManager playerManager;

    public LogicSinglePlayerState(LogicStateManager stateManager) {
        super(stateManager);

        Bitmap background = null;
        try {
            background = stateManager.assetManager.getBitmap("bg.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        backgroundView = new ImageView(0, 0, 2, 1.8f * 2, background);
        overCellListener = new EventListener() {
            @Override
            public void onEvent(EventData event) {
                cone.disable();
            }
        };
        cone = new Cone(stateManager.processManager, stateManager.assetManager,
                        stateManager.eventManager, stateManager.timeManager, stateManager.root);

        playerManager = new SinglePlayerManager(cone, stateManager);
    }

    @Override
    public void entry() {
        score = 0;
        life = 3;
        stateManager.root.addView(backgroundView);
        cone.entry();
        // playerManager.setQuestion();
        playerManager.entry();
    }

    @Override
    public void exit() {
        playerManager.entry();
        cone.exit();
        stateManager.root.removeView(backgroundView);
    }
}
