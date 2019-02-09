package com.fiap.gcs.githubaac.di.modules

import com.fiap.gcs.githubaac.ui.userprofile.UserProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeUserProfileFragment() : UserProfileFragment
}