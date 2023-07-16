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
    private lateinit var indirizzoMail : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAuth = Firebase.auth

        println("Arrivo almeno a qui?")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        if(mAuth.currentUser == null){
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.loginFragment)
        }

        usernameText = view.findViewById(R.id.usernameText)
        toLoginButton = view.findViewById(R.id.goingToLoginButton)
        toRegisterButton = view.findViewById(R.id.goingToRegisterButton)
        indirizzoMail = view.findViewById(R.id.indirizzoMail)

        if(mAuth.currentUser != null){
            indirizzoMail.isGone = false
            usernameText.isGone = false
            toLoginButton.isGone = true
            toRegisterButton.isGone = true
            var gameCount = 0
            var charCount = 0

            database = FirebaseDatabase.getInstance("https://stormbringersheetmanager-default-rtdb.europe-west1.firebasedatabase.app/").reference



            database.child("Users").child(mAuth.uid!!).get().addOnSuccessListener { it ->
                val username = it.child("username").value.toString()
                usernameText.text = username
                val mail = it.child("mail").value.toString()
                indirizzoMail.text = mail
            }.addOnFailureListener(){
                println("NON HA FUNZIONATO")
            }

        } else {
            indirizzoMail.isGone = true
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