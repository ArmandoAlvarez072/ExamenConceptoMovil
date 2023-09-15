package armando.alvarez.examenconceptomovil.presentation.di

import armando.alvarez.examenconceptomovil.data.repository.NasaRepositoryImpl
import armando.alvarez.examenconceptomovil.data.repository.datasource.NasaRemoteDataSource
import armando.alvarez.examenconceptomovil.domain.repository.NasaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesNasaRepository(nasaRemoteDataSource: NasaRemoteDataSource) : NasaRepository{
        return NasaRepositoryImpl(nasaRemoteDataSource)
    }

}