package app.creditme.dto

import app.creditme.domain.Credit
import app.creditme.domain.Customer
import app.creditme.validation.ValidDayOfInstallment
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    
    @field:NotNull(message = "Credit Value missing")
    @field:Positive(message = "Credit value must be positive")
    val creditValue: BigDecimal,
    @field:NotNull(message = "Day of first installment missing")
    @field:ValidDayOfInstallment(message = "Day of first installment must be after today and before 90 days from today")
    val dayFirstInstallment: LocalDate,
    @field:NotNull(message = "Number of installments missing")
    val numberOfInstallments: Int,
    @field:NotNull(message = "Customer Id missing")
    val customerId: Long
) {
  fun toEntity(): Credit =
      Credit(
          creditValue = this.creditValue,
          dayFirstInstallment = this.dayFirstInstallment,
          numberOfInstallments = this.numberOfInstallments,
          customer = Customer(id = this.customerId)
      )
}
