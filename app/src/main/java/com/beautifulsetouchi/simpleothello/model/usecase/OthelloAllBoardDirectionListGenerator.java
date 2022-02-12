package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.SingleBoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * オセロ盤面における、上下左右および斜めの全8方向の一覧を生成するクラス
 */
public class OthelloAllBoardDirectionListGenerator {

    List<BoardDirection> allBoardDirectionList;

    public OthelloAllBoardDirectionListGenerator() {

        allBoardDirectionList = new ArrayList<>();

        List<SingleBoardDirection> verticalDirectionList = new ArrayList<>(
                Arrays.asList(
                        SingleBoardDirection.DECREMENTAL_DIRECTION,
                        SingleBoardDirection.NO_DIRECTION,
                        SingleBoardDirection.INCREMENTAL_DIRECTION
                )
        );

        List<SingleBoardDirection> horizontalDirectionList = new ArrayList<>(
                Arrays.asList(
                        SingleBoardDirection.DECREMENTAL_DIRECTION,
                        SingleBoardDirection.NO_DIRECTION,
                        SingleBoardDirection.INCREMENTAL_DIRECTION
                )
        );

        for (SingleBoardDirection verticalDirection : verticalDirectionList) {
            for (SingleBoardDirection horizontalDirection : horizontalDirectionList){

                if (verticalDirection == SingleBoardDirection.NO_DIRECTION && horizontalDirection == SingleBoardDirection.NO_DIRECTION) {
                    continue;
                }

                BoardDirection boardDirection = new BoardDirection(
                        verticalDirection,
                        horizontalDirection
                );

                allBoardDirectionList.add(boardDirection);

            }
        }

    }

    public List<BoardDirection> getAllBoardDirectionList() {
        return allBoardDirectionList;
    }

}
