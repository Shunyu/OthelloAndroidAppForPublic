package com.beautifulsetouchi.simpleothello.di;

import com.beautifulsetouchi.simpleothello.model.service.LoginUserAiOthelloApiService;

import dagger.Component;

/**
 * loginUserAiOthelloApiServiceインスタンスに、依存性の注入を行うためのインターフェース
 */
@Component(modules={LoginUserApiModule.class})
public interface LoginUserApiComponent {
    void inject(LoginUserAiOthelloApiService loginUserAiOthelloApiService);
}
