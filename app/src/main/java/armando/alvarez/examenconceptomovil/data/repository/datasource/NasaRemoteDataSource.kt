package armando.alvarez.examenconceptomovil.data.repository.datasource

import armando.alvarez.examenconceptomovil.data.model.NasaItem
import retrofit2.Response

interface NasaRemoteDataSource {
    suspend fun getImages(key: String, items: Int): Response<List<NasaItem>>
}