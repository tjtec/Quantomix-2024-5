package hwr.oop

class RoundsWith2Trainers(private val trainer1:Coach, private val trainer2:Coach) {
    var currentQuantomixTrainer1: Quantomix?=trainer1.quantomix1
    var currentQuantomixTrainer2: Quantomix?=trainer2.quantomix2
    fun start(): Coach {
        if (currentQuantomixTrainer1==null || currentQuantomixTrainer2==null) {
            error("No Quantomix found")
        }
        else {
        var battle = Battle(currentQuantomixTrainer1!!, currentQuantomixTrainer2!!)
        while (nextQuantomix() != null){
            round(battle)
            if (currentQuantomixTrainer1!!.kp==0){
                currentQuantomixTrainer1=nextQuantomix()
            }
            else{
                currentQuantomixTrainer2=nextQuantomix()
            }
        }
        if (currentQuantomixTrainer1 == null){
            return trainer1
        }
        else {
            return trainer2
        }
        //ToDo:Maybe add massage that there is no Quantomix left

    // ToDo: schauen, dass man hier die Initiative miteinbezieht und nicht einfach stumpf
         //ToDO: (Fortsetzung) die Quantomix nacheinander
    }
    }
    private fun round(battle: Battle){
        var nextAttacker = battle.nextAttacker()
        var attack1 = askForFirstAttackTrainer1()
        var attack2 = askForFirstAttackTrainer2()
        var battleEnd = false
        while (battleEnd == false){
            if (nextAttacker == currentQuantomixTrainer1){
                battleEnd =battle.newKp(attack1)
                attack1 = askForFirstAttackTrainer1()
            }
            else{
                battleEnd =battle.newKp(attack2)
                askForFirstAttackTrainer2()
            }
            nextAttacker = battle.otherAttacker()
        }
    }
    fun askForFirstAttackTrainer1 (): Attack{
        return TODO("We have to implement a function, " +
                "which gets the attack from the cli and give it to this function.")
    }
    fun askForFirstAttackTrainer2 (): Attack{
        return TODO("We have to implement a function, " +
                "which gets the attack from the cli and give it to this function.")
    }

    fun nextQuantomix(): Quantomix?{
        if (currentQuantomixTrainer1 != null) {
            return TODO(
                "This function should check, if the trainer has other Quantomix " +
                        "which are alive. The function should also work with the answer of the trainer " +
                        "which of the alive Quantomix he want to use next."
            )
        }
        return null
    }
}