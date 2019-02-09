package com.fiap.gcs.githubaac.di.modules

import android.arch.lifecycle.*
import com.fiap.gcs.githubaac.di.key.ViewModelKey
import com.fiap.gcs.githubaac.ui.userprofile.UserProfileViewModel
import com.fiap.gcs.githubaac.util.viewmodel.FactoryViewModel
import dagger.*
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserProfileViewModel::class)
    abstract fun bindUserProfileViewModel(repoViewModel: UserProfileViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: FactoryViewModel): ViewModelProvider.Factory
}
