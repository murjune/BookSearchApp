package org.techtown.booksearchapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.techtown.booksearchapp.data.datasource.RemoteDataSource
import org.techtown.booksearchapp.data.datasource.RemoteDataSourceImpl

@InstallIn(SingletonComponent::class)
@Module
abstract class RemoteSourceModule {

    @Binds
    abstract fun bindRemoteDataSource(
        impl: RemoteDataSourceImpl
    ): RemoteDataSource
}
