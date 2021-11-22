package br.com.raphaelamorim.cat.data.model.dto


class Result<T>(
    val status: ResultStatus,
    val data: T? = null,
    val error: Throwable? = null,
    val message: String? = null,
    val code: Int? = null,
) {
    companion object {
        fun <T> success(data: T) =
            Result(ResultStatus.SUCCESS, data)
        fun <T>error(err: Throwable? = null, message: String? = null, code: Int? = null) =
            Result<T>(status = ResultStatus.ERROR, error= err, message = message, code = code)
    }
}

enum class ResultStatus {
    SUCCESS, ERROR
}