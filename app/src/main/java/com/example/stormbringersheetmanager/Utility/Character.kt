package com.example.stormbringersheetmanager.Utility

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Character(
    var characterName: String?,
    var characterAge: Int?,
    var characterGender: String?,
    var playerName: String?,
    var nationality : String?,
    var finalClass : ArrayList<String>?,
    var cult : String?,
    var elan : Int?,
    var stats : ArrayList<Int>?,
    var height : Int?,
    var weight : Int?,
    var description : String?,
    var armor : String?,
    var armorProtection : Int?,
    var graveWounds : Int?,
    var hp : Int?,
    var availableHP : Int?,
    var equipment : String?,
    var money : Int?,
    var handicap : String?,
    var weapons : ArrayList<Weapon>?,
    var notes : String?,
    var agility : Int?,
    var agilitySkills : ArrayList<Skills>?,
    var communication : Int?,
    var communicationSkills : ArrayList<Skills>?,
    var knowledge : Int?,
    var knowledgeSkills : ArrayList<Skills>?,
    var readAndWrite : String?,
    var readAndWriteSkills : ArrayList<Skills>?,
    var manipulation : Int?,
    var manipulationSkills : ArrayList<Skills>?,
    var perception : Int?,
    var perceptionSkills : ArrayList<Skills>?,
    var stealth : Int?,
    var stealthSkills : ArrayList<Skills>?,
    var availableInt : Int?,
    var evocation : Boolean?,
    var availableEvocations : String?
) {

}