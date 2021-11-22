package br.com.raphaelamorim.cat.extensions

import br.com.raphaelamorim.cat.data.model.dto.Result
import kotlinx.coroutines.flow.FlowCollector
import retrofit2.Response

suspend fun <T> FlowCollector<Result<T?>>.feedCommonFlow(
    request: suspend () -> Response<T>
) {
    try {
        val result = request()
        result.run {
            if (isSuccessful) {
                emit(Result.success(body()))
            } else {
                emit(
                    Result.error(message = message(), code = code())
                )
            }
        }
    } catch (e: IllegalArgumentException){
        emit(Result.error(e))
    } catch (e: Exception) {
        emit(Result.error(e))
    }
}