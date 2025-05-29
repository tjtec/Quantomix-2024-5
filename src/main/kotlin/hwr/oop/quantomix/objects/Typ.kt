package hwr.oop.quantomix.objects

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

    fun getEffectivity(attack: Attack): Float {
        when (attack.getType()) {
            Normal -> return when (this) {
                Gestein -> 0.5f
                Stahl -> 0.5f
                Geist -> 0.0f
                else -> 1.0f
            }

            Kampf -> return when (this) {
                Normal -> 2.0f
                Gestein -> 2.0f
                Stahl -> 2.0f
                Eis -> 2.0f
                Unlicht -> 2.0f
                Flug -> 0.5f
                Gift -> 0.5f
                Kaefer -> 0.5f
                Psycho -> 0.5f
                Fee -> 0.5f
                Geist -> 0.0f
                else -> 1.0f
            }

            Flug -> return when (this) {
                Kampf -> 2.0f
                Kaefer -> 2.0f
                Pflanze -> 2.0f
                Gestein -> 0.5f
                Stahl -> 0.5f
                Elektro -> 0.5f
                else -> 1.0f
            }

            Gift -> return when (this) {
                Pflanze -> 2.0f
                Fee -> 2.0f
                Gift -> 0.5f
                Boden -> 0.5f
                Gestein -> 0.5f
                Geist -> 0.5f
                Stahl -> 0.0f
                else -> 1.0f
            }

            Boden -> return when (this) {
                Gift -> 2.0f
                Gestein -> 2.0f
                Stahl -> 2.0f
                Feuer -> 2.0f
                Elektro -> 2.0f
                Kaefer -> 0.5f
                Pflanze -> 0.5f
                Flug -> 0.0f
                else -> 1.0f
            }

            Gestein -> return when (this) {
                Flug -> 2.0f
                Kaefer -> 2.0f
                Feuer -> 2.0f
                Eis -> 2.0f
                Kampf -> 0.5f
                Boden -> 0.5f
                Stahl -> 0.5f
                else -> 1.0f
            }

            Kaefer -> return when (this) {
                Pflanze -> 2.0f
                Psycho -> 2.0f
                Unlicht -> 2.0f
                Kampf -> 0.5f
                Flug -> 0.5f
                Gift -> 0.5f
                Geist -> 0.5f
                Stahl -> 0.5f
                Feuer -> 0.5f
                Fee -> 0.5f
                else -> 1.0f
            }

            Geist -> return when (this) {
                Geist -> 2.0f
                Psycho -> 2.0f
                Unlicht -> 0.5f
                Normal -> 0.0f
                else -> 1.0f
            }

            Stahl -> return when (this) {
                Gestein -> 2.0f
                Eis -> 2.0f
                Fee -> 2.0f
                Stahl -> 0.5f
                Feuer -> 0.5f
                Wasser -> 0.5f
                Elektro -> 0.5f
                else -> 1.0f
            }

            Feuer -> return when (this) {
                Kaefer -> 2.0f
                Stahl -> 2.0f
                Pflanze -> 2.0f
                Eis -> 2.0f
                Gestein -> 0.5f
                Feuer -> 0.5f
                Wasser -> 0.5f
                Drache -> 0.5f
                else -> 1.0f
            }

            Wasser -> return when (this) {
                Boden -> 2.0f
                Gestein -> 2.0f
                Feuer -> 2.0f
                Wasser -> 0.5f
                Pflanze -> 0.5f
                Drache -> 0.5f
                else -> 1.0f
            }

            Pflanze -> return when (this) {
                Boden -> 2.0f
                Gestein -> 2.0f
                Wasser -> 2.0f
                Flug -> 0.5f
                Gift -> 0.5f
                Kaefer -> 0.5f
                Stahl -> 0.5f
                Feuer -> 0.5f
                Pflanze -> 0.5f
                Drache -> 0.5f
                else -> 1.0f
            }

            Elektro -> return when (this) {
                Flug -> 2.0f
                Wasser -> 2.0f
                Pflanze -> 0.5f
                Elektro -> 0.5f
                Drache -> 0.5f
                Boden -> 0.0f
                else -> 1.0f
            }

            Psycho -> return when (this) {
                Kampf -> 2.0f
                Gift -> 2.0f
                Stahl -> 0.5f
                Psycho -> 0.5f
                Unlicht -> 0.0f
                else -> 1.0f
            }

            Eis -> return when (this) {
                Flug -> 2.0f
                Boden -> 2.0f
                Pflanze -> 2.0f
                Drache -> 2.0f
                Stahl -> 0.5f
                Feuer -> 0.5f
                Wasser -> 0.5f
                Eis -> 0.5f
                else -> 1.0f
            }

            Drache -> return when (this) {
                Drache -> 2.0f
                Stahl -> 0.5f
                Fee -> 0.0f
                else -> 1.0f
            }

            Unlicht -> return when (this) {
                Geist -> 2.0f
                Psycho -> 2.0f
                Kampf -> 0.5f
                Unlicht -> 0.5f
                Fee -> 0.5f
                else -> 1.0f
            }

            Fee -> return when (this) {
                Kampf -> 2.0f
                Drache -> 2.0f
                Unlicht -> 2.0f
                Gift -> 0.5f
                Stahl -> 0.5f
                Feuer -> 0.5f
                else -> 1.0f
            }
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
                else -> Normal
            }
        }
    }
}
//class Typ(val name: String)