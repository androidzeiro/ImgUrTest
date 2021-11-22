package br.com.raphaelamorim.cat.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.raphaelamorim.cat.data.model.dto.ImgurDTO
import br.com.raphaelamorim.cat.data.model.dto.ResultStatus
import br.com.raphaelamorim.cat.data.repository.ImgurRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImgurViewModel @Inject constructor(private val repository: ImgurRepository) : ViewModel() {

    private val _imgurResult = MutableLiveData<ImgurDTO?>(null)
    val imgurResult: LiveData<ImgurDTO?> get() = _imgurResult

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> get() = _error

    fun searchImages(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.searchImgur(text).collect { result ->
                when (result.status) {
                    ResultStatus.SUCCESS -> {
                        _imgurResult.postValue(ImgurDTO from result.data)
                    }
                    ResultStatus.ERROR -> {
                        _error.postValue("Fail to fetch images")
                    }
                }

            }
        }
    }

}