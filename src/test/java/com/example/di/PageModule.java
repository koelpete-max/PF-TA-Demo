package com.example.di;

import com.example.core.TestContext;
import com.example.pages.demo.home.StartPage;
import dagger.Provides;
import dagger.Module;


@Module
public class PageModule {

    @Provides
    public StartPage provideHomePage(TestContext context) {
        return new StartPage(context.getPage());
    }

}