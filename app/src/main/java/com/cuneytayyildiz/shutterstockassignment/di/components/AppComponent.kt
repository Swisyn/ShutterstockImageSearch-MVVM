package com.cuneytayyildiz.shutterstockassignment.di.components

import android.app.Application
import android.content.Context
import com.cuneytayyildiz.shutterstockassignment.ShutterstockApp
import com.cuneytayyildiz.shutterstockassignment.data.repository.ShutterstockRepository
import com.cuneytayyildiz.shutterstockassignment.di.modules.AppModule
import com.cuneytayyildiz.shutterstockassignment.di.modules.NetModule
import com.cuneytayyildiz.shutterstockassignment.di.modules.RepositoryModule
import com.cuneytayyildiz.shutterstockassignment.ui.main.MainViewModel
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
        modules = arrayOf(AppModule::class, NetModule::class, RepositoryModule::class)
)
interface AppComponent {

    fun inject(viewModelModule: MainViewModel)
    fun inject(activity: Context)

    fun provideShutterstockRepository(): ShutterstockRepository

    companion object Factory{
        fun create(app: Application): AppComponent {
            val appComponent = DaggerAppComponent.builder().
                    appModule(AppModule(app as ShutterstockApp)).
                    netModule(NetModule()).
                    repositoryModule(RepositoryModule()).
                    build();
            return appComponent
        }
    }
}