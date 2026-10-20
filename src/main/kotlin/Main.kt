fun main() {
    var cmd: String
    var lista = mutableListOf<Movimento>()
    var valorMinimo = 20
    while (true) {
        cmd = readln()
        val strings = cmd.split(" ")
        when(strings[0]) {
            "i" -> lista = Movimento.inserir(strings.drop(1), lista)
            "l" -> Movimento.verMovimentos(strings[1].toInt(), lista)
            "r" -> lista = Movimento.removerMovimento(strings.drop(1), lista)
            "b" -> println(Movimento.calcularBalanco(strings[1].toInt(), lista))
            "m" -> valorMinimo = strings[1].toInt()
            "v" -> Movimento.verificarValidade(valorMinimo, lista)
            "w" -> Movimento.ehComportavel(strings[1].toInt(),valorMinimo,lista,strings[2].toInt())
            "c" -> break
        }

    }
}