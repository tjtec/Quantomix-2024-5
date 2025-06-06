package hwr.oop.quantomix.objects

import hwr.oop.quantomix.fight.objects.Attack
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat

class TypTest : AnnotationSpec() {
  @BeforeEach
  fun attackGestein(): Attack {
    return Attack(
      attackName = "Gestein",
      type = Typ.Gestein,
      damage = 50,
      damageQuote = 50,
      specialAttack = false
    )
  }

  @BeforeEach
  fun attackStahl(): Attack {
    return Attack(
      attackName = "Stahl",
      type = Typ.Stahl,
      damage = 50,
      damageQuote = 50,
      specialAttack = false
    )
  }

  @BeforeEach
  fun attackGeist(): Attack {
    return Attack(
      attackName = "Geist",
      type = Typ.Geist,
      damage = 50,
      damageQuote = 50,
      specialAttack = false
    )
  }

  @BeforeEach
  fun attackNormal(): Attack {
    return Attack(
      attackName = "Normal",
      type = Typ.Normal,
      damage = 50,
      damageQuote = 50,
      specialAttack = false
    )
  }

  @BeforeEach
  fun attackEis(): Attack {
    return Attack(
      attackName = "Eis",
      type = Typ.Eis,
      damage = 50,
      damageQuote = 50,
      specialAttack = false
    )
  }

  @BeforeEach
  fun attackUnlicht(): Attack {
    return Attack(
      attackName = "Unlicht",
      type = Typ.Unlicht,
      damage = 50,
      damageQuote = 50,
      specialAttack = false
    )
  }

  @BeforeEach
  fun attackFlug(): Attack {
    return Attack(
      attackName = "Flug",
      type = Typ.Flug,
      damage = 50,
      damageQuote = 50,
      specialAttack = false
    )
  }

  @BeforeEach
  fun attackGift(): Attack {
    return Attack(
      attackName = "Gift",
      type = Typ.Gift,
      damage = 50,
      damageQuote = 50,
      specialAttack = false
    )
  }

  @BeforeEach
  fun attackKaefer(): Attack {
    return Attack(
      attackName = "Kaefer",
      type = Typ.Kaefer,
      damage = 50,
      damageQuote = 50,
      specialAttack = false
    )
  }

  @BeforeEach
  fun attackPsycho(): Attack {
    return Attack(
      attackName = "Psycho",
      type = Typ.Psycho,
      damage = 50,
      damageQuote = 50,
      specialAttack = false
    )
  }

  @BeforeEach
  fun attackFee(): Attack {
    return Attack(
      attackName = "Fee",
      type = Typ.Fee,
      damage = 50,
      damageQuote = 50,
      specialAttack = false
    )
  }

  @BeforeEach
  fun attackKampf(): Attack {
    return Attack(
      attackName = "Kampf",
      type = Typ.Kampf,
      damage = 50,
      damageQuote = 50,
      specialAttack = false
    )
  }

  @BeforeEach
  fun attackPflanze(): Attack {
    return Attack(
      attackName = "Pflanze",
      type = Typ.Pflanze,
      damage = 50,
      damageQuote = 50,
      specialAttack = false
    )
  }

  @BeforeEach
  fun attackElektro(): Attack {
    return Attack(
      attackName = "Elektro",
      type = Typ.Elektro,
      damage = 50,
      damageQuote = 50,
      specialAttack = false
    )
  }

  @BeforeEach
  fun attackBoden(): Attack {
    return Attack(
      attackName = "Boden",
      type = Typ.Boden,
      damage = 50,
      damageQuote = 50,
      specialAttack = false
    )
  }

  @BeforeEach
  fun attackFeuer(): Attack {
    return Attack(
      attackName = "Feuer",
      type = Typ.Feuer,
      damage = 50,
      damageQuote = 50,
      specialAttack = false
    )
  }

  @BeforeEach
  fun attackWasser(): Attack {
    return Attack(
      attackName = "Wasser",
      type = Typ.Wasser,
      damage = 50,
      damageQuote = 50,
      specialAttack = false
    )
  }

  @BeforeEach
  fun attackDrache(): Attack {
    return Attack(
      attackName = "Drache",
      type = Typ.Drache,
      damage = 50,
      damageQuote = 50,
      specialAttack = false
    )
  }

  @Test
  fun `test Attacktyp Normal`() {
    assertThat(Typ.Gestein.getEffectivity(attackNormal())).isEqualTo(0.5f)
    assertThat(Typ.Stahl.getEffectivity(attackNormal())).isEqualTo(0.5f)
    assertThat(Typ.Geist.getEffectivity(attackNormal())).isEqualTo(0.0f)
    assertThat(Typ.Normal.getEffectivity(attackNormal())).isEqualTo(1.0f)
  }

  @Test
  fun `test Attacktyp Kampf`() {
    assertThat(Typ.Normal.getEffectivity(attackKampf())).isEqualTo(2.0f)
    assertThat(Typ.Gestein.getEffectivity(attackKampf())).isEqualTo(2.0f)
    assertThat(Typ.Stahl.getEffectivity(attackKampf())).isEqualTo(2.0f)
    assertThat(Typ.Eis.getEffectivity(attackKampf())).isEqualTo(2.0f)
    assertThat(Typ.Unlicht.getEffectivity(attackKampf())).isEqualTo(2.0f)
    assertThat(Typ.Flug.getEffectivity(attackKampf())).isEqualTo(0.5f)
    assertThat(Typ.Gift.getEffectivity(attackKampf())).isEqualTo(0.5f)
    assertThat(Typ.Kaefer.getEffectivity(attackKampf())).isEqualTo(0.5f)
    assertThat(Typ.Psycho.getEffectivity(attackKampf())).isEqualTo(0.5f)
    assertThat(Typ.Fee.getEffectivity(attackKampf())).isEqualTo(0.5f)
    assertThat(Typ.Geist.getEffectivity(attackKampf())).isEqualTo(0.0f)
    assertThat(Typ.Wasser.getEffectivity(attackKampf())).isEqualTo(1.0f)
  }

  @Test
  fun `test Attacktyp Flug`() {
    assertThat(Typ.Kampf.getEffectivity(attackFlug())).isEqualTo(2.0f)
    assertThat(Typ.Kaefer.getEffectivity(attackFlug())).isEqualTo(2.0f)
    assertThat(Typ.Pflanze.getEffectivity(attackFlug())).isEqualTo(2.0f)
    assertThat(Typ.Gestein.getEffectivity(attackFlug())).isEqualTo(0.5f)
    assertThat(Typ.Stahl.getEffectivity(attackFlug())).isEqualTo(0.5f)
    assertThat(Typ.Elektro.getEffectivity(attackFlug())).isEqualTo(0.5f)
    assertThat(Typ.Wasser.getEffectivity(attackFlug())).isEqualTo(1.0f)
  }

  @Test
  fun `test Attacktyp Gift`() {
    assertThat(Typ.Pflanze.getEffectivity(attackGift())).isEqualTo(2.0f)
    assertThat(Typ.Fee.getEffectivity(attackGift())).isEqualTo(2.0f)
    assertThat(Typ.Gift.getEffectivity(attackGift())).isEqualTo(0.5f)
    assertThat(Typ.Boden.getEffectivity(attackGift())).isEqualTo(0.5f)
    assertThat(Typ.Gestein.getEffectivity(attackGift())).isEqualTo(0.5f)
    assertThat(Typ.Geist.getEffectivity(attackGift())).isEqualTo(0.5f)
    assertThat(Typ.Stahl.getEffectivity(attackGift())).isEqualTo(0.0f)
    assertThat(Typ.Wasser.getEffectivity(attackGift())).isEqualTo(1.0f)

  }

  @Test
  fun `test Attacktyp Boden`() {
    assertThat(Typ.Gift.getEffectivity(attackBoden())).isEqualTo(2.0f)
    assertThat(Typ.Gestein.getEffectivity(attackBoden())).isEqualTo(2.0f)
    assertThat(Typ.Stahl.getEffectivity(attackBoden())).isEqualTo(2.0f)
    assertThat(Typ.Feuer.getEffectivity(attackBoden())).isEqualTo(2.0f)
    assertThat(Typ.Elektro.getEffectivity(attackBoden())).isEqualTo(2.0f)
    assertThat(Typ.Kaefer.getEffectivity(attackBoden())).isEqualTo(0.5f)
    assertThat(Typ.Pflanze.getEffectivity(attackBoden())).isEqualTo(0.5f)
    assertThat(Typ.Flug.getEffectivity(attackBoden())).isEqualTo(0.0f)
    assertThat(Typ.Wasser.getEffectivity(attackBoden())).isEqualTo(1.0f)
  }

  @Test
  fun `test Attacktyp Gestein`() {
    assertThat(Typ.Flug.getEffectivity(attackGestein())).isEqualTo(2.0f)
    assertThat(Typ.Kaefer.getEffectivity(attackGestein())).isEqualTo(2.0f)
    assertThat(Typ.Feuer.getEffectivity(attackGestein())).isEqualTo(2.0f)
    assertThat(Typ.Eis.getEffectivity(attackGestein())).isEqualTo(2.0f)
    assertThat(Typ.Kampf.getEffectivity(attackGestein())).isEqualTo(0.5f)
    assertThat(Typ.Boden.getEffectivity(attackGestein())).isEqualTo(0.5f)
    assertThat(Typ.Stahl.getEffectivity(attackGestein())).isEqualTo(0.5f)
    assertThat(Typ.Wasser.getEffectivity(attackGestein())).isEqualTo(1.0f)
  }

  @Test
  fun `test Attacktyp Kaefer`() {
    assertThat(Typ.Pflanze.getEffectivity(attackKaefer())).isEqualTo(2.0f)
    assertThat(Typ.Psycho.getEffectivity(attackKaefer())).isEqualTo(2.0f)
    assertThat(Typ.Kampf.getEffectivity(attackKaefer())).isEqualTo(0.5f)
    assertThat(Typ.Flug.getEffectivity(attackKaefer())).isEqualTo(0.5f)
    assertThat(Typ.Geist.getEffectivity(attackKaefer())).isEqualTo(0.5f)
    assertThat(Typ.Stahl.getEffectivity(attackKaefer())).isEqualTo(0.5f)
    assertThat(Typ.Feuer.getEffectivity(attackKaefer())).isEqualTo(0.5f)
    assertThat(Typ.Fee.getEffectivity(attackKaefer())).isEqualTo(0.5f)
    assertThat(Typ.Wasser.getEffectivity(attackKaefer())).isEqualTo(1.0f)
  }

  @Test
  fun `test Attacktyp Geist`() {
    assertThat(Typ.Geist.getEffectivity(attackGeist())).isEqualTo(2.0f)
    assertThat(Typ.Psycho.getEffectivity(attackGeist())).isEqualTo(2.0f)
    assertThat(Typ.Unlicht.getEffectivity(attackGeist())).isEqualTo(0.5f)
    assertThat(Typ.Normal.getEffectivity(attackGeist())).isEqualTo(0.0f)
    assertThat(Typ.Wasser.getEffectivity(attackGeist())).isEqualTo(1.0f)
  }

  @Test
  fun `test Attacktyp Stahl`() {
    assertThat(Typ.Gestein.getEffectivity(attackStahl())).isEqualTo(2.0f)
    assertThat(Typ.Eis.getEffectivity(attackStahl())).isEqualTo(2.0f)
    assertThat(Typ.Fee.getEffectivity(attackStahl())).isEqualTo(2.0f)
    assertThat(Typ.Feuer.getEffectivity(attackStahl())).isEqualTo(0.5f)
    assertThat(Typ.Wasser.getEffectivity(attackStahl())).isEqualTo(0.5f)
    assertThat(Typ.Elektro.getEffectivity(attackStahl())).isEqualTo(0.5f)
    assertThat(Typ.Normal.getEffectivity(attackStahl())).isEqualTo(1.0f)
  }

  @Test
  fun `test Attacktyp Feuer`() {
    assertThat(Typ.Kaefer.getEffectivity(attackFeuer())).isEqualTo(2.0f)
    assertThat(Typ.Stahl.getEffectivity(attackFeuer())).isEqualTo(2.0f)
    assertThat(Typ.Pflanze.getEffectivity(attackFeuer())).isEqualTo(2.0f)
    assertThat(Typ.Eis.getEffectivity(attackFeuer())).isEqualTo(2.0f)
    assertThat(Typ.Gestein.getEffectivity(attackFeuer())).isEqualTo(0.5f)
    assertThat(Typ.Feuer.getEffectivity(attackFeuer())).isEqualTo(0.5f)
    assertThat(Typ.Wasser.getEffectivity(attackFeuer())).isEqualTo(0.5f)
    assertThat(Typ.Drache.getEffectivity(attackFeuer())).isEqualTo(0.5f)
    assertThat(Typ.Normal.getEffectivity(attackFeuer())).isEqualTo(1.0f)
  }

  @Test
  fun `test Attacktyp Wasser`() {
    assertThat(Typ.Boden.getEffectivity(attackWasser())).isEqualTo(2.0f)
    assertThat(Typ.Gestein.getEffectivity(attackWasser())).isEqualTo(2.0f)
    assertThat(Typ.Feuer.getEffectivity(attackWasser())).isEqualTo(2.0f)
    assertThat(Typ.Wasser.getEffectivity(attackWasser())).isEqualTo(0.5f)
    assertThat(Typ.Pflanze.getEffectivity(attackWasser())).isEqualTo(0.5f)
    assertThat(Typ.Drache.getEffectivity(attackWasser())).isEqualTo(0.5f)
    assertThat(Typ.Normal.getEffectivity(attackWasser())).isEqualTo(1.0f)
  }

  @Test
  fun `test Attacktyp Pflanze`() {
    assertThat(Typ.Boden.getEffectivity(attackPflanze())).isEqualTo(2.0f)
    assertThat(Typ.Gestein.getEffectivity(attackPflanze())).isEqualTo(2.0f)
    assertThat(Typ.Wasser.getEffectivity(attackPflanze())).isEqualTo(2.0f)
    assertThat(Typ.Flug.getEffectivity(attackPflanze())).isEqualTo(0.5f)
    assertThat(Typ.Gift.getEffectivity(attackPflanze())).isEqualTo(0.5f)
    assertThat(Typ.Kaefer.getEffectivity(attackPflanze())).isEqualTo(0.5f)
    assertThat(Typ.Stahl.getEffectivity(attackPflanze())).isEqualTo(0.5f)
    assertThat(Typ.Feuer.getEffectivity(attackPflanze())).isEqualTo(0.5f)
    assertThat(Typ.Pflanze.getEffectivity(attackPflanze())).isEqualTo(0.5f)
    assertThat(Typ.Drache.getEffectivity(attackPflanze())).isEqualTo(0.5f)
    assertThat(Typ.Normal.getEffectivity(attackPflanze())).isEqualTo(1.0f)
  }

  @Test
  fun `test Attacktyp Elektro`() {
    assertThat(Typ.Flug.getEffectivity(attackElektro())).isEqualTo(2.0f)
    assertThat(Typ.Wasser.getEffectivity(attackElektro())).isEqualTo(2.0f)
    assertThat(Typ.Pflanze.getEffectivity(attackElektro())).isEqualTo(0.5f)
    assertThat(Typ.Elektro.getEffectivity(attackElektro())).isEqualTo(0.5f)
    assertThat(Typ.Drache.getEffectivity(attackElektro())).isEqualTo(0.5f)
    assertThat(Typ.Boden.getEffectivity(attackElektro())).isEqualTo(0.0f)
    assertThat(Typ.Normal.getEffectivity(attackElektro())).isEqualTo(1.0f)
  }

  @Test
  fun `test Attacktyp Psycho`() {
    assertThat(Typ.Kampf.getEffectivity(attackPsycho())).isEqualTo(2.0f)
    assertThat(Typ.Gift.getEffectivity(attackPsycho())).isEqualTo(2.0f)
    assertThat(Typ.Stahl.getEffectivity(attackPsycho())).isEqualTo(0.5f)
    assertThat(Typ.Psycho.getEffectivity(attackPsycho())).isEqualTo(0.5f)
    assertThat(Typ.Normal.getEffectivity(attackPsycho())).isEqualTo(1.0f)
  }

  @Test
  fun `test Attacktyp Eis`() {
    assertThat(Typ.Flug.getEffectivity(attackEis())).isEqualTo(2.0f)
    assertThat(Typ.Boden.getEffectivity(attackEis())).isEqualTo(2.0f)
    assertThat(Typ.Pflanze.getEffectivity(attackEis())).isEqualTo(2.0f)
    assertThat(Typ.Drache.getEffectivity(attackEis())).isEqualTo(2.0f)
    assertThat(Typ.Stahl.getEffectivity(attackEis())).isEqualTo(0.5f)
    assertThat(Typ.Feuer.getEffectivity(attackEis())).isEqualTo(0.5f)
    assertThat(Typ.Wasser.getEffectivity(attackEis())).isEqualTo(0.5f)
    assertThat(Typ.Eis.getEffectivity(attackEis())).isEqualTo(0.5f)
    assertThat(Typ.Normal.getEffectivity(attackEis())).isEqualTo(1.0f)
  }

  @Test
  fun `test Attacktyp Drache`() {
    assertThat(Typ.Drache.getEffectivity(attackDrache())).isEqualTo(2.0f)
    assertThat(Typ.Stahl.getEffectivity(attackDrache())).isEqualTo(0.5f)
    assertThat(Typ.Fee.getEffectivity(attackDrache())).isEqualTo(0.0f)
    assertThat(Typ.Normal.getEffectivity(attackDrache())).isEqualTo(1.0f)
  }

  @Test
  fun `test Attacktyp Unlicht`() {
    assertThat(Typ.Geist.getEffectivity(attackUnlicht())).isEqualTo(2.0f)
    assertThat(Typ.Psycho.getEffectivity(attackUnlicht())).isEqualTo(2.0f)
    assertThat(Typ.Kampf.getEffectivity(attackUnlicht())).isEqualTo(0.5f)
    assertThat(Typ.Unlicht.getEffectivity(attackUnlicht())).isEqualTo(0.5f)
    assertThat(Typ.Fee.getEffectivity(attackUnlicht())).isEqualTo(0.5f)
    assertThat(Typ.Normal.getEffectivity(attackUnlicht())).isEqualTo(1.0f)
  }

  @Test
  fun `test Attacktyp Fee`() {
    assertThat(Typ.Kampf.getEffectivity(attackFee())).isEqualTo(2.0f)
    assertThat(Typ.Drache.getEffectivity(attackFee())).isEqualTo(2.0f)
    assertThat(Typ.Unlicht.getEffectivity(attackFee())).isEqualTo(2.0f)
    assertThat(Typ.Gift.getEffectivity(attackFee())).isEqualTo(0.5f)
    assertThat(Typ.Stahl.getEffectivity(attackFee())).isEqualTo(0.5f)
    assertThat(Typ.Normal.getEffectivity(attackFee())).isEqualTo(1.0f)
  }
}