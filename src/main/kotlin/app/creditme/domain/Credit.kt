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
    @Column(nullable = false, unique = true) val creditCode: UUID = UUID.randomUUID(),
    @Column(nullable = false) val creditValue: BigDecimal = BigDecimal.ZERO,
    @Column(nullable = false) val dayFirstInstallment: LocalDate,
    @Column(nullable = false) val numberOfInstallments: Int  = 0,
    @Enumerated val status: Status = Status.IN_PROGRESS,
    @ManyToOne  var customer: Customer? = null,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null
)
