package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

/**
 * 実際に石を反転させる
 * 石が置けるか確認する
 * 現在の盤面で現在の手番のPlayerにとって、石を置ける場所が存在するか確認する
 * といった、オセロゲームの機能を利用する際のファサードクラス
 */
public class OthelloRule {

    OthelloTurnOverStonesRule othelloTurnOverStonesRule;
    OthelloBoardSpecificIndexLegalMoveChecker othelloBoardSpecificIndexLegalMoveChecker;
    OthelloBoardLegalMoveChecker othelloBoardLegalMoveChecker;

    public OthelloRule(
            OthelloTurnOverStonesRule othelloTurnOverStonesRule,
            OthelloBoardSpecificIndexLegalMoveChecker othelloBoardSpecificIndexLegalMoveChecker,
            OthelloBoardLegalMoveChecker othelloBoardLegalMoveChecker
    ){
        this.othelloTurnOverStonesRule = othelloTurnOverStonesRule;
        this.othelloBoardSpecificIndexLegalMoveChecker = othelloBoardSpecificIndexLegalMoveChecker;
        this.othelloBoardLegalMoveChecker = othelloBoardLegalMoveChecker;
    }

    public void turnOverStones(UpdatableBoardIndex boardIndex){

        othelloTurnOverStonesRule.turnOverStones(boardIndex);

    }
    
    public boolean isLegalMove(UpdatableBoardIndex boardIndex){

        return othelloBoardSpecificIndexLegalMoveChecker.isLegalMove(boardIndex);

    }

    public boolean legalMoveExists(){

        return othelloBoardLegalMoveChecker.legalMoveExists();
    }

}
