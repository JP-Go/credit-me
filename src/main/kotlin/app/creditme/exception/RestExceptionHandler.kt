package app.creditme.exception

import java.time.LocalDateTime
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import app.creditme.domain.exception.CustomerNotFoundException

@RestControllerAdvice
class RestExceptionHander {

  @ExceptionHandler(MethodArgumentNotValidException::class)
  fun handle(exception: MethodArgumentNotValidException): ResponseEntity<ExceptionDetails> {
    val errors: MutableMap<String, String?> = HashMap()
    exception.bindingResult.allErrors.stream().forEach { error: ObjectError ->
      val field: String = (error as FieldError).field
      val message: String? = error.defaultMessage
      errors[field] = message
    }
    return ResponseEntity(
        ExceptionDetails(
            title = "Bad Request",
            message = "Missing or invalid fields.",
            timestamp = LocalDateTime.now(),
            status = HttpStatus.BAD_REQUEST.value(),
            exception = exception.javaClass.toString(),
            details = errors
        ),
        HttpStatus.BAD_REQUEST
    )
  }

  @ExceptionHandler(DataIntegrityViolationException::class)
  fun handleIntegrityException(
      ex: DataIntegrityViolationException
  ): ResponseEntity<ExceptionDetails> {
    return ResponseEntity(
        ExceptionDetails(
            title = "Bad Request",
            message = "Duplicate fields",
            timestamp = LocalDateTime.now(),
            status = HttpStatus.BAD_REQUEST.value(),
            exception = ex.javaClass.toString(),
            details = mutableMapOf(ex.cause.toString() to ex.message)
        ),
        HttpStatus.CONFLICT
    )
  }

  @ExceptionHandler(BussinessRuleException::class)
  fun handleBussinessRuleException(ex:BussinessRuleException): ResponseEntity<ExceptionDetails>{
    return ResponseEntity(
        ExceptionDetails(
            title = "Bad Request",
            message = ex.message,
            timestamp = LocalDateTime.now(),
            status = HttpStatus.BAD_REQUEST.value(),
            exception = ex.javaClass.toString(),
            details = mutableMapOf(ex.javaClass.toString() to ex.message)
        ),
        HttpStatus.CONFLICT
    )
  }
}
