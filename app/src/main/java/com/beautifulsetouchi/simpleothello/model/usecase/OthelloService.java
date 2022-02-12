package com.beautifulsetouchi.simpleothello.model.usecase;

import android.util.Log;

import com.beautifulsetouchi.simpleothello.model.constant.Player;
import com.beautifulsetouchi.simpleothello.model.constant.Winner;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoard;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStatus;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloGameResult;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloHintBoard;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

/**
 * オセロゲームで特定のマスに石を置けるかどうか
 * オセロゲームの盤面
 * 現在の手番のPlayerがおける場所を示した盤面
 * 黒石の数、白石の数、現在のPlayer
 * ゲームオーバーであるか、ゲームを終了して最初からやりなおすか、勝者はだれであるか
 * といった、ViewModelが必要な情報を取得する際に利用するファサードクラス
 */
public class OthelloService {

    OthelloBoard othelloBoard;
    OthelloBoardStatus othelloBoardStatus;
    OthelloGameResult othelloGameResult;
    OthelloNextMoveCalculator othelloNextMoveCalculator;
    OthelloTaskManager othelloTaskManager;


    public OthelloService(
            OthelloBoard othelloBoard,
            OthelloBoardStatus othelloBoardStatus,
            OthelloGameResult othelloGameResult,
            OthelloNextMoveCalculator othelloNextMoveCalculator,
            OthelloTaskManager othelloTaskManager
    ){
        this.othelloBoard = othelloBoard;;
        this.othelloBoardStatus = othelloBoardStatus;
        this.othelloGameResult = othelloGameResult;
        this.othelloNextMoveCalculator = othelloNextMoveCalculator;
        this.othelloTaskManager = othelloTaskManager;
    }

    public boolean isGameUpdate(int tappedVerticalCounter, int tappedHorizontalCounter){

        if (tappedVerticalCounter <= 0 || 9 <= tappedVerticalCounter) {
            throw new IllegalArgumentException("tappedVerticalCounterは1以上8以下でなければなりません。");
        }
        if (tappedHorizontalCounter <= 0 || 9 <= tappedHorizontalCounter) {
            throw new IllegalArgumentException("tappedHorizontalCounterは1以上8以下でなければなりません。");
        }

        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(tappedVerticalCounter, tappedHorizontalCounter);

        return othelloTaskManager.isGameUpdate(boardIndex);
    }

    public int[][] getBoardArray(){
        return othelloBoard.getBoardArray();
    }

    public int[][] getHintArray(){

        OthelloHintBoard othelloHintBoard = othelloNextMoveCalculator.calculateOthelloHintBoard();
        int[][] hintBoardArray = othelloHintBoard.getBoardArray();

        return hintBoardArray;

    }

    public int getBlackStoneNum(){
        return othelloBoardStatus.getBlackStoneNum();
    }

    public int getWhiteStoneNum(){
        return othelloBoardStatus.getWhiteStoneNum();
    }

    public int getPlayer(){
        Player player = othelloBoardStatus.getPlayer();
        return player.getPlayer();
    }

    public boolean isGameOverFlag() {
        return othelloBoardStatus.isGameOverFlag();
    }

    public boolean isRestartFlag(){
        return othelloBoardStatus.isRestartFlag();
    }

    public Winner getWinner(){
        return othelloGameResult.getWinner();
    }

    public void restartGame(){

        Log.i("model restartGame","do!");

        othelloBoardStatus.setRestartFlag(true);

        Log.i("model restartGame","finish!");

        return;
    }

}
