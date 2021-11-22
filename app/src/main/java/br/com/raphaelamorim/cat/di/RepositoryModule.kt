package br.com.raphaelamorim.cat.di

import br.com.raphaelamorim.cat.data.repository.ImgurRepository
import br.com.raphaelamorim.cat.data.repository.ImgurRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract  fun bindCatRepository(
        repository: ImgurRepositoryImpl
    ): ImgurRepository
}