package app.creditme.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID
import org.springframework.data.jpa.repository.Query

import app.creditme.domain.Credit

@Repository
interface CreditRepository: JpaRepository<Credit,Long>{

    fun findByCreditCode(creditCode: UUID):Credit?

    @Query(value = "SELECT * FROM credit WHERE customer_id = ?1",nativeQuery= true)
    fun findAllByCustomer(customerId:Long): List<Credit>
}
