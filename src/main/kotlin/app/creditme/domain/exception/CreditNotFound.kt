package app.creditme.domain.exception

import app.creditme.exception.BussinessRuleException

class CreditNotFoundException(override val message: String = "Credit not found"):
    BussinessRuleException(message)
