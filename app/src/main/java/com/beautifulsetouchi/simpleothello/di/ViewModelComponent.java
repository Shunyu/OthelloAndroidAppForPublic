package com.beautifulsetouchi.simpleothello.di;

import com.beautifulsetouchi.simpleothello.di.scope.ViewModelScope;
import com.beautifulsetouchi.simpleothello.viewmodel.OthelloViewModel;

import dagger.Component;

/**
 * othelloViewModelインスタンスに、依存性の注入を行うためのインターフェース
 */
@Component(modules = {OthelloServiceModule.class})
@ViewModelScope
public interface ViewModelComponent {
    void inject(OthelloViewModel othelloViewModel);
}
