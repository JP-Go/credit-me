package app.creditme.controller

import app.creditme.dto.CreditDto
import app.creditme.dto.CreditView
import app.creditme.dto.CreditViewList
import app.creditme.service.impl.CreditService
import jakarta.validation.Valid
import java.util.UUID
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/credit")
class CreditController(
    private val creditService: CreditService,
) {

  @PostMapping
  fun saveCredit(@RequestBody @Valid creditDto: CreditDto): ResponseEntity<String> {
    val credit = this.creditService.save(creditDto.toEntity())
    return ResponseEntity(
        "Credit ${credit.creditCode} created for customer ${credit.customer?.firstName}",
        HttpStatus.CREATED
    )
  }

  @GetMapping
  fun findAllByCustomerId(
      @RequestParam(value = "customer") customerId: Long
  ): ResponseEntity<List<CreditViewList>> {

    this.creditService.findAllByCustomer(customerId).map { CreditViewList(it) }.also {
      return ResponseEntity(it, HttpStatus.OK)
    }
  }

  @GetMapping("/{creditCode}")
  fun findByCreditCode(
      @RequestParam("customer") customerId: Long,
      @PathVariable("creditCode") creditCode: UUID,
  ): ResponseEntity<CreditView> {
    val credit = this.creditService.findByCreditCode(creditCode, customerId)
    return ResponseEntity(CreditView(credit), HttpStatus.OK)
  }
}
