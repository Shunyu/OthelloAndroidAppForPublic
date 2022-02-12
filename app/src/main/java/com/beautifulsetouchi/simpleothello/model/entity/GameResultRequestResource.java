package com.beautifulsetouchi.simpleothello.model.entity;

import java.util.List;

/**
 * ログイン中、AIオセロとの対戦終了後、
 * 対戦結果を添付して、対戦成績更新APIに対してリクエストを送り、対戦成績を更新する。
 * その際には、チートを防ぐために、対戦時の盤面推移などを、同時に送付する。
 * そのリクエストの際に送信する、それらのデータを格納するクラス
 */
public class GameResultRequestResource {

    private String gameresult;
    private List<GameSituation> gameSituationList;

    public void setGameresult(String gameresult) {

        if (gameresult.equals("black") || gameresult.equals("white") || gameresult.equals("draw")) {
            this.gameresult = gameresult;
        } else {
            throw new IllegalArgumentException("gameresultが不正です。");
        }

    }

    public void setGameSituationList(List<GameSituation> gameSituationList){
        this.gameSituationList = gameSituationList;
    }

    public String getGameresult(){
        return gameresult;
    }

    public List<GameSituation> getGameSituationList() {
        return gameSituationList;
    }

}
