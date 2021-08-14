package com.example.stormbringersheetmanager

object DiceShots {
    fun D100() : Int {
        var diceShot : Int = (1..100).random()
        return diceShot
    }

    fun D6() : Int {
        var diceShot : Int = (1..6).random()
        return diceShot
    }

    fun D8() : Int {
        var diceShot : Int = (1..8).random()
        return diceShot
    }

    fun D10() : Int {
        var diceShot : Int = (1..10).random()
        return diceShot
    }

    fun D20() : Int {
        var diceShot : Int = (1..20).random()
        return diceShot
    }

    fun D4() : Int {
        var diceShot : Int = (1..4).random()
        return diceShot
    }
}