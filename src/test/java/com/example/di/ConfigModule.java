package com.example.di;

import com.example.utils.EnvConfig;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class ConfigModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    String provideBaseUrl() {
        return EnvConfig.resolveBaseUrl();
    }
}