package com.beautifulsetouchi.simpleothello.model.entity;

import com.beautifulsetouchi.simpleothello.model.constant.Player;

/**
 * オセロ盤面やオセロゲームの情報を格納するクラス
 */
public class OthelloBoardStatus {

    // 現在のPlayer（どちらの手番であるか）
    private Player player;

    // 黒石の数
    private int blackStoneNum;
    // 白石の数
    private int whiteStoneNum;

    // ゲームオーバーしたかどうか
    private boolean gameOverFlag;
    // オセロをリセットして、再スタートするかどうか
    private boolean restartFlag;

    public OthelloBoardStatus(){
        this(Player.BLACK, 2, 2, false, false);
    }

    private OthelloBoardStatus(
            Player player,
            int blackStoneNum,
            int whiteStoneNum,
            boolean gameOverFlag,
            boolean restartFlag
    ){

        this.player = player;

        if(blackStoneNum<=-1||65<=blackStoneNum){
            throw new IllegalArgumentException("blackStoneNumは0以上64以下でなければなりません。");
        }
        this.blackStoneNum = blackStoneNum;

        if(whiteStoneNum<=-1||65<=whiteStoneNum){
            throw new IllegalArgumentException("whiteStoneNumは0以上64以下でなければなりません。");
        }
        this.whiteStoneNum = whiteStoneNum;
        this.gameOverFlag = gameOverFlag;
        this.restartFlag = restartFlag;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setBlackStoneNum(int blackStoneNum) {
        if(blackStoneNum<=-1||65<=blackStoneNum){
            throw new IllegalArgumentException("blackStoneNumは0以上64以下でなければなりません。");
        }
        this.blackStoneNum = blackStoneNum;
    }

    public void setWhiteStoneNum(int whiteStoneNum) {
        if(whiteStoneNum<=-1||65<=whiteStoneNum){
            throw new IllegalArgumentException("whiteStoneNumは0以上64以下でなければなりません。");
        }
        this.whiteStoneNum = whiteStoneNum;
    }

    public void setOthelloBoardStoneNum(OthelloBoardStoneNum othelloBoardStoneNum) {
        this.blackStoneNum = othelloBoardStoneNum.getBlackStoneNum();
        this.whiteStoneNum = othelloBoardStoneNum.getWhiteStoneNum();
    }

    public void setGameOverFlag(boolean gameOverFlag) {
        this.gameOverFlag = gameOverFlag;
    }

    public void setRestartFlag(boolean restartFlag) {
        this.restartFlag = restartFlag;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getOpponentPlayer() {

        Player opponentPlayer;
        if (player == Player.BLACK) {
            opponentPlayer = Player.WHITE;
        } else {
            opponentPlayer = Player.BLACK;
        }

        return opponentPlayer;
    }

    public boolean isRestartFlag() {
        return restartFlag;
    }

    public boolean isGameOverFlag() {
        return gameOverFlag;
    }

    public int getBlackStoneNum() {
        return blackStoneNum;
    }

    public int getWhiteStoneNum() {
        return whiteStoneNum;
    }

}
