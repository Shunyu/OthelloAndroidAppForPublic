package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

import java.util.ArrayList;
import java.util.List;

/**
 * 外周の32マスを除く、オセロ盤面64マスの一覧を生成するクラス
 */
public class OthelloAllUpdatableBoardIndexListGenerator {

    List<UpdatableBoardIndex> allUpdatableBoardIndexList;
    
    public OthelloAllUpdatableBoardIndexListGenerator() {

        allUpdatableBoardIndexList = new ArrayList<>();

        for (int verticalIndex = 1; verticalIndex <= 8; verticalIndex++) {
            for (int horizontalIndex = 1; horizontalIndex <= 8; horizontalIndex++) {
                UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(verticalIndex, horizontalIndex);
                allUpdatableBoardIndexList.add(boardIndex);
            }
        }

    }

    public List<UpdatableBoardIndex> getAllUpdatableBoardIndexList() {
        return allUpdatableBoardIndexList;
    }
}
