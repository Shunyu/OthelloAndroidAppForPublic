package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.SingleBoardDirection;

import java.util.ArrayList;
import java.util.List;

/**
 * マスの縦（もしくは横）の位置から、
 * 増加方向・減少方向にずらしていった際に、
 * オセロ盤上に収まるマスの範囲を返すクラス
 *
 * ただし、増加方向でも減少方向でもない、方向なし（NO_DIRECTION）とした際には、
 * 当初のマスの位置の情報のコピーを要素として持つリストを作成する。
 */
public class OthelloSingleIndexRangeCalculator {

    private final int LOWER_BOUND_INDEX = 0;
    private final int UPPER_BOUND_INDEX = 9;
    private final int LIST_MAX_LENGTH = 8;

    public List<Integer> getIndexRangeList(int index, SingleBoardDirection direction){

        if (index<=LOWER_BOUND_INDEX||UPPER_BOUND_INDEX<=index){
            throw new IllegalArgumentException("indexが1から8までの場合しか、このメソッドを利用できません。");
        }

        List<Integer> indexRangeList = new ArrayList<>();
        int nextIndex = index + direction.getSingleDirection();

        if (direction == SingleBoardDirection.NO_DIRECTION) {
            for (int count = 0; count < LIST_MAX_LENGTH; count++){
                indexRangeList.add(nextIndex);
            }
        } else if (direction == SingleBoardDirection.DECREMENTAL_DIRECTION) {
            for (int decrementalIndex = nextIndex; decrementalIndex >= LOWER_BOUND_INDEX; decrementalIndex--) {
                indexRangeList.add(decrementalIndex);
            }
        } else  {
            for (int incrementalIndex = nextIndex; incrementalIndex <= UPPER_BOUND_INDEX; incrementalIndex++) {
                indexRangeList.add(incrementalIndex);
            }
        }

        if (indexRangeList.size() > LIST_MAX_LENGTH) {
            throw new RuntimeException("返却するListが想定外の長さなので、動作を停止します。");
        }

        return indexRangeList;
    }
}
