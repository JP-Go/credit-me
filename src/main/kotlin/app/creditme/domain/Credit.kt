package app.creditme.domain

import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID
import app.creditme.enummeration.Status
import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.Temporal
import jakarta.persistence.Table
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.ManyToOne
import jakarta.persistence.Enumerated


@Entity
@Table(name = "credit")
data class Credit(
    @Column(nullable = false, unique = true) var creditCode: UUID = UUID.randomUUID(),
    @Column(nullable = false) var creditValue: BigDecimal = BigDecimal.ZERO,
    @Column(nullable = false) var dayFirstInstallment: LocalDate,
    @Column(nullable = false) var numberOfInstallments: Int  = 0,
    @Enumerated var status: Status = Status.IN_PROGRESS,
    @ManyToOne  var customer: Customer? = null,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
)
