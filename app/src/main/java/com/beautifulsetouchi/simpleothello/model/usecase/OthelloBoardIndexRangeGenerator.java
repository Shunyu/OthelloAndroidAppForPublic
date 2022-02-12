package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

import java.util.ArrayList;
import java.util.List;

/**
 * 指定のマスから指定の方向を確認した際に、
 * 指定の個数（startOffset）だけ離れた場所から、指定の個数（endOffset）だけ離れた場所までのマス一覧を
 * 取得するクラス
 */
public class OthelloBoardIndexRangeGenerator {

    OthelloBoardIndexAdder othelloBoardIndexAdder;
    BoardIndexCastor boardIndexCastor;
    OthelloBoardIndexRangeGeneratorChecker othelloBoardIndexRangeGeneratorChecker;

    public OthelloBoardIndexRangeGenerator(
            OthelloBoardIndexAdder othelloBoardIndexAdder,
            BoardIndexCastor boardIndexCastor,
            OthelloBoardIndexRangeGeneratorChecker othelloBoardIndexRangeGeneratorChecker
    ) {
        this.othelloBoardIndexAdder = othelloBoardIndexAdder;
        this.boardIndexCastor = boardIndexCastor;
        this.othelloBoardIndexRangeGeneratorChecker = othelloBoardIndexRangeGeneratorChecker;
    }

    public List<UpdatableBoardIndex> generateBoardIndexRangeList(
            UpdatableBoardIndex boardIndex,
            BoardDirection boardDirection,
            int startOffset,
            int endOffset
    ){
        if (startOffset > endOffset) {
            throw new IllegalArgumentException("startOffset<=endOffsetでなければなりません。");
        }

        if (startOffset <= 0 || 9 <= startOffset) {
            throw new IllegalArgumentException("startOffsetは1以上8以下でなければなりません。");
        }

        if (endOffset <= 0 || 9 <= endOffset) {
            throw new IllegalArgumentException("endOffsetは1以上8以下でなければなりません。");
        }

        boolean updatableFlagAtStartOffset = othelloBoardIndexRangeGeneratorChecker.isUpdatableBoardIndex(boardIndex, boardDirection, startOffset);
        if (!updatableFlagAtStartOffset) {
            throw new IllegalArgumentException("startOffsetでは、updatableではありません");
        }

        boolean updatableFlagAtEndOffset = othelloBoardIndexRangeGeneratorChecker.isUpdatableBoardIndex(boardIndex, boardDirection, endOffset);
        if(!updatableFlagAtEndOffset) {
            throw new IllegalArgumentException("endOffsetでは、updatableではありません");
        }

        List<UpdatableBoardIndex> boardIndexRangeList = new ArrayList<>();
        for (int targetOffset = startOffset; targetOffset <= endOffset; targetOffset++){

            BoardIndex targetBoardIndex = othelloBoardIndexAdder.addBoardIndex(boardIndex, boardDirection, targetOffset);

            UpdatableBoardIndex targetUpdatableBoardIndex = boardIndexCastor.castToUpdatableBoardIndex(targetBoardIndex);

            boardIndexRangeList.add(targetUpdatableBoardIndex);
        }
        return boardIndexRangeList;
    }
}
