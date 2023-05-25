package app.creditme.dto

import app.creditme.domain.Customer
import java.math.BigDecimal
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PositiveOrZero

data class CustomerUpdateDto(
    @field:NotEmpty(message = "Missing firstName") val firstName: String,
    @field:NotEmpty(message = "Missing lastName") val lastName: String,
    @field:NotEmpty(message = "Missing zipCode") val zipCode: String,
    @field:NotEmpty(message = "Missing street") val street: String,
    @field:NotNull(message = "Missing income") 
    @field:PositiveOrZero(message =  "Income must be positive or zero")
    val income: BigDecimal,
) {
    fun toEntity(customer: Customer): Customer {
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.zipCode = this.zipCode
        customer.address.street = this.street
        return customer
    }
}
