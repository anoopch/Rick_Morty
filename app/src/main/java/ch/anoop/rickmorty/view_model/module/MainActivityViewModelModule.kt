package ch.anoop.rickmorty.view_model.module

import ch.anoop.rickmorty.repository.network.NetworkDataSource
import ch.anoop.rickmorty.repository.network.NetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class MainActivityViewModelModule {

    @Binds
    @ViewModelScoped
    abstract fun bindNetworkRepository(repo: NetworkDataSourceImpl): NetworkDataSource

}