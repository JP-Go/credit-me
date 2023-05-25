package app.creditme.service.impl

import app.creditme.service.ICreditService
import app.creditme.domain.Credit
import app.creditme.domain.exception.CreditNotFoundException
import app.creditme.repository.CreditRepository
import app.creditme.service.ICustomerService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CreditService(
  private val creditRepository: CreditRepository,
  private val customerService: ICustomerService
) 
: ICreditService {

    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }
  
    override fun findAllByCustomer(customerId: Long): List<Credit> =
        this.creditRepository.findAllByCustomer(customerId)

    override fun findByCreditCode(creditCode: UUID,customerId:Long): Credit
    {
        val credit = 
            this.creditRepository.findByCreditCode(creditCode) ?:
            throw CreditNotFoundException("Credit with code $creditCode not found") 

        return if (credit.customer?.id == customerId) credit else throw RuntimeException("Contact admin")
    }


}
