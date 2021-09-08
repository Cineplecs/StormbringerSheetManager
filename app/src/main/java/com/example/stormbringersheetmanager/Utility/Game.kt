package com.example.stormbringersheetmanager.Utility

data class Game(
    var gameName : String? = "",
    var gamePassword : String? = "",
    var gameMaster : String? = "",
    var players : ArrayList<String>? = ArrayList()
){
    constructor() : this(
        "",
        "",
        "",
        ArrayList<String>()
    )
}
