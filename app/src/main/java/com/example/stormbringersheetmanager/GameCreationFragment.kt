package com.example.stormbringersheetmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.stormbringersheetmanager.Utility.Game
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class GameCreation : Fragment() {

    private lateinit var gameNameText : EditText
    private lateinit var gamePasswordText: EditText
    private lateinit var gameCreationConfirmButton : Button
    private lateinit var mAuth : FirebaseAuth
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_creation, container, false)

        gameNameText = view.findViewById(R.id.gameNameText)
        gamePasswordText = view.findViewById(R.id.gamePasswordText)
        gameCreationConfirmButton = view.findViewById(R.id.gameCreationConfirmButton)
        mAuth = Firebase.auth
        database = FirebaseDatabase.getInstance("https://stormbringersheetmanager-default-rtdb.europe-west1.firebasedatabase.app/").reference

        var masterName = ""
        var players = ArrayList<String>()

        database.child("Users").child(mAuth.uid!!).get().addOnSuccessListener { it ->
            masterName = it.child("username").value.toString()
        }.addOnFailureListener(){
            println("NON HA FUNZIONATO")
        }

        gameCreationConfirmButton.setOnClickListener(){
            var game = Game(
                gameNameText.text.toString(),
                gamePasswordText.text.toString(),
                masterName,
                players
            )
            database.child("Games").child(gameNameText.text.toString()).setValue(game)
        }

        return view
    }
}