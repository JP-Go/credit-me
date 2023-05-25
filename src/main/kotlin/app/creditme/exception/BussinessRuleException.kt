package app.creditme.exception

open class BussinessRuleException(override val message: String) :
    RuntimeException(message)
