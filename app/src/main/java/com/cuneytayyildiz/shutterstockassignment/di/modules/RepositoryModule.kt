package com.cuneytayyildiz.shutterstockassignment.di.modules

import com.cuneytayyildiz.shutterstockassignment.data.api.ShutterstockService
import com.cuneytayyildiz.shutterstockassignment.data.repository.ShutterstockRepository
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideShutterstockRepository(
        compositeDisposable: CompositeDisposable,
        api: ShutterstockService
    ): ShutterstockRepository {
        return ShutterstockRepository(compositeDisposable, api)
    }

    @Provides
    @Singleton
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

}