package armando.alvarez.examenconceptomovil.presentation.di

import armando.alvarez.examenconceptomovil.presentation.adapter.NasaAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Provides
    @Singleton
    fun providesNasaAdapter() : NasaAdapter{
        return NasaAdapter()
    }
}