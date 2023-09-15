package armando.alvarez.examenconceptomovil.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import armando.alvarez.examenconceptomovil.R
import armando.alvarez.examenconceptomovil.data.model.NasaItem
import armando.alvarez.examenconceptomovil.data.util.Network
import armando.alvarez.examenconceptomovil.data.util.Resource
import armando.alvarez.examenconceptomovil.domain.usecase.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NasaViewModel @Inject constructor(
    private val application: Application,
    private val getImagesUseCase: GetImagesUseCase
) : ViewModel() {

    var imagesResponse: MutableLiveData<Resource<List<NasaItem>>> = MutableLiveData()

    fun getImages(items: Int) = viewModelScope.launch(Dispatchers.IO) {
        imagesResponse.postValue(Resource.Loading())

        try {
            if (Network.isNetworkAvailable(application)) {
                val response = getImagesUseCase.execute(items)
                imagesResponse.postValue(response)
            } else {
                imagesResponse.postValue(Resource.Error(application.getString(R.string.error_no_network)))

            }
        } catch (e: Exception) {
            imagesResponse.postValue(Resource.Error(e.message))
        }

    }

}