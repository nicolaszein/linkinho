package dev.nicolaszein.linkinho.web.api.configuration

import dev.nicolaszein.linkinho.web.api.exceptions.ResourceNotFound
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

data class ExceptionResponse(
    val code: String,
    val message: String
)

data class DetailExceptionResponse(
    val code: String,
    val message: String,
    val details: MutableList<ErrorDetail> = mutableListOf()
) {
    fun addErrorDetails(target: String, message: String) {
        this.details.add(ErrorDetail(target, message))
    }
}

data class ErrorDetail(
    val target: String,
    val message: String
)

@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun invalidJsonInput(
        ex: HttpMessageNotReadableException
    ): ResponseEntity<ExceptionResponse> {
        val exceptionResponse = ExceptionResponse(
            code = "INPUT_VALIDATION_ERROR",
            message = "Invalid Json input"
        )

        return ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException::class)
    fun invalidInputFormat(): ResponseEntity<ExceptionResponse> {
        val exceptionResponse = ExceptionResponse(
            code = "UNSUPPORTED_MEDIA_TYPE",
            message = "Input format is unsupported"
        )

        return ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ResourceNotFound::class)
    fun resourceNotFound(
        ex: ResourceNotFound
    ): ResponseEntity<ExceptionResponse> {
        val exceptionResponse = ExceptionResponse(
            code = "RESOURCE_NOT_FOUND",
            message = ex.message
        )

        return ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun invalidInput(
        ex: MethodArgumentNotValidException
    ): ResponseEntity<DetailExceptionResponse> {
        val exceptionResponse = DetailExceptionResponse(
            code = "INPUT_VALIDATION_ERROR",
            message = "Some fields are not valid"
        )

        for (error in ex.bindingResult.fieldErrors) {
            exceptionResponse.addErrorDetails(
                target = error.field,
                message = error.defaultMessage
            )
        }

        return ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST)
    }
}

