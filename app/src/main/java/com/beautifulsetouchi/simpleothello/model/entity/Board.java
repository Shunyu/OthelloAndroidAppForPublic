package com.beautifulsetouchi.simpleothello.model.entity;

import com.beautifulsetouchi.simpleothello.model.constant.Player;

import java.util.Arrays;
import java.util.List;

import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.BLACK;
import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.WHITE;

/**
 * オセロ盤面の64マス+外周の32マスの状況（置けない・置ける・既に黒石がある・既に白石がある）を格納するクラス
 */
public abstract class Board {

    int[][] boardArray;

    public Board() {
        boardArray = new int[10][10];
    }

    public int[][] getBoardArray() {
        return boardArray;
    }

    /**
     * どのマス目に、どのPlayerの石を置くかの情報を与えて、マスの状況を更新する
     * @param boardIndex
     * @param player
     */
    public void setBoardArrayOneElement(UpdatableBoardIndex boardIndex, Player player) {

        int verticalIndex = boardIndex.getVerticalIndex();
        int horizontalIndex = boardIndex.getHorizontalIndex();

        int boardStatus;
        if (player == Player.BLACK) {
            boardStatus = BLACK;
        } else {
            boardStatus = WHITE;
        }
        boardArray[verticalIndex][horizontalIndex] = boardStatus;
    }

    /**
     * どのマス目に、どのPlayerの石を置くかの情報を与えて、マスの状況を更新する
     * @param boardIndex
     * @param player
     */
    public void setBoardStone(UpdatableBoardIndex boardIndex, Player player) {

        setBoardArrayOneElement(boardIndex, player);

    }

    /**
     * 複数のマス目に、どのPlayerの石を置くかの情報を与えて、マスの状況を更新する
     * @param boardIndexList
     * @param player
     */
    public void setBoardStones(List<UpdatableBoardIndex> boardIndexList, Player player){

        for (UpdatableBoardIndex boardIndex : boardIndexList) {
            setBoardArrayOneElement(boardIndex, player);
        }
    }

    /**
     * マス目の情報を与えて、マスの状況を取得する
     * @param boardIndex
     * @return
     */
    public int getBoardStone(BoardIndex boardIndex){

        int verticalIndex = boardIndex.getVerticalIndex();
        int horizontalIndex = boardIndex.getHorizontalIndex();

        return boardArray[verticalIndex][horizontalIndex];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Arrays.deepEquals(boardArray, board.boardArray);
    }

}
