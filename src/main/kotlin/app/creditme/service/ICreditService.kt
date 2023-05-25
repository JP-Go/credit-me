package app.creditme.service

import app.creditme.domain.Credit
import java.util.UUID

interface ICreditService {

  fun save(credit: Credit): Credit
  fun findAllByCustomer(customerId: Long): List<Credit>
  fun findByCreditCode(creditCode: UUID,customerId: Long): Credit
}
