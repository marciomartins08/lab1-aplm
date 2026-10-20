data class User (
    val email: String,
    val password: String,
    val sessoes: MutableList<Session> = mutableListOf(),
    val movimentos: MutableList<Movimento> = mutableListOf()
)
{
    companion object {
        fun registarOuLogin(cmd: List<String>, bdUsers: MutableMap<String, User>): User? {
            val email = cmd[0]
            val password = cmd[1]

            var user = bdUsers[email]
            if (user != null) {
                if (user.password != password) {
                    return null
                }
            } else {
                user = User(email, password)
                bdUsers[email] = user
            }

            val newSession = Session()
            user.sessoes.add(newSession)

            return user
        }
    }
}