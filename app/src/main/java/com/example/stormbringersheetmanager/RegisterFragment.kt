package com.example.stormbringersheetmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.Navigation
import com.example.stormbringersheetmanager.Utility.Users
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.net.PasswordAuthentication

class RegisterFragment : Fragment() {

    private lateinit var username : EditText
    private lateinit var mail : EditText
    private lateinit var password : EditText
    private lateinit var confirmRegister : Button
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAuth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        if(mAuth.currentUser != null){
            Navigation.findNavController(view).navigate(R.id.accountFragment)
        }

        username = view.findViewById(R.id.usernameEditText)
        mail = view.findViewById(R.id.emailEditText)
        password = view.findViewById(R.id.passwordEditText)
        confirmRegister = view.findViewById(R.id.confirmRegisterButton)

        confirmRegister.setOnClickListener(){
            mAuth.createUserWithEmailAndPassword(mail.text.toString(), password.text.toString())
                .addOnCompleteListener(requireActivity()){ task ->
                    if(task.isSuccessful){
                        val user = Users(username.text.toString(), mail.text.toString(), password.text.toString())

                        FirebaseDatabase.getInstance().reference.child("Users")
                            .child(FirebaseAuth.getInstance().currentUser!!.uid)
                            .setValue(user)
                    }
                }

            Navigation.findNavController(view).navigate(R.id.registerToLogin)
        }

        return view
    }

}