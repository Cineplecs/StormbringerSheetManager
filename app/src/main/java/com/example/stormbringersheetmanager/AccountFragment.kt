package com.example.stormbringersheetmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isGone
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class AccountFragment : Fragment() {

    private lateinit var mAuth : FirebaseAuth
    private lateinit var database : DatabaseReference
    private lateinit var usernameText : TextView
    private lateinit var toLoginButton : Button
    private lateinit var toRegisterButton : Button
    private lateinit var gameCountTextView : TextView
    private lateinit var charCountTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAuth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        usernameText = view.findViewById(R.id.usernameText)
        toLoginButton = view.findViewById(R.id.goingToLoginButton)
        toRegisterButton = view.findViewById(R.id.goingToRegisterButton)
        gameCountTextView = view.findViewById(R.id.gameCountTextView)
        charCountTextView = view.findViewById(R.id.charCountTextVIew)

        if(mAuth.currentUser != null){
            charCountTextView.isGone = false
            gameCountTextView.isGone = false
            usernameText.isGone = false
            toLoginButton.isGone = true
            toRegisterButton.isGone = true
            var gameCount = 0
            var charCount = 0

            database = FirebaseDatabase.getInstance("https://stormbringersheetmanager-default-rtdb.europe-west1.firebasedatabase.app/").reference

            database.child("Users").child(mAuth.uid!!).get().addOnSuccessListener { it ->
                val username = it.child("username").value.toString()
                usernameText.text = username
            }.addOnFailureListener(){
                println("NON HA FUNZIONATO")
            }

        } else {
            charCountTextView.isGone = true
            gameCountTextView.isGone = true
            usernameText.isGone = true
            toLoginButton.isGone = false
            toRegisterButton.isGone = false

            toLoginButton.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.loginFragment)
            }

            toRegisterButton.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.registerFragment)
            }
        }

        return view
    }


}