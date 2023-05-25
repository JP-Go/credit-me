package app.creditme.service.impl

import app.creditme.domain.Customer
import app.creditme.domain.exception.CustomerNotFoundException
import app.creditme.repository.CustomerRepository
import app.creditme.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(private val customerRepository: CustomerRepository) : ICustomerService {

  override fun save(customer: Customer): Customer = this.customerRepository.save(customer)

  override fun findById(customerId: Long): Customer =
      this.customerRepository.findById(customerId).orElseThrow {
        throw CustomerNotFoundException("Customer with id '$customerId' not found")
      }

  override fun deleteById(customerId: Long){
    val customerToDelete:Customer = this.findById(customerId)
    this.customerRepository.delete(customerToDelete)
  }
}
