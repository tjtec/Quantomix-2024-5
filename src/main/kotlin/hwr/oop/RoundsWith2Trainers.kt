package hwr.oop

class RoundsWith2Trainers(private val trainer1:Coach, private val trainer2:Coach) {
    var currentQuantomixTrainer1=trainer1.quantomix1
    var currentQuantomixTrainer2=trainer2.quantomix2
    fun start() {
        var battle = Battle(currentQuantomixTrainer1, currentQuantomixTrainer2)
        var nextAttacker = battle.nextAttacker()
        var attack1 = askForFirstAttackTrainer1()
        var attack2 = askForFirstAttackTrainer2()
        var battleEnd = false
        while (battleEnd == false){
            if (nextAttacker == currentQuantomixTrainer1){
                battle.newKp(attack1, nextAttacker)
                attack1 = askForFirstAttackTrainer1()
            }
            else{
                battle.newKp(attack2, nextAttacker)
                askForFirstAttackTrainer2()
            }
            nextAttacker = battle.otherAttacker()
        }// ToDo: schauen, dass man hier die Initiative miteinbezieht und nicht einfach stumpf
         //ToDO: (Fortsetzung) die Quantomix nacheinander
        // ToDo: eine Runde besteht aus einem angriff, der angriff des anderen und dann in der
    //  TODO: (Fortsetzung)nächsten Runde wieder schauen, welcher Quantomix als erstes angreift.
    //   (zweites Quantomix besiegt miteinbeziehen)
        //ToDo:vielleicht fragen, welches nächste Quantomix (in einer anderen Klasse und Methode)

    }
    fun askForFirstAttackTrainer1 (): Attack{
        return TODO("We have to implement a function, " +
                "which gets the attack from the cli and give it to this function.")
    }
    fun askForFirstAttackTrainer2 (): Attack{
        return TODO("We have to implement a function, " +
                "which gets the attack from the cli and give it to this function.")
    }
}