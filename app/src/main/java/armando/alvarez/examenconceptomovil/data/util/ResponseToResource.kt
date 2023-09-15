package armando.alvarez.examenconceptomovil.data.util

import retrofit2.Response

object ResponseToResource {
    fun <T> responseToResource(response: Response<T>): Resource<T> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }

        return if (response.errorBody() == null) {
            Resource.Error(null, response.body())
        } else {
            Resource.Error(response.errorBody().toString(), response.body())
        }

    }
}