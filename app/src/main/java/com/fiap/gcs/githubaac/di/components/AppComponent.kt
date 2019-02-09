package com.fiap.gcs.githubaac.di.components

import android.app.Application
import com.fiap.gcs.githubaac.MyApp
import com.fiap.gcs.githubaac.di.modules.*
import dagger.*
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidInjectionModule::class,
            ActivityModule::class,
            FragmentModule::class,
            AppModule::class
        ]
)
interface AppComponent{
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: MyApp){

    }
}