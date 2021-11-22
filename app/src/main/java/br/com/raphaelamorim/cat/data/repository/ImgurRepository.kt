package br.com.raphaelamorim.cat.data.repository

import br.com.raphaelamorim.cat.data.model.response.ImgurResponse
import kotlinx.coroutines.flow.Flow
import br.com.raphaelamorim.cat.data.model.dto.Result

interface ImgurRepository {
    fun searchImgur(text: String): Flow<Result<ImgurResponse?>>
}