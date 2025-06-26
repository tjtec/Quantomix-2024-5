package hwr.oop.quantomix.memory

import hwr.oop.quantomix.fight.logic.BattleFundamentals
import hwr.oop.quantomix.fight.logic.ModeOfDamageCalculation
import hwr.oop.quantomix.fight.objects.BattleStats
import hwr.oop.quantomix.monster.Quantomix
import hwr.oop.quantomix.objects.Coach
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonClassDiscriminator
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import java.io.File

public var filePath = "game_state.json"

@Serializable
@JsonClassDiscriminator("type")
sealed interface GameStateComponent


@Serializable
data class TrainerData(val trainer: Coach) : GameStateComponent

@Serializable
data class BattleFundamentalsData(val value: BattleFundamentalsDTO) : GameStateComponent

@Serializable
data class BattleMapData(
  val battleStatsList: List<BattleStatsEntry>,
) : GameStateComponent

@Serializable
data class BattleStatsEntry(
  val quantomix: Quantomix,
  val battleStats: BattleStats,
)


val gameModule = SerializersModule {
  polymorphic(GameStateComponent::class) {
    subclass(TrainerData::class)
    subclass(BattleMapData::class)
  }
}

val gameJson = Json {
  serializersModule = gameModule
  prettyPrint = true
  encodeDefaults = true
  ignoreUnknownKeys = true
}

fun Save(
  trainer1: Coach,
  trainer2: Coach,
  battleStats: MutableMap<Quantomix, BattleStats>,

) {
  val battleFundamentalsDTO=BattleFundamentalsDTO(numberOfQuantomixInRound = BattleFundamentals.numberOfQuantomixInRound,
    damageStrategy = BattleFundamentals.damageStrategy)
  val battleStatsEntries = battleStats.map { (quantomix, stats) ->
    BattleStatsEntry(quantomix, stats)
  }

  val gameStateComponents: List<GameStateComponent> = listOf(
    TrainerData(trainer1),
    TrainerData(trainer2),
    BattleMapData(battleStatsEntries),
    BattleFundamentalsData(battleFundamentalsDTO)
  )

  val jsonString = gameJson.encodeToString(gameStateComponents)
  File(filePath).writeText(jsonString)
}

fun Load(): Triple<Coach?, Coach?, MutableMap<Quantomix, BattleStats>?> {
  var loadedTrainer1: Coach? = null
  var loadedTrainer2: Coach? = null
  var loadedBattleStatsMap: MutableMap<Quantomix, BattleStats>? = null
  var tempBattleFundamentalsDTO = BattleFundamentalsDTO()

  if (!File(filePath).exists()) {
    println("Game state file not found. Returning nulls.")
    return Triple(null, null, null)
  }
  /* val json
  val dto = Json.decodeFromString<BattleFundamentalsDTO>(json)
  BattleFundamentals.numberOfQuantomixInRound = dto.numberOfQuantomixInRound
  BattleFundamentals.damageStrategy = dto.damageStrategy
*/

  val jsonString = File(filePath).readText()
  val deserializedComponents =
    gameJson.decodeFromString<List<GameStateComponent>>(jsonString)

  deserializedComponents.forEach { component ->
    when (component) {
      is TrainerData -> {
        if (loadedTrainer1 == null) loadedTrainer1 =
          component.trainer else loadedTrainer2 = component.trainer
      }

      is BattleMapData -> {
        loadedBattleStatsMap =
          component.battleStatsList.associateBy { it.quantomix }
            .mapValues { it.value.battleStats }.toMutableMap()
      }
      is BattleFundamentalsData -> {
        tempBattleFundamentalsDTO = component.value
      }
    }
  }
  BattleFundamentals.damageStrategy= tempBattleFundamentalsDTO.damageStrategy
  BattleFundamentals.numberOfQuantomixInRound=tempBattleFundamentalsDTO.numberOfQuantomixInRound
  return Triple(loadedTrainer1, loadedTrainer2, loadedBattleStatsMap)
}