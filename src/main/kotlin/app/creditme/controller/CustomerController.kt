package app.creditme.controller

import app.creditme.dto.CustomerDto
import app.creditme.dto.CustomerUpdateDto
import app.creditme.dto.CustomerView
import app.creditme.service.impl.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.ResponseStatus

@RestController()
@RequestMapping("/api/v1/customer")
class CustomerController(private val customerService: CustomerService) {

  @PostMapping
  fun registerCustomer(@RequestBody @Valid customerDto: CustomerDto): ResponseEntity<String> {
    val customer = this.customerService.save(customerDto.toEntity())
    return ResponseEntity("Customer ${customer.email} saved", HttpStatus.CREATED)
  }

  @GetMapping("/{id}")
  fun findCustomerById(@PathVariable id: Long): ResponseEntity<CustomerView> {
    val customer = this.customerService.findById(id)
    return ResponseEntity(CustomerView(customer), HttpStatus.OK)
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun deleteCustomer(@PathVariable id: Long) = this.customerService.deleteById(id)

  @PatchMapping("/{id}")
  fun updateCustomer(
      @PathVariable id: Long,
      @RequestBody @Valid customerUpdateDto: CustomerUpdateDto
  ): ResponseEntity<CustomerView> {

    val customer = this.customerService.findById(id)
    val customerToUpdate = customerUpdateDto.toEntity(customer)
    val updatedCustomer = this.customerService.save(customerToUpdate)

    return ResponseEntity(CustomerView(updatedCustomer), HttpStatus.OK)
  }
}
