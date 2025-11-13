import kotlin.random.Random

fun main(){
    val DAUS: String = "⚀ ⚁ ⚂ ⚃ ⚄ ⚅"
    val CARES_DAU: Array<String> = arrayOf("⚀", "⚁", "⚂", "⚃", "⚄", "⚅")

    var partides: Int?
    var tiradesPerPartida: Int?

    var partidesGuanyades: Int = 0

    println("=======================================")
    println("🎲 BENVINGUT/DA AL JOC DELS DAUS 🎲")
    println("=======================================")
    println("Per guanyar cada partida, la suma dels teus punts ha de ser superior a la de la CPU.")
    println("Sort i que la fortuna et somrigui! 🍀\n")

    // Llegim el número de partides que volem jugar
    do {
        println("Quantes partides vols jugar? (de 1 a 3)")
        partides = readLine()?.toIntOrNull()

        if (partides != null && (partides < 1 || partides > 3)){
            partides = null
            println("ERROR: Valor no acceptat!")
        }
    }while(partides == null)

    // Llegim el número de quantes tirades volem fer per cada partida
    do {
        println("Quantes tirades vols fer per cada partida? (de 1 a 6)")
        tiradesPerPartida = readLine()?.toIntOrNull()

        if (tiradesPerPartida != null && (tiradesPerPartida < 1 || tiradesPerPartida > 6)){
            tiradesPerPartida = null
            println("ERROR: Valor no acceptat!")
        }
    }while(tiradesPerPartida == null)

    // Declarem la matriu
    var tiradesGuardades: Array<IntArray>

    // Inicialitzem la matriu de partides files i (tiradesPerPartida + 1) columnes
    tiradesGuardades = Array(partides){IntArray((tiradesPerPartida + 1)) }

    // Repetim tantes vegades com partides
    for(partida in 0 until partides) {
        var acumuladorCPU: Int = 0
        var tiradaActual: Int = 0

        for (tirada in 0 until tiradesGuardades[partida].size - 1) {
            /** Tirades persona **/
            println("Tira el dau! (Intent $tirada)")
            tiradaActual = Random.nextInt(1, 6 + 1)
            println("Has tret un ${CARES_DAU[tiradaActual-1]} !")

            // Guardem la tirada
            tiradesGuardades[partida][tirada] = tiradaActual

            // Acumulem el sumatori a l'última columna de la fila
            tiradesGuardades[partida][tiradesPerPartida] += tiradaActual

            /** Tirades CPU **/
            val tiradaCPU = Random.nextInt(1, 7)
            acumuladorCPU += tiradaCPU
            println("La CPU ha tret un ${CARES_DAU[tiradaCPU - 1]} !\n")
        }

        println("\n=======================================")
        println("📋 RESUM FINAL DE LA PARTIDA")
        println("=======================================")
        println("Partida acabada!")
        println("Tu has aconseguit ${tiradesGuardades[partida][tiradesPerPartida]} punts")
        println("La CPU ha aconseguit $acumuladorCPU punts")

        if (tiradesGuardades[partida][tiradesPerPartida] > acumuladorCPU){
            partidesGuanyades ++
            println("Has guanyat!")
        }else if (tiradesGuardades[partida][tiradesPerPartida] < acumuladorCPU){
            println("Has perdut!")
        }else{
            println("Heu empatat!")
        }
    }
    val PERCENTATGE_GUANYAT = partidesGuanyades.toDouble() / partides.toDouble() * 100
    println("\n${DAUS} FI DEL JOC ${DAUS}")
    println("🏆 Has guanyat $partidesGuanyades de $partides partides.")
    println("El teu percentatge de victòries és del **${"%.2f".format(PERCENTATGE_GUANYAT)}%**!")
}