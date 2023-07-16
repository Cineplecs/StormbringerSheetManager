package com.example.stormbringersheetmanager

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import com.example.stormbringersheetmanager.CharacterCreation.MainInfoCharacterFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import android.widget.ArrayAdapter as ArrayAdapter

class VaultFragment : Fragment() {

    private lateinit var createButton : Button
    private lateinit var viewButton : Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view : View = inflater.inflate(R.layout.fragment_vault, container, false)

        mAuth = Firebase.auth

        if(mAuth.currentUser == null){
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.loginFragment)
            Toast.makeText(requireContext(), "Login necessario", Toast.LENGTH_SHORT).show()
        }

        createButton = view.findViewById(R.id.createButton)
        viewButton = view.findViewById(R.id.viewButton)

        createButton.setOnClickListener(){
            Navigation.findNavController(view).navigate(R.id.VaultToCreation)
        }

        viewButton.setOnClickListener(){
            Navigation.findNavController(view).navigate(R.id.vaultToList)
        }

        return view
    }
}