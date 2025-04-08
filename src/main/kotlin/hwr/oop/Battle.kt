package hwr.oop

class Battle(var quantomixA: Quantomix, var quantomixB: Quantomix) {

    fun nextAttacker (): Quantomix{
        if (quantomixA.speed >= quantomixB.speed){
            return quantomixA;
        }
        else {
            return quantomixB;
        }
    }

    fun attackPower (var attack:Attack): Int {

    }
}