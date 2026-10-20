fun main() {
    var cmd: String
    var valorMinimo = 0
    var bdUsers = mutableMapOf<String, User>()
    var userLogado: User? = null

    while (userLogado == null) {
        val cmd1 = readln().split(" ")
        if (cmd1.isNotEmpty() && cmd1[0] == "u") {
            userLogado = User.registarOuLogin(cmd1.drop(1), bdUsers)
        } else {
            println("Enter u <email> <password>")
        }
    }
    while (true) {
        cmd = readln()
        val strings = cmd.split(" ")
        var listaUser = userLogado.movimentos
        when(strings[0]) {
            "i" -> Movimento.inserir(strings.drop(1), listaUser)
            "l" -> Movimento.verMovimentos(strings[1].toInt(), listaUser)
            "r" -> Movimento.removerMovimento(strings.drop(1), listaUser)
            "b" -> println(Movimento.calcularBalanco(strings[1].toInt(), listaUser))
            "m" -> valorMinimo = strings[1].toInt()
            "v" -> Movimento.verificarValidade(valorMinimo, listaUser)
            "w" -> Movimento.ehComportavel(strings[1].toInt(),valorMinimo,listaUser,strings[2].toInt())
            "c" -> break
        }

    }
}