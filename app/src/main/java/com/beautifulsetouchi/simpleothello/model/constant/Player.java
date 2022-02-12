package com.beautifulsetouchi.simpleothello.model.constant;

/**
 * 現在の手番であるPlayerがどちらであるかを表す列挙型
 */
public enum Player {

    BLACK(1),
    WHITE(2);

    private int player;

    Player(int player) {
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }

}
