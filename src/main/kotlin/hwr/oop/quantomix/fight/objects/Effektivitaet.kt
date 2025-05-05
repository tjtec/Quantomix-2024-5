package hwr.oop.quantomix.fight.objects

data class Effektivitaet(
    public var Klassen: Map<String, Int> = mapOf(
        "Normal" to 1,
        "Kampf" to 2,
        "Flug" to 3,
        "Gift" to 4,
        "Boden" to 5,
        "Gestein" to 6,
        "Käfer" to 7,
        "Geist" to 8,
        "Stahl" to 9,
        "Feuer" to 10,
        "Wasser" to 11,
        "Pflanze" to 12,
        "Elektro" to 13,
        "Psycho" to 14,
        "Eis" to 15,
        "Drache" to 16,
        "Unlicht" to 17,
        "Fee" to 18
    )
)