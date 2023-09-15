package armando.alvarez.examenconceptomovil.domain.usecase

import armando.alvarez.examenconceptomovil.data.model.NasaItem
import armando.alvarez.examenconceptomovil.data.util.Resource
import armando.alvarez.examenconceptomovil.domain.repository.NasaRepository

class GetImagesUseCase(
    private val repository: NasaRepository
) {

    suspend fun execute(items: Int): Resource<List<NasaItem>>{
        return repository.getImages(items)
    }

}