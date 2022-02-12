package com.beautifulsetouchi.simpleothello.model.entity;

import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.PUTABLE;
import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.UNPUTABLE;

/**
 * オセロ盤面の64マス+外周の32マスの状況（置けない・置ける・既に黒石がある・既に白石がある）を格納するクラス
 * initializeBoardArrayでは、ヒント盤面作成時の初期化を実施する。
 * ここで、ヒント盤面は、現在の手番のPlayerが、次の手でどこに石を置くことができるかを示した情報を格納したもの。
 */
public class OthelloHintBoard extends Board {

    public OthelloHintBoard() {

        super();
        initializeBoardArray();

    }

    private void initializeBoardArray() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boardArray[i][j] = UNPUTABLE;
            }
        }

        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                boardArray[i][j] = PUTABLE;
            }
        }

    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
