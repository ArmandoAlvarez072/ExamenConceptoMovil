package armando.alvarez.examenconceptomovil.presentation.di

import armando.alvarez.examenconceptomovil.domain.repository.NasaRepository
import armando.alvarez.examenconceptomovil.domain.usecase.GetImagesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun providesGetImagesUseCase(nasaRepository: NasaRepository) : GetImagesUseCase{
        return GetImagesUseCase(nasaRepository)
    }

}