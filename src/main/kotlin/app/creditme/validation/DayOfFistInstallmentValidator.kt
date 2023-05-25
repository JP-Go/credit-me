package app.creditme.validation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import jakarta.validation.Constraint
import java.time.LocalDate
import kotlin.reflect.KClass
import kotlin.annotation.Target
import kotlin.annotation.Retention
import kotlin.annotation.AnnotationTarget
import kotlin.annotation.AnnotationRetention

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [DayOfFirstInstallmentValidator::class])
annotation class ValidDayOfInstallment(
  val message:String = "Date must be after today and less that 90 days from today",
  val groups: Array<KClass<*>> = [],
  val payload: Array<KClass<out Payload>> = []
)

class DayOfFirstInstallmentValidator: ConstraintValidator<ValidDayOfInstallment,LocalDate>{

  override fun isValid(value: LocalDate?,context: ConstraintValidatorContext): Boolean {
    if (value == null) return false
    val now = LocalDate.now()
    val nowAfter90Days = now.plusDays(90)
    if (value.isBefore(now) || value.isAfter(nowAfter90Days)){
      return false
    }
    return true
  }

}
