import java.time.LocalDateTime
import java.util.UUID

data class Session(
    val token: String = UUID.randomUUID().toString(),
    val dataHora: LocalDateTime = LocalDateTime.now()
) {
}