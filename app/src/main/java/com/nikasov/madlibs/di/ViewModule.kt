package com.nikasov.madlibs.di

import android.content.Context
import com.nikasov.madlibs.data.room.Database
import com.nikasov.madlibs.ui.utils.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ViewModule {

    @Provides
    @Singleton
    fun provideResourceProvider (@ApplicationContext context: Context) = ResourceProvider(context)
}