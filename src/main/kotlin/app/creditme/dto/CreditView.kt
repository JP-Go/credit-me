package app.creditme.dto

import app.creditme.domain.Credit
import app.creditme.enummeration.Status
import java.math.BigDecimal
import java.util.UUID

data class CreditView (
    val creditCode: UUID,
    val creditValue: BigDecimal,
    val numberOfInstallments: Int,
    val status: Status,
    val customerEmail: String?,
    val customerIncome: BigDecimal?
){
    constructor(credit:Credit): this(
        creditCode = credit.creditCode,
        creditValue = credit.creditValue,
        numberOfInstallments = credit.numberOfInstallments,
        status = credit.status,
        customerEmail = credit.customer?.email,
        customerIncome = credit.customer?.income
    )
}
