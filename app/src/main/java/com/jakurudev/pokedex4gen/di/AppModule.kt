package com.jakurudev.pokedex4gen.di

import com.jakurudev.pokedex4gen.data.PokemonService
import com.jakurudev.pokedex4gen.data.repository_impl.PokemonRepositoryImpl
import com.jakurudev.pokedex4gen.domain.repository.PokemonRepository
import com.jakurudev.pokedex4gen.domain.use_case.GetOnePokemonUseCase
import com.jakurudev.pokedex4gen.domain.use_case.GetPokemonsUseCase
import com.jakurudev.pokedex4gen.domain.use_case.PokemonUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofitService(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providePokemonService(retrofit: Retrofit): PokemonService = retrofit.create(PokemonService::class.java)

    @Provides
    @Singleton
    fun providePokemonRepository(service: PokemonService): PokemonRepository {
        return PokemonRepositoryImpl(pokemonService = service)
    }

    @Provides
    @Singleton
    fun providePokemonUseCases(repository: PokemonRepository) : PokemonUseCases {
        return PokemonUseCases(
            getPokemons = GetPokemonsUseCase(repository = repository),
            getOnePokemonUseCase = GetOnePokemonUseCase(repository = repository)
        )
    }
}