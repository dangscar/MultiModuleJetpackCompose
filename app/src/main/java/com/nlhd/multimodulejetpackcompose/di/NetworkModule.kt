package com.nlhd.multimodulejetpackcompose.di

import com.nlhd.multimodulejetpackcompose.data.repository.CharacterRepositoryImp
import com.nlhd.multimodulejetpackcompose.domain.repository.CharacterRepository
import com.nlhd.multimodulejetpackcompose.domain.usecases.CharacterUseCases
import com.nlhd.multimodulejetpackcompose.domain.usecases.GetAllCharacterByPage
import com.nlhd.multimodulejetpackcompose.domain.usecases.GetCharacter
import com.nlhd.multimodulejetpackcompose.domain.usecases.GetEpisodes
import com.nlhd.network.KtorClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesKtorClient(): KtorClient = KtorClient()

    @Singleton
    @Provides
    fun providesCharacterRepository(ktorClient: KtorClient): CharacterRepository = CharacterRepositoryImp(ktorClient = ktorClient)

    @Singleton
    @Provides
    fun providesCharacterUseCases(repository: CharacterRepository): CharacterUseCases = CharacterUseCases(
        getCharacter = GetCharacter(repository = repository),
        getEpisodes = GetEpisodes(repository = repository),
        getAllCharacterByPage = GetAllCharacterByPage(repository = repository)
    )

}