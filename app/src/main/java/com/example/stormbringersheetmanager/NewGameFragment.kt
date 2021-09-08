package com.example.stormbringersheetmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation

class NewGameFragment : Fragment() {

    private lateinit var newGameCreationButton : Button
    private lateinit var joinGame : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_game, container, false)

        newGameCreationButton = view.findViewById(R.id.newGameCreationButton)
        joinGame = view.findViewById(R.id.joinGame)

        newGameCreationButton.setOnClickListener(){
            Navigation.findNavController(view).navigate(R.id.toGameCreation)
        }

        joinGame.setOnClickListener(){
            Navigation.findNavController(view).navigate(R.id.toGameJoin)
        }

        return view
    }
}