package com.beautifulsetouchi.simpleothello.model.constant;

/**
 * ゲーム終了時の勝者をあらわす列挙型
 */
public enum Winner {

    BLACK(1),
    WHITE(2),
    DRAW(3);

    private int winner;

    Winner(int winner) {
        this.winner = winner;
    }

    public int getWinner() {
        return winner;
    }

}
