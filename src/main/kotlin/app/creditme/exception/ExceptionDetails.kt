package app.creditme.exception

import java.time.LocalDateTime

data class ExceptionDetails (
  val title: String,
  val message: String,
  val status: Int,
  val timestamp: LocalDateTime,
  val exception: String,
  val details: MutableMap<String,String?>
)
