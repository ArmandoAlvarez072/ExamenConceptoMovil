package armando.alvarez.examenconceptomovil.domain.repository

import armando.alvarez.examenconceptomovil.data.model.NasaItem
import armando.alvarez.examenconceptomovil.data.util.Resource

interface NasaRepository {
    suspend fun getImages(items: Int): Resource<List<NasaItem>>
}