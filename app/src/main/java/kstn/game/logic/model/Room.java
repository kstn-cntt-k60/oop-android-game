package kstn.game.logic.model;

import java.util.ArrayList;
import java.util.List;

import kstn.game.logic.state.LogicStateManager;
import kstn.game.logic.state.multiplayer.Player;

public class Room {
    private LogicStateManager stateManager;
    private String roomName;
    private List<Player> playerList;
    private Player finalPlayer;
    private int currentLevel;

    public Room(LogicStateManager stateManager, String roomName) {
        this.stateManager = stateManager;
        this.roomName = roomName;
        playerList = new ArrayList<>();
        currentLevel = 0;
    }

    public LogicStateManager getStateManager() {
        return stateManager;
    }

    public String getRoomName() {
        return roomName;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public Player getFinalPlayer() {
        return finalPlayer;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void exit() {
    }

    private void requestedJoinRoom(int requestId, String playerName, int avatarId) {
    }

    private void acceptJoinRoom(int requestId, int playerId,
                                String playerName, int avatarId)
    {}

    private void nextLevel() {
        currentLevel++;
    }

    public void startPlaying() {
    }

    public void endPlaying() {

    }
}
