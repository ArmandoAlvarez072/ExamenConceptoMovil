package armando.alvarez.examenconceptomovil.presentation.di

import armando.alvarez.examenconceptomovil.data.api.NasaApiService
import armando.alvarez.examenconceptomovil.data.repository.datasource.NasaRemoteDataSource
import armando.alvarez.examenconceptomovil.data.repository.datasourceimpl.NasaRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    fun providesNasaRemoteDataSource(nasaApiService: NasaApiService) : NasaRemoteDataSource{
        return NasaRemoteDataSourceImpl(nasaApiService)
    }

}