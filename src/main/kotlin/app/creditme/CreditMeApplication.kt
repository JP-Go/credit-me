package app.creditme

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CreditMeApplication

fun main(args: Array<String>) {
	runApplication<CreditMeApplication>(*args)
}
