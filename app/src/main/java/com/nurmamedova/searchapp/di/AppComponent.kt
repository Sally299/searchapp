package com.nurmamedova.searchapp.di

import android.app.Application
import com.nurmamedova.searchapp.ui.fragments.ListFragment
import com.nurmamedova.searchapp.ui.fragments.MovieDescriptionFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class, RepositoriesModule::class, ViewModelModule::class,
        RoomModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(fragment: ListFragment)
    fun inject(fragment: MovieDescriptionFragment)
}