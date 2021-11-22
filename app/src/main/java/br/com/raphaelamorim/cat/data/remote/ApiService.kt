package br.com.raphaelamorim.cat.data.remote

import br.com.raphaelamorim.cat.data.model.response.ImgurResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("gallery/search/")
    suspend  fun searchGallery(
        @Query("q") text: String
    ): Response<ImgurResponse>
}