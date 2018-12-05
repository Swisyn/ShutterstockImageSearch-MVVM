package com.cuneytayyildiz.shutterstockassignment.di.modules

import com.cuneytayyildiz.shutterstockassignment.ShutterstockApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton



@Module
class AppModule(val app: ShutterstockApp) {

    @Provides
    @Singleton
    fun provideApplication(): ShutterstockApp = app


}