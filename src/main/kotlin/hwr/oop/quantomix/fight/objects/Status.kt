package hwr.oop.quantomix.fight.objects

import java.util.*

enum class Status {
  NoDamage,
  Poison,
  StrongPoison,
  Combustion,
  Paralysis,
  Sleep,
  Confusion,
  Freeze;

  fun calculateStatusEffect(
    battleStats: BattleStats,
    previous: StatusHelper = StatusHelper(),
    setDurationForRounds: Int = Random().nextInt(2, 5),
    selfHit: Int = Random().nextInt(0, 100),
    randomValueParalysis: Int = Random().nextInt(0, 100),
  ): StatusHelper {
    return when (this) {
      StrongPoison -> {
        val rounds = previous.alreadyPassedRounds + 1
        val newSummand = if (previous.alreadyPassedRounds <= 0)
          (battleStats.getStats().getKp() / 16)
        else previous.summand * 2
        StatusHelper(
          multiplicator = 1,
          summand = newSummand,
          alreadyPassedRounds = rounds
        )
      }

      Poison -> {
        StatusHelper(
          multiplicator = 1,
          summand = (battleStats.getStats().getKp() / 16),
        )
      }

      Combustion -> {
        StatusHelper(
          multiplicator = 1,
          summand = (battleStats.getStats().getKp() / 8),
        )
      }

      Paralysis -> {
        battleStats.getStats().reduceSpeed(0.5)
        val m =
          UsefulInformationForStatus(previous.alreadyPassedRounds).hitParalysis(
            randomValueParalysis
          )
        StatusHelper(
          multiplicator = m,
          summand = 0,
        )
      }

      Sleep -> {
        val rounds = previous.alreadyPassedRounds + 1
        val effectiveMultiplicator =
          if (previous.alreadyPassedRounds < setDurationForRounds) 0 else 1
        StatusHelper(
          multiplicator = effectiveMultiplicator,
          summand = 0,
          alreadyPassedRounds = rounds
        )
      }

      Confusion -> {
        val rounds = previous.alreadyPassedRounds + 1
        val effectiveMultiplicator = if (
          UsefulInformationForStatus(
            alreadyPassedRounds = previous.alreadyPassedRounds
          ).selfHit(
            selfHit
          ) &&
          UsefulInformationForStatus(
            alreadyPassedRounds = previous.alreadyPassedRounds
          ).roundsWithStatusEffectLeft(
            randomValueDuration = setDurationForRounds
          )
        ) -1 else 1
        StatusHelper(
          multiplicator = effectiveMultiplicator,
          summand = 0,
          alreadyPassedRounds = rounds
        )
      }

      Freeze, NoDamage -> {
        StatusHelper(
          multiplicator = 0,
          summand = 0,
          alreadyPassedRounds = previous.alreadyPassedRounds
        )
      }
    }
  }
}