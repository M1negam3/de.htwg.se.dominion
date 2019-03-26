case class MoneyCard (cost: Int, value:Int) {
}

val moneyCard1 = MoneyCard(0,1)
moneyCard1.cost
moneyCard1.value

val moneyCard2 = MoneyCard(3,2)
moneyCard2.cost
moneyCard2.value

val moneyCard3 = MoneyCard(6,3)
moneyCard3.cost
moneyCard3.value

case class UselessCard (cost: Int, text: String) {
}

val uselessCard1 = UselessCard(3 ,"Aktion: +1 " + "Kauf: +1")
uselessCard1.text
uselessCard1.cost

case class HandCards (handCards: Vector[MoneyCard])

val handCards1 = HandCards(Vector(moneyCard1, moneyCard2))
handCards1.handCards