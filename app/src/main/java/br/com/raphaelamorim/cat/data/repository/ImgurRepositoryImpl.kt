package br.com.raphaelamorim.cat.data.repository

import br.com.raphaelamorim.cat.data.remote.ApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import br.com.raphaelamorim.cat.extensions.feedCommonFlow
class ImgurRepositoryImpl @Inject constructor(
    private val api: ApiService
): ImgurRepository {
    override fun searchImgur(text: String) = flow {
        feedCommonFlow {api.searchGallery(text)}
    }
}