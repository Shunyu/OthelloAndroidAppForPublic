package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

/**
 * 特定のマスで、特定の方向を考えた際に、
 * 相手Playerの石が何個連続しているかを確認した後、
 * 連続した相手Playerの石の先には、自分の石があるか確認し、
 * 自分の石が存在した場合には、反転可能な石の個数を返す。
 * 自分の石が存在しなかった場合には、反転可能な石の個数は0個。
 */
public class OthelloTurnOverStoneCounter {

    OthelloOpponentPlayerStoneCounter othelloOpponentPlayerStoneCounter;
    OthelloPlayerChecker othelloPlayerChecker;

    public OthelloTurnOverStoneCounter(
            OthelloOpponentPlayerStoneCounter othelloOpponentPlayerStoneCounter,
            OthelloPlayerChecker othelloPlayerChecker
    ) {
        this.othelloOpponentPlayerStoneCounter = othelloOpponentPlayerStoneCounter;
        this.othelloPlayerChecker = othelloPlayerChecker;
    }

    public int countTurnOverStones(UpdatableBoardIndex boardIndex, BoardDirection boardDirection){

        int opponentPlayerStoneNum = othelloOpponentPlayerStoneCounter.countOpponentPlayerStones(boardIndex, boardDirection);

        int targetOffset = opponentPlayerStoneNum + 1;
        boolean isMyselfFlag = othelloPlayerChecker.isMyself(boardIndex, boardDirection, targetOffset);

        if (isMyselfFlag) {
            return opponentPlayerStoneNum;
        } else {
            return 0;
        }

    }
}
