package hwr.oop.quantomix.objects

import hwr.oop.quantomix.Exceptions.NotAllowedTyp
import hwr.oop.quantomix.fight.objects.Attack

enum class Typ {
  Normal,
  Geist,
  Feuer,
  Pflanze,
  Wasser,
  Boden,
  Elektro,
  Kampf,
  Gift,
  Kaefer,
  Psycho,
  Drache,
  Unlicht,
  Flug,
  Gestein,
  Stahl,
  Eis,
  Fee;

  fun weaknesses(): Set<Typ> {
    return when (this) {
      Normal -> setOf(Kampf)
      Kampf -> setOf(Flug, Psycho, Fee)
      Flug -> setOf(Gestein, Elektro, Eis)
      Gift -> setOf(Boden, Psycho)
      Boden -> setOf(Wasser, Pflanze, Eis)
      Gestein -> setOf(Wasser, Pflanze, Kampf, Boden, Stahl)
      Kaefer -> setOf(Feuer, Flug, Gestein)
      Geist -> setOf(Geist, Unlicht)
      Stahl -> setOf(Kampf, Boden, Feuer)
      Feuer -> setOf(Wasser, Boden, Gestein)
      Wasser -> setOf(Pflanze, Elektro)
      Pflanze -> setOf(Feuer, Eis, Gift, Flug, Kaefer)
      Elektro -> setOf(Boden)
      Psycho -> setOf(Kaefer, Geist, Unlicht)
      Eis -> setOf(Feuer, Kampf, Gestein, Stahl)
      Drache -> setOf(Eis, Drache, Fee)
      Unlicht -> setOf(Kampf, Fee)
      Fee -> setOf(Gift, Stahl)
    }
  }

  fun strength(): Set<Typ> {
    return when (this) {
      Normal -> emptySet()
      Kampf -> setOf(Kaefer, Gestein, Unlicht)
      Flug -> setOf(Kampf, Kaefer, Pflanze)
      Gift -> setOf(Pflanze, Fee, Kampf, Gift)
      Boden -> setOf(Gift, Gestein)
      Gestein -> setOf(Normal, Feuer, Gift, Flug)
      Kaefer -> setOf(Kampf, Pflanze, Boden)
      Geist -> setOf(Gift, Kaefer) // Korrigiert: Unlicht entfernt!
      Stahl -> setOf(
        Normal,
        Pflanze,
        Eis,
        Flug,
        Psycho,
        Kaefer,
        Gestein,
        Drache,
        Fee
      )

      Feuer -> setOf(Feuer, Pflanze, Eis, Kaefer, Stahl)
      Wasser -> setOf(Feuer, Wasser, Eis, Stahl)
      Pflanze -> setOf(Wasser, Elektro, Pflanze, Boden)
      Elektro -> setOf(Elektro, Flug, Stahl)
      Psycho -> setOf(Kampf, Psycho)
      Eis -> setOf(Eis)
      Drache -> setOf(Wasser, Elektro, Pflanze, Feuer)
      Unlicht -> setOf(Geist, Unlicht)
      Fee -> setOf(Kampf, Kaefer, Unlicht)
    }

  }

  fun notEffective(): Set<Typ> {
    return when (this) {
      Normal -> setOf(Geist)
      Geist -> setOf(Normal, Kampf)
      Flug -> setOf(Boden)
      Boden -> setOf(Elektro)
      Stahl -> setOf(Gift)
      Psycho -> setOf(Unlicht)
      Fee -> setOf(Drache)
      else -> emptySet()
    }
  }

  fun getEffectivity(attack: Attack): Float {
    return if (
      this.strength().contains(attack.getType())) {
      0.5f
    } else if (weaknesses().contains(attack.getType())) {
      2.0f
    } else if (notEffective().contains(attack.getType())) {
      0.0f
    } else {
      1.0f
    }
  }

  companion object {
    fun getFromString(name: String): Typ {
      return when (name) {
        "Normal" -> Normal
        "Kampf" -> Kampf
        "Flug" -> Flug
        "Gift" -> Gift
        "Stahl" -> Stahl
        "Fee" -> Fee
        "Wasser" -> Wasser
        "Drache" -> Drache
        "Unlicht" -> Unlicht
        "Feuer" -> Feuer
        "Eis" -> Eis
        "Boden" -> Boden
        "Elektro" -> Elektro
        "Kaefer" -> Kaefer
        "Pflanze" -> Pflanze
        "Geist" -> Geist
        "Psycho" -> Psycho
        "Gestein" -> Gestein
        else -> throw NotAllowedTyp(
          "$name is not an allowed type. " +
              "The following types are possible:" + "Normal; Kampf; Flug; Gift; " +
              "Stahl; Fee; Wasser; Drache; Unlicht; Feuer; Eis; Boden; Elektro; " +
              "Kaefer;" + " Pflanze; Geist; Psycho; Gestein "
        )
      }
    }
  }
}