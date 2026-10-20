data class Movimento(
    val tipo: TipoMovimento,
    val data: Int,
    val periodicidade: Int,
    val valor: Int,
    val descricao: String
) {
    companion object {
        fun inserir(cmd: List<String>, listaMovimento: MutableList<Movimento>){
            val mnt = cmd[0].toInt()
            val data = cmd[1].toInt()
            val period = cmd[2].toInt()
            val tipo = when (cmd[3]) {
                "r" -> TipoMovimento.RECEITA
                "d" -> TipoMovimento.DESPESA
                else -> return
            }
            val desc = cmd.drop(4).joinToString(" ")
            if (period == 0) {
                listaMovimento.add(
                    Movimento(tipo, data, period, mnt, desc)
                )
            } else {
                var sum = data
                while (sum <= 999) {
                    listaMovimento.add(
                        Movimento(tipo, sum, period, mnt, desc)
                    )
                    sum += period
                }
            }
        }

        fun listarMovimentos(data: Int, listaMovimento: MutableList<Movimento>): MutableList<Movimento>{
            val listaMovimento2 = mutableListOf<Movimento>()
            for(movimento in listaMovimento){
                if(movimento.data == data)
                    listaMovimento2.add(movimento)
            }

            return listaMovimento2
        }

        fun verMovimentos(data: Int, listaMovimento: MutableList<Movimento>){
            val listaMovimento2 = listarMovimentos(data,listaMovimento)
            var cont = 0
            for(movimento in listaMovimento2){
                if(movimento.data == data)
                    println("${cont++} $movimento")
            }
        }

        fun removerMovimento(cmd: List<String>, listaMovimento: MutableList<Movimento>){
            val data = cmd[0].toInt()
            val pos = cmd[1].toInt()
            val listaMovimento2 = listarMovimentos(data, listaMovimento)
            if(pos >= 0 && pos < listaMovimento2.size){
                val movimento = listaMovimento2[pos]
                listaMovimento.remove(movimento)
            }
        }

        fun calcularBalanco(data: Int, listaMovimento: MutableList<Movimento>) : Int{
            var balanco = 0
            for (movimento in listaMovimento){
                if(movimento.data <= data)
                    if(movimento.tipo == TipoMovimento.DESPESA)
                        balanco -= movimento.valor
                    else
                        balanco += movimento.valor
            }
            return balanco
        }

        fun verificarValidade(valorMinimo: Int, listaMovimento: MutableList<Movimento>) {
            var despRecente: Movimento? = null

            for (i in 0..listaMovimento.lastIndex) {
                val movimento = listaMovimento[i]
                if (movimento.tipo == TipoMovimento.DESPESA) {
                    if (despRecente == null || movimento.data > despRecente.data) {
                        despRecente = movimento
                    }
                }
            }

            if (despRecente != null) {
                val balanco = calcularBalanco(despRecente.data, listaMovimento)
                if (balanco >= valorMinimo) {
                    println("Valido")
                } else {
                    println("Invalido")
                }
            }
        }

        fun ehComportavel(data: Int, valorMinimo: Int, listaMovimento: MutableList<Movimento>, valorDespesa: Int) {
            for (i in data..999) {
                var dataFutura = i
                var valido = true

                while (dataFutura <= 999) {
                    val balanco = calcularBalanco(dataFutura, listaMovimento)

                    if (balanco - valorDespesa < valorMinimo) {
                        valido = false
                        break
                    }

                    dataFutura++
                }

                if (valido) {
                    println(i)
                    return
                }
            }

            println("Incomportavel")
        }
    }
    override fun toString(): String {
        val tipoChar = if (tipo == TipoMovimento.DESPESA) "d" else "r"
        return "$valor $tipoChar $descricao"
    }
}