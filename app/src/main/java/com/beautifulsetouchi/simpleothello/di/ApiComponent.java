package com.beautifulsetouchi.simpleothello.di;

import com.beautifulsetouchi.simpleothello.model.service.AiOthelloApiService;

import dagger.Component;

/**
 * aiOthelloApiServiceインスタンスに、依存性の注入を行うためのインターフェース
 */
@Component(modules = {ApiModule.class})
public interface ApiComponent {
    void inject(AiOthelloApiService aiOthelloApiService);
}
