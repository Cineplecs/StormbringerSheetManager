package com.example.stormbringersheetmanager

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class GamesFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var newGameButton : Button
    private lateinit var gameListButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_games, container, false)

        mAuth = Firebase.auth
        newGameButton = view.findViewById(R.id.newGameButton)
        gameListButton = view.findViewById(R.id.gameListButton)

        if(mAuth.currentUser == null){
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.loginFragment)
            Toast.makeText(requireContext(), "Login necessario", Toast.LENGTH_SHORT).show()
        }

        newGameButton.setOnClickListener() {
            Navigation.findNavController(view).navigate(R.id.gamesToNewGame)
        }

        gameListButton.setOnClickListener(){
            Navigation.findNavController(view).navigate(R.id.gamesToGameList)
        }

        return view
    }
}