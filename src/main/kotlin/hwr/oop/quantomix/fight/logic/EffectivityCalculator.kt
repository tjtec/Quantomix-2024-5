package hwr.oop.quantomix.fight.logic

import hwr.oop.quantomix.objects.Typ

object EffectivityCalculator {
    fun calculateEffectivity(context: DamageContext): Float {
      val primaryEffectivity = if (context.isSelfHit) {
        context.attacker.getQuantomix().getType1().getEffectivity(context.attack)
      } else {
        context.target.getQuantomix().getType1().getEffectivity(context.attack)
      }
      val secondaryType: Typ? = if (context.isSelfHit) {
        context.attacker.getQuantomix().getType2()
      } else {
        context.target.getQuantomix().getType2()
      }
      return secondaryType?.let { type2 ->
        val secondaryEffectivity = type2.getEffectivity(context.attack)
        if (primaryEffectivity >= secondaryEffectivity) primaryEffectivity else secondaryEffectivity
      } ?: primaryEffectivity
    }
}