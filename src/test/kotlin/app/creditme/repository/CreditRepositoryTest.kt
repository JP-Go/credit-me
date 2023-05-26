import app.creditme.domain.Address
import app.creditme.domain.Credit
import app.creditme.domain.Customer
import app.creditme.repository.CreditRepository
import java.math.BigDecimal
import java.time.LocalDate
import java.time.Month
import java.util.UUID
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class CreditRepositoryTest {

  @Autowired lateinit var creditRepository: CreditRepository
  @Autowired lateinit var testEntityManager: TestEntityManager

  private lateinit var customer: Customer
  private lateinit var credit1: Credit
  private lateinit var credit2: Credit

  @BeforeEach
  fun setup() {
    customer = testEntityManager.persist(buildCustomer())
    credit1 = testEntityManager.persist(buildCredit(customer = customer))
    credit2 = testEntityManager.persist(buildCredit(customer = customer))
  }

  @Test
  fun `should find credit by creditCode`() {
    val creditCode1 = UUID.fromString("189fa9ab-cf17-4d36-a79b-03830d8f338f")
    val creditCode2 = UUID.fromString("ac07a29b-e155-4693-b3cc-bef05f0d77d8")

    credit1.creditCode = creditCode1
    credit2.creditCode = creditCode2

    val fakeCredit1 = creditRepository.findByCreditCode(creditCode1)!!
    val fakeCredit2 = creditRepository.findByCreditCode(creditCode2)!!

    Assertions.assertThat(fakeCredit1).isNotNull
    Assertions.assertThat(fakeCredit2).isNotNull
  }

  private fun buildCredit(
      creditValue: BigDecimal = BigDecimal.valueOf(500.0),
      dayFirstInstallment: LocalDate = LocalDate.of(2023, Month.APRIL, 22),
      numberOfInstallments: Int = 5,
      customer: Customer
  ): Credit =
      Credit(
          creditValue = creditValue,
          dayFirstInstallment = dayFirstInstallment,
          numberOfInstallments = numberOfInstallments,
          customer = customer
      )
  private fun buildCustomer(
      firstName: String = "Cami",
      lastName: String = "Cavalcante",
      cpf: String = "28475934625",
      email: String = "camila@gmail.com",
      password: String = "12345",
      zipCode: String = "12345",
      street: String = "Rua da Cami",
      income: BigDecimal = BigDecimal.valueOf(1000.0),
  ) =
      Customer(
          firstName = firstName,
          lastName = lastName,
          cpf = cpf,
          email = email,
          password = password,
          address =
              Address(
                  zipCode = zipCode,
                  street = street,
              ),
          income = income,
      )
}
