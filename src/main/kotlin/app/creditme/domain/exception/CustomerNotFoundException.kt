package app.creditme.domain.exception

import app.creditme.exception.BussinessRuleException

class CustomerNotFoundException(override val message: String = "Customer not found"):
    BussinessRuleException(
        message,
    )
