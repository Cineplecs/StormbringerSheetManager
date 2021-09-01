package com.example.stormbringersheetmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.math.log
import com.google.firebase.database.DatabaseError

import com.google.firebase.database.DataSnapshot
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {

    private lateinit var loginMail : EditText
    private lateinit var loginPassword : EditText
    private lateinit var loginButton : Button
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAuth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        if(mAuth.currentUser != null){
            println("SEI DENTRO" + mAuth.uid)
            Firebase.auth.signOut()
            println("PORCA TROIA" + mAuth.uid)
            Toast.makeText(requireContext(), "Login già effettuato", Toast.LENGTH_SHORT).show()
        }

        loginMail = view.findViewById(R.id.loginMail)
        loginPassword = view.findViewById(R.id.loginPassword)
        loginButton = view.findViewById(R.id.loginButton)

        loginButton.setOnClickListener(){

            mAuth.signInWithEmailAndPassword(loginMail.text.toString(), loginPassword.text.toString())
                .addOnCompleteListener(requireActivity()){ task ->
                    if(task.isSuccessful){
                        Toast.makeText(requireContext(), "Login effettuato", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "Non è stato effettuato il login", Toast.LENGTH_SHORT).show()
                    }
                }

        }

        return view
    }

}