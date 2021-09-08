package com.example.stormbringersheetmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.example.stormbringersheetmanager.Utility.Character
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class GameJoinFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var joinButton: Button
    private lateinit var gameNameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var characterSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_join, container, false)

        mAuth = Firebase.auth
        database =
            FirebaseDatabase.getInstance("https://stormbringersheetmanager-default-rtdb.europe-west1.firebasedatabase.app/").reference
        joinButton = view.findViewById(R.id.joinButton)
        gameNameInput = view.findViewById(R.id.gameNameInput)
        passwordInput = view.findViewById(R.id.passwordInput)
        characterSpinner = view.findViewById(R.id.characterSpinner)

        var charNames = ArrayList<String>()

        database.child("Char").child(mAuth.uid!!).get().addOnSuccessListener {
            it.children.forEach() { charChild ->
                if (charChild.child("inGame").value != true) {
                    charNames.add(
                        charChild.child("characterName").value.toString()
                    )
                }
            }
            var adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, charNames)
            characterSpinner.adapter = adapter
        }


        var username = ""
        database.child("Users").child(mAuth.uid!!).get().addOnSuccessListener {
            username = it.child("username").value.toString()
        }

        joinButton.setOnClickListener() {
            var gameName = gameNameInput.text.toString()
            var password = passwordInput.text.toString()
            database.child("Games").get().addOnSuccessListener {
                if (it.child(gameName).exists()) {
                    println(it.child(gameName).child("gamePassword").value)
                    if (it.child(gameName).child("gamePassword").value == password) {
                        database.child("Char").get().addOnSuccessListener {
                            database.child("Char").child(mAuth.uid!!)
                                .child(characterSpinner.selectedItem.toString()).child("inGame")
                                .setValue(true)
                            var character = it.child(mAuth.uid!!)
                                .child(characterSpinner.selectedItem.toString()).value
                            var key =
                                database.child("Games").child(gameName).child("players").push()
                            key.child(username).setValue(character)
                            var charKey = it.child(mAuth.uid!!).child(characterSpinner.selectedItem.toString()).ref
                            charKey.child("inGame").setValue(true)
                        }
                    }
                }
            }
        }

        return view
    }
}