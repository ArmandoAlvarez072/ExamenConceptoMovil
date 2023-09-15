package armando.alvarez.examenconceptomovil.data.repository

import armando.alvarez.examenconceptomovil.data.model.NasaItem
import armando.alvarez.examenconceptomovil.data.repository.datasource.NasaRemoteDataSource
import armando.alvarez.examenconceptomovil.data.util.Constants
import armando.alvarez.examenconceptomovil.data.util.Resource
import armando.alvarez.examenconceptomovil.data.util.ResponseToResource
import armando.alvarez.examenconceptomovil.domain.repository.NasaRepository

class NasaRepositoryImpl(
    private val nasaRemoteDataSource: NasaRemoteDataSource
) : NasaRepository {
    override suspend fun getImages(items: Int): Resource<List<NasaItem>> {
        return ResponseToResource.responseToResource(
            nasaRemoteDataSource.getImages(
                Constants.apiKey,
                items
            )
        )
    }
}