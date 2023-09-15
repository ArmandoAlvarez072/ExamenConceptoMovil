package armando.alvarez.examenconceptomovil.data.repository.datasourceimpl

import armando.alvarez.examenconceptomovil.data.api.NasaApiService
import armando.alvarez.examenconceptomovil.data.model.NasaItem
import armando.alvarez.examenconceptomovil.data.repository.datasource.NasaRemoteDataSource
import retrofit2.Response

class NasaRemoteDataSourceImpl(
    private val nasaApiService: NasaApiService
) : NasaRemoteDataSource {
    override suspend fun getImages(key: String, items: Int): Response<List<NasaItem>> {
        return nasaApiService.getImages(key, items)
    }
}