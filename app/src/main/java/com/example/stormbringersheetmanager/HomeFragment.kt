package com.example.stormbringersheetmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private lateinit var gamesButton: Button
    private lateinit var vaultButton: Button
    private lateinit var accountButton: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        mAuth = Firebase.auth

        if (mAuth.currentUser == null) {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.accountFragment)
            Toast.makeText(requireContext(), "Login necessario", Toast.LENGTH_SHORT).show()
        }

        gamesButton = view.findViewById(R.id.gamesButton)
        vaultButton = view.findViewById(R.id.vaultButton)
        accountButton = view.findViewById(R.id.accountButton)

        gamesButton.setOnClickListener() {
            Navigation.findNavController(view).navigate(R.id.gamesFragment)
        }

        vaultButton.setOnClickListener() {
            Navigation.findNavController(view).navigate(R.id.vaultFragment)
        }

        accountButton.setOnClickListener() {
            Navigation.findNavController(view).navigate(R.id.accountFragment)
        }

        return view
    }

}