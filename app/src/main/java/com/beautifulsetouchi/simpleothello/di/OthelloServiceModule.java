package com.beautifulsetouchi.simpleothello.di;

import com.beautifulsetouchi.simpleothello.di.scope.ViewModelScope;
import com.beautifulsetouchi.simpleothello.model.usecase.BoardIndexCastor;
import com.beautifulsetouchi.simpleothello.utilities.MinListLengthCalculator;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloAllBoardDirectionListGenerator;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloAllUpdatableBoardIndexListGenerator;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloAnyDirectionTurnOverStoneExistenceChecker;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoard;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardAllDirectionTurnOverExecutor;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardGameOverStatusUpdater;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardIndexAdder;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardIndexHorizontalRangeGeneratorChecker;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardIndexRangeCalculator;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardIndexRangeGenerator;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardIndexRangeGeneratorChecker;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardIndexVerticalRangeGeneratorChecker;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardLegalMoveChecker;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardPlayerStatusUpdater;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardSpecificDirectionTurnOverExecutor;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardSpecificIndexLegalMoveChecker;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardSpecificIndexListTurnOverExecutor;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardSpecificIndexTurnOverExecutor;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStatus;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardStatusObserver;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardStoneCounter;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardStonePutablePositionChecker;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardStoneStatusUpdater;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloGameResult;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloGameResultUpdater;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloGameUpdater;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloHorizontalSingleIndexRangeCalculator;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloNextMoveCalculator;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloOpponentPlayerStoneCounter;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloOpponentPlayerStoneSequenceInformationGenerator;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloPlayerChecker;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloRule;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloService;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloSingleIndexRangeCalculator;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloSpecificDirectionTurnOverStoneExistenceChecker;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloTaskManager;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloTurnOverStoneCounter;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloTurnOverStonesRule;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloVerticalSingleIndexRangeCalculator;

import dagger.Module;
import dagger.Provides;

/**
 * 依存性の注入で利用するOthelloServiceについて、
 * いかにOthelloServiceインスタンスを構築するかを示したクラス
 * OthelloServiceでは、人どうしが対戦するオセロにて利用する、
 * オセロの盤面の更新に関わるロジック、および実際の盤面などのゲーム状況に関する情報を保有している。
 * OthelloServiceに関わるクラスは、ユニットテストのコードも作成済。
 */
@Module
public class OthelloServiceModule {

    @Provides
    @ViewModelScope
    public OthelloBoard provideOthelloBoard() {
        return new OthelloBoard();
    }

    @Provides
    @ViewModelScope
    public OthelloBoardStatus provideOthelloBoardStatus() {
        return new OthelloBoardStatus();
    }

    @Provides
    @ViewModelScope
    public OthelloGameResult provideOthelloGameResult() {
        return new OthelloGameResult();
    }

    @Provides
    @ViewModelScope
    public OthelloSingleIndexRangeCalculator provideOthelloSingleIndexRangeCalculator() {
        return new OthelloSingleIndexRangeCalculator();
    }

    @Provides
    @ViewModelScope
    public OthelloVerticalSingleIndexRangeCalculator provideOthelloVerticalSingleIndexRangeCalculator(
            OthelloSingleIndexRangeCalculator othelloSingleIndexRangeCalculator
    ) {
        return new OthelloVerticalSingleIndexRangeCalculator(othelloSingleIndexRangeCalculator);
    }

    @Provides
    @ViewModelScope
    public OthelloHorizontalSingleIndexRangeCalculator provideOthelloHorizontalSingleIndexRangeCalculator(
            OthelloSingleIndexRangeCalculator othelloSingleIndexRangeCalculator
    ) {
        return new OthelloHorizontalSingleIndexRangeCalculator(othelloSingleIndexRangeCalculator);
    }


    @Provides
    @ViewModelScope
    public MinListLengthCalculator provideMinListLengthCalculator() {
        return new MinListLengthCalculator();
    }

    @Provides
    @ViewModelScope
    public OthelloBoardIndexRangeCalculator provideOthelloBoardIndexRangeCalculator(
            OthelloVerticalSingleIndexRangeCalculator othelloVerticalSingleIndexRangeCalculator,
            OthelloHorizontalSingleIndexRangeCalculator othelloHorizontalSingleIndexRangeCalculator,
            MinListLengthCalculator minListLengthCalculator
    ) {
        return new OthelloBoardIndexRangeCalculator(
                othelloVerticalSingleIndexRangeCalculator,
                othelloHorizontalSingleIndexRangeCalculator,
                minListLengthCalculator
        );
    }

    @Provides
    @ViewModelScope
    public OthelloOpponentPlayerStoneSequenceInformationGenerator provideOthelloOpponentPlayerStoneSequenceInformationGenerator(
            OthelloBoard othelloBoard,
            OthelloBoardStatus othelloBoardStatus
    ){
        return new OthelloOpponentPlayerStoneSequenceInformationGenerator(
                othelloBoard,
                othelloBoardStatus
        );
    }

    @Provides
    @ViewModelScope
    public OthelloOpponentPlayerStoneCounter provideOthelloOpponentPlayerStoneCounter(
            OthelloBoardIndexRangeCalculator othelloBoardIndexRangeCalculator,
            OthelloOpponentPlayerStoneSequenceInformationGenerator othelloOpponentPlayerStoneSequenceInformationGenerator
    ){
        return new OthelloOpponentPlayerStoneCounter(
                othelloBoardIndexRangeCalculator,
                othelloOpponentPlayerStoneSequenceInformationGenerator
        );
    }

    @Provides
    @ViewModelScope
    public OthelloBoardIndexAdder provideOthelloBoardIndexAdder() {
        return new OthelloBoardIndexAdder();
    }

    @Provides
    @ViewModelScope
    public OthelloPlayerChecker provideOthelloPlayerChecker(
            OthelloBoard othelloBoard,
            OthelloBoardStatus othelloBoardStatus,
            OthelloBoardIndexAdder othelloBoardIndexAdder
    ){
        return new OthelloPlayerChecker(
                othelloBoard,
                othelloBoardStatus,
                othelloBoardIndexAdder
        );
    }

    @Provides
    @ViewModelScope
    public OthelloTurnOverStoneCounter provideOthelloTurnOverStoneCounter(
            OthelloOpponentPlayerStoneCounter othelloOpponentPlayerStoneCounter,
            OthelloPlayerChecker othelloPlayerChecker
    ){
        return new OthelloTurnOverStoneCounter(othelloOpponentPlayerStoneCounter, othelloPlayerChecker);
    }

    @Provides
    @ViewModelScope
    public OthelloAllBoardDirectionListGenerator provideOthelloAllBoardDirectionListGenerator(){
        return new OthelloAllBoardDirectionListGenerator();
    }

    @Provides
    @ViewModelScope
    public OthelloAllUpdatableBoardIndexListGenerator provideOthelloAllUpdatableBoardIndexListGenerator() {
        return new OthelloAllUpdatableBoardIndexListGenerator();
    }

    @Provides
    @ViewModelScope
    public OthelloBoardSpecificIndexTurnOverExecutor provideOthelloBoardSpecificIndexTurnOverExecutor(
            OthelloBoard othelloBoard,
            OthelloBoardStatus othelloBoardStatus
    ) {
        return new OthelloBoardSpecificIndexTurnOverExecutor(
                othelloBoard,
                othelloBoardStatus
        );
    }

    @Provides
    @ViewModelScope
    public OthelloBoardSpecificIndexListTurnOverExecutor provideOthelloBoardSpecificIndexListTurnOverExecutor(
            OthelloBoard othelloBoard,
            OthelloBoardStatus othelloBoardStatus
    ) {
        return new OthelloBoardSpecificIndexListTurnOverExecutor(
                othelloBoard,
                othelloBoardStatus
        );
    }

    @Provides
    @ViewModelScope
    public BoardIndexCastor provideBoardIndexCastor() {
        return new BoardIndexCastor();
    }

    @Provides
    @ViewModelScope
    public OthelloBoardIndexVerticalRangeGeneratorChecker provideOthelloBoardIndexVerticalRangeGeneratorChecker() {
        return new OthelloBoardIndexVerticalRangeGeneratorChecker();
    }

    @Provides
    @ViewModelScope
    public OthelloBoardIndexHorizontalRangeGeneratorChecker provideOthelloBoardIndexHorizontalRangeGeneratorChecker() {
        return new OthelloBoardIndexHorizontalRangeGeneratorChecker();
    }

    @Provides
    @ViewModelScope
    public OthelloBoardIndexRangeGeneratorChecker provideOthelloBoardIndexRanegeGeneratorChecker(
            OthelloBoardIndexVerticalRangeGeneratorChecker othelloBoardIndexVerticalRangeGeneratorChecker,
            OthelloBoardIndexHorizontalRangeGeneratorChecker othelloBoardIndexHorizontalRangeGeneratorChecker
    ) {
        return new OthelloBoardIndexRangeGeneratorChecker(
                othelloBoardIndexVerticalRangeGeneratorChecker,
                othelloBoardIndexHorizontalRangeGeneratorChecker
        );
    }

    @Provides
    @ViewModelScope
    public OthelloBoardIndexRangeGenerator provideOthelloBoardIndexRangeGenerator(
            OthelloBoardIndexAdder othelloBoardIndexAdder,
            BoardIndexCastor boardIndexCastor,
            OthelloBoardIndexRangeGeneratorChecker othelloBoardIndexRangeGeneratorChecker
    ) {
        return new OthelloBoardIndexRangeGenerator(
                othelloBoardIndexAdder,
                boardIndexCastor,
                othelloBoardIndexRangeGeneratorChecker
        );
    }

    @Provides
    @ViewModelScope
    public OthelloBoardSpecificDirectionTurnOverExecutor provideOthelloBoardSpecificDirectionTurnOverExecutor(
            OthelloTurnOverStoneCounter othelloTurnOverStoneCounter,
            OthelloBoardIndexRangeGenerator othelloBoardIndexRangeGenerator,
            OthelloBoardSpecificIndexListTurnOverExecutor othelloBoardSpecificIndexListTurnOverExecutor
    ) {
        return new OthelloBoardSpecificDirectionTurnOverExecutor(
                othelloTurnOverStoneCounter,
                othelloBoardIndexRangeGenerator,
                othelloBoardSpecificIndexListTurnOverExecutor
        );
    }

    @Provides
    @ViewModelScope
    public OthelloBoardAllDirectionTurnOverExecutor provideOthelloBoardAllDirectionTurnOverExecutor(
            OthelloAllBoardDirectionListGenerator othelloAllBoardDirectionListGenerator,
            OthelloBoardSpecificDirectionTurnOverExecutor othelloBoardSpecificDirectionTurnOverExecutor
    ) {
        return new OthelloBoardAllDirectionTurnOverExecutor(
                othelloAllBoardDirectionListGenerator,
                othelloBoardSpecificDirectionTurnOverExecutor
        );
    }

    @Provides
    @ViewModelScope
    public OthelloTurnOverStonesRule provideOthelloTurnOverStonesRule(
            OthelloBoardAllDirectionTurnOverExecutor othelloBoardAllDirectionTurnOverExecutor,
            OthelloBoardSpecificIndexTurnOverExecutor othelloBoardSpecificIndexTurnOverExecutor
    ) {
        return new OthelloTurnOverStonesRule(
                othelloBoardAllDirectionTurnOverExecutor,
                othelloBoardSpecificIndexTurnOverExecutor
        );
    }

    @Provides
    @ViewModelScope
    public OthelloBoardStonePutablePositionChecker provideOthelloBoardStonePutablePositionChecker(
            OthelloBoard othelloBoard
    ) {
        return new OthelloBoardStonePutablePositionChecker(
                othelloBoard
        );
    }

    @Provides
    @ViewModelScope
    public OthelloSpecificDirectionTurnOverStoneExistenceChecker provideOthelloSpecificDirectionTurnOverStoneExistenceChecker(
            OthelloTurnOverStoneCounter othelloTurnOverStoneCounter
    ) {
        return new OthelloSpecificDirectionTurnOverStoneExistenceChecker(
                othelloTurnOverStoneCounter
        );
    }

    @Provides
    @ViewModelScope
    public OthelloAnyDirectionTurnOverStoneExistenceChecker provideOthelloAnyDirectionTurnOverStoneExistenceChecker(
            OthelloAllBoardDirectionListGenerator othelloAllBoardDirectionListGenerator,
            OthelloSpecificDirectionTurnOverStoneExistenceChecker othelloSpecificDirectionTurnOverStoneExistenceChecker
    ) {
        return new OthelloAnyDirectionTurnOverStoneExistenceChecker(
                othelloAllBoardDirectionListGenerator,
                othelloSpecificDirectionTurnOverStoneExistenceChecker
        );
    }

    @Provides
    @ViewModelScope
    public OthelloBoardSpecificIndexLegalMoveChecker provideOthelloBoardSpecificIndexLegalMoveChecker(
            OthelloBoardStonePutablePositionChecker othelloBoardStonePutablePositionChecker,
            OthelloAnyDirectionTurnOverStoneExistenceChecker othelloAnyDirectionTurnOverStoneExistenceChecker
    ) {
        return new OthelloBoardSpecificIndexLegalMoveChecker(
                othelloBoardStonePutablePositionChecker,
                othelloAnyDirectionTurnOverStoneExistenceChecker
        );
    }

    @Provides
    @ViewModelScope
    public OthelloBoardLegalMoveChecker provideOthelloBoardLegalMoveChecker(
            OthelloAllUpdatableBoardIndexListGenerator othelloAllUpdatableBoardIndexListGenerator,
            OthelloBoardSpecificIndexLegalMoveChecker othelloBoardSpecificIndexLegalMoveChecker

    ) {
        return new OthelloBoardLegalMoveChecker(
                othelloAllUpdatableBoardIndexListGenerator,
                othelloBoardSpecificIndexLegalMoveChecker
        );
    }

    @Provides
    @ViewModelScope
    public OthelloRule provideOthelloRule(
            OthelloTurnOverStonesRule othelloTurnOverStonesRule,
            OthelloBoardSpecificIndexLegalMoveChecker othelloBoardSpecificIndexLegalMoveChecker,
            OthelloBoardLegalMoveChecker othelloBoardLegalMoveChecker
    ){
        return new OthelloRule(
                othelloTurnOverStonesRule,
                othelloBoardSpecificIndexLegalMoveChecker,
                othelloBoardLegalMoveChecker
        );
    }

    @Provides
    @ViewModelScope
    public OthelloNextMoveCalculator provideOthelloNextMoveCalculator(
            OthelloBoardStatus othelloBoardStatus,
            OthelloRule othelloRule,
            OthelloAllUpdatableBoardIndexListGenerator othelloAllUpdatableBoardIndexListGenerator
    ){
        return new OthelloNextMoveCalculator(
                othelloBoardStatus,
                othelloRule,
                othelloAllUpdatableBoardIndexListGenerator
        );
    }

    @Provides
    @ViewModelScope
    public OthelloBoardStoneCounter provideOthelloBoardStoneCounter(
            OthelloBoard othelloBoard,
            OthelloAllUpdatableBoardIndexListGenerator othelloAllUpdatableBoardIndexListGenerator
    ) {
        return new OthelloBoardStoneCounter(
                othelloBoard,
                othelloAllUpdatableBoardIndexListGenerator
        );
    }

    @Provides
    @ViewModelScope
    public OthelloBoardStoneStatusUpdater provideOthelloBoardStoneStatusUpdater(
            OthelloBoardStoneCounter othelloBoardStoneCounter,
            OthelloBoardStatus othelloBoardStatus
    ) {
        return new OthelloBoardStoneStatusUpdater(
                othelloBoardStoneCounter,
                othelloBoardStatus
        );
    }

    @Provides
    @ViewModelScope
    public OthelloBoardPlayerStatusUpdater provideOthelloBoardPlayerStatusUpdater(
            OthelloBoardStatus othelloBoardStatus
    ) {
        return new OthelloBoardPlayerStatusUpdater(othelloBoardStatus);
    }

    @Provides
    @ViewModelScope
    public OthelloBoardGameOverStatusUpdater provideOthelloBoardGameOverStatusUpdater(
            OthelloBoardStatus othelloBoardStatus
    ) {
        return new OthelloBoardGameOverStatusUpdater(othelloBoardStatus);
    }

    @Provides
    @ViewModelScope
    public OthelloGameResultUpdater provideOthelloGameResultUpdater(
            OthelloBoardStatus othelloBoardStatus,
            OthelloGameResult othelloGameResult
    ) {
        return new OthelloGameResultUpdater(
                othelloBoardStatus,
                othelloGameResult
        );
    }

    @Provides
    @ViewModelScope
    public OthelloBoardStatusObserver provideOthelloBoardStatusObserver(
            OthelloBoardStoneStatusUpdater othelloBoardStoneStatusUpdater,
            OthelloBoardPlayerStatusUpdater othelloBoardPlayerStatusUpdater,
            OthelloBoardGameOverStatusUpdater othelloBoardGameOverStatusUpdater,
            OthelloGameResultUpdater othelloGameResultUpdater
    ) {
        return new OthelloBoardStatusObserver(
                othelloBoardStoneStatusUpdater,
                othelloBoardPlayerStatusUpdater,
                othelloBoardGameOverStatusUpdater,
                othelloGameResultUpdater
        );
    }

    @Provides
    @ViewModelScope
    public OthelloGameUpdater provideOthelloGameUpdater(
            OthelloRule othelloRule,
            OthelloBoardStatusObserver othelloBoardStatusObserver
    ) {
        return new OthelloGameUpdater(othelloRule, othelloBoardStatusObserver);
    }

    @Provides
    @ViewModelScope
    public OthelloTaskManager provideOthelloTaskManager(
            OthelloRule othelloRule,
            OthelloGameUpdater othelloGameUpdater
    ){
        return new OthelloTaskManager(othelloRule, othelloGameUpdater);
    }

    @Provides
    @ViewModelScope
    public OthelloService provideOthelloService(
            OthelloBoard othelloBoard,
            OthelloBoardStatus othelloBoardStatus,
            OthelloGameResult othelloGameResult,
            OthelloNextMoveCalculator othelloNextMoveCalculator,
            OthelloTaskManager othelloTaskManager
    ) {
        OthelloService othelloService = new OthelloService(
                othelloBoard,
                othelloBoardStatus,
                othelloGameResult,
                othelloNextMoveCalculator,
                othelloTaskManager
        );

        return othelloService;
    }
}
