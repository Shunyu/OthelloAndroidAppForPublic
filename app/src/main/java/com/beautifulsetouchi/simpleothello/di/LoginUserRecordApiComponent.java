package com.beautifulsetouchi.simpleothello.di;

import com.beautifulsetouchi.simpleothello.model.service.LoginUserRecordApiService;

import dagger.Component;

/**
 * loginUserRecordApiServiceインスタンスに、依存性の注入を行うためのインターフェース
 */
@Component(modules={LoginUserRecordApiModule.class})
public interface LoginUserRecordApiComponent {
    void inject(LoginUserRecordApiService loginUserRecordApiService);
}
