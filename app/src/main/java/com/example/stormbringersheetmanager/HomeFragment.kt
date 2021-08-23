package com.example.stormbringersheetmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation

class HomeFragment : Fragment() {

    private lateinit var gamesButton: Button
    private lateinit var vaultButton: Button
    private lateinit var accountButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        gamesButton = view.findViewById(R.id.gamesButton)
        vaultButton = view.findViewById(R.id.vaultButton)
        accountButton = view.findViewById(R.id.accountButton)

        gamesButton.setOnClickListener(){
            Navigation.findNavController(view).navigate(R.id.gamesFragment)
        }

        vaultButton.setOnClickListener(){
            Navigation.findNavController(view).navigate(R.id.vaultFragment)
        }

        accountButton.setOnClickListener(){
            Navigation.findNavController(view).navigate(R.id.accountFragment)
        }

        return view
    }

}