package br.com.raphaelamorim.cat.data.model.dto

import br.com.raphaelamorim.cat.data.model.response.ImgurResponse

data class ImgurDTO(
    val images: List<String>
) {
    companion object {
        infix fun from(response: ImgurResponse?): ImgurDTO {
            if(response == null) return ImgurDTO(emptyList())
            val list = response.data?.flatMap { it.images.orEmpty() }?.map { it.link }
            return ImgurDTO(list.orEmpty())
        }
    }
}
