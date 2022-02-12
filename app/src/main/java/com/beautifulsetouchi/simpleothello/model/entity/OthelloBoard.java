package com.beautifulsetouchi.simpleothello.model.entity;

import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.BLACK;
import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.PUTABLE;
import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.UNPUTABLE;
import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.WHITE;

/**
 * オセロ盤面の64マス+外周の32マスの状況（置けない・置ける・既に黒石がある・既に白石がある）を格納するクラス
 * initializeBoardArrayでは、オセローゲーム開始時の初期化を実施する。
 */
public class OthelloBoard extends Board {

    public OthelloBoard() {

        super();
        initializeBoardArray();

    }

    private void initializeBoardArray() {

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                boardArray[i][j] = UNPUTABLE;
            }
        }

        for(int i = 1; i < 9; i++){
            for(int j = 1; j < 9; j++){
                boardArray[i][j] = PUTABLE;
            }
        }

        boardArray[4][4] = BLACK;
        boardArray[4][5] = WHITE;
        boardArray[5][4] = WHITE;
        boardArray[5][5] = BLACK;
    }

}
