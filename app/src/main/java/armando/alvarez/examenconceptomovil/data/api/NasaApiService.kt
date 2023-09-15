package armando.alvarez.examenconceptomovil.data.api

import armando.alvarez.examenconceptomovil.data.model.NasaItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApiService {

    @GET("planetary/apod")
    suspend fun getImages(
        @Query("api_key")
        key: String,
        @Query("count")
        items: Int
    ) : Response<List<NasaItem>>

}