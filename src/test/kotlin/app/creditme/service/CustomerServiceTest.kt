package app.creditme.service

import app.creditme.domain.Address
import app.creditme.domain.Customer
import app.creditme.domain.exception.CustomerNotFoundException
import app.creditme.repository.CustomerRepository
import app.creditme.service.impl.CustomerService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import java.math.BigDecimal
import java.util.Optional
import java.util.Random
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles

@ExtendWith(MockKExtension::class)
class CustomerServiceTest {

  @MockK lateinit var customerRepository: CustomerRepository
  @InjectMockKs lateinit var customerService: CustomerService

  @Test
  fun `should create a customer`() {

    val fakeCustomer = buildCustomer()
    every { customerRepository.save(any()) } returns fakeCustomer

    val actual = customerService.save(fakeCustomer)

    Assertions.assertThat(actual).isNotNull
    Assertions.assertThat(actual).isSameAs(fakeCustomer)
    verify(exactly = 1) { customerRepository.save(fakeCustomer) }
  }

  @Test
  fun `should find customer by id`() {
    val fakeId: Long = Random().nextLong()
    val fakeCustomer = buildCustomer(id = fakeId)

    every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)

    val actual = customerService.findById(fakeId)

    Assertions.assertThat(actual).isNotNull
    Assertions.assertThat(actual).isExactlyInstanceOf(Customer::class.java)
    Assertions.assertThat(actual).isSameAs(fakeCustomer)

    verify(exactly = 1) { customerRepository.findById(fakeId) }
  }

  @Test
  fun `should throw CustomerNotFoundException if customer is not found`() {
    val fakeId: Long = Random().nextLong()

    every { customerRepository.findById(fakeId) } returns Optional.empty()

    Assertions.assertThatExceptionOfType(CustomerNotFoundException::class.java)
        .isThrownBy { customerService.findById(fakeId) }
        .withMessage("Customer with id '$fakeId' not found")

    verify(exactly = 1) { customerRepository.findById(fakeId) }
  }

  @Test
  fun `should delete the Customer and throw when called with the same id`() {
    val fakeId: Long = Random().nextLong()
    val fakeCustomer = buildCustomer(id = fakeId)

    every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)
    every { customerRepository.delete(fakeCustomer) } just runs

    customerService.deleteById(fakeId)

    verify(exactly = 1) { customerRepository.findById(fakeId) }
    verify(exactly = 1) { customerRepository.delete(fakeCustomer) }
  }

  private fun buildCustomer(
      firstName: String = "Laiza",
      lastName: String = "Leal",
      cpf: String = "287.785.693-00",
      email: String = "laiza@mail.com",
      password: String = "123456789",
      zipCode: String = "65543-874",
      street: String = "Rua dos abandonados",
      income: BigDecimal = BigDecimal.valueOf(1000),
      id: Long = 1L
  ) =
      Customer(
          firstName = firstName,
          lastName = lastName,
          cpf = cpf,
          email = email,
          password = password,
          income = income,
          id = id,
          address = Address(zipCode = zipCode, street = street)
      )
}
