package app.creditme.domain

import jakarta.persistence.Embeddable

@Embeddable
data class Address(
    var zipCode: String = "",
    var street: String = "",
)
