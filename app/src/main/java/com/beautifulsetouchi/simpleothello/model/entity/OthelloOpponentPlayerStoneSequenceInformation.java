package com.beautifulsetouchi.simpleothello.model.entity;

/**
 * 現在の手番であるPlayerの相手Playerの石の数や、
 * 手番のPlayerの石が存在するかの情報を格納するクラス
 *
 * 特定の方向に対して、相手Playerの石の数が何個連続しており、
 * また、その連続した相手石の後に、自分の石が存在しているかの情報を格納している
 */
public class OthelloOpponentPlayerStoneSequenceInformation {

    private int opponentPlayerStoneNum;
    private boolean nonOpponentPlayerExistenceFlag;

    public OthelloOpponentPlayerStoneSequenceInformation() {
        this(0, false);
    }

    private OthelloOpponentPlayerStoneSequenceInformation(
            int opponentPlayerStoneNum,
            boolean nonOpponentPlayerExistenceFlag
    ) {
        this.opponentPlayerStoneNum = opponentPlayerStoneNum;
        this.nonOpponentPlayerExistenceFlag = nonOpponentPlayerExistenceFlag;
    }

    public void incrementOpponentPlayerStoneNum() {
        opponentPlayerStoneNum++;
    }

    public int getOpponentPlayerStoneNum() {
        return opponentPlayerStoneNum;
    }

    public void setNonOpponentPlayerExistenceFlag(boolean nonOpponentPlayerExistenceFlag) {
        this.nonOpponentPlayerExistenceFlag = nonOpponentPlayerExistenceFlag;
    }

    public void setOpponentPlayerStoneNum(int opponentPlayerStoneNum) {
        this.opponentPlayerStoneNum = opponentPlayerStoneNum;
    }

    public boolean isNonOpponentPlayerExistenceFlag() {
        return nonOpponentPlayerExistenceFlag;
    }
}
