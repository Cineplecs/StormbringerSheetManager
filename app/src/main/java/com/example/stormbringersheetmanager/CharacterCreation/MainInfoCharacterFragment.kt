package com.example.stormbringersheetmanager.CharacterCreation

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import com.example.stormbringersheetmanager.R
import com.example.stormbringersheetmanager.Utility.Character
import com.example.stormbringersheetmanager.Utility.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class MainInfoCharacterFragment : Fragment() {

    private lateinit var buttonProsegui: Button
    private lateinit var nameEditText: EditText
    private lateinit var playerEditText: TextView
    private lateinit var database : DatabaseReference
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_main_info_character, container, false)

        mAuth = Firebase.auth
        buttonProsegui = view.findViewById(R.id.buttonProsegui)
        nameEditText = view.findViewById(R.id.NameEditText)
        playerEditText = view.findViewById(R.id.playerEditText)

        database = FirebaseDatabase.getInstance("https://stormbringersheetmanager-default-rtdb.europe-west1.firebasedatabase.app/").reference

        var username = ""

        database.child("Users").child(mAuth.uid!!).get().addOnSuccessListener { it ->
            username = it.child("username").value.toString()
            playerEditText.text = username
        }.addOnFailureListener(){
            println("NON HA FUNZIONATO")
        }


        val ageSpinner = view.findViewById<Spinner>(R.id.ageSpinner)
        val genderSpinner = view.findViewById<Spinner>(R.id.genderSpinner)

        val genderNames = arrayOf("Selezionare un genere", "Maschio", "Femmina")
        val ageYears = arrayOfNulls<Int>(90)
        spinnerFill(ageYears)

        val ageAdapter = ArrayAdapter<Int>(requireContext(), R.layout.spinner_item, ageYears)
        val genderAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, genderNames)

        ageSpinner.adapter = ageAdapter
        genderSpinner.adapter = genderAdapter

        buttonProsegui.setOnClickListener() {
            if (!nameEditText.text.isNullOrEmpty()) {
                if (!playerEditText.text.isNullOrEmpty()) {
                    if (!genderSpinner.selectedItem.equals("Selezionare un genere")) {
                        var bundle: Bundle = Bundle()
                        bundle.putString("gender", genderSpinner.selectedItem.toString())
                        bundle.putInt("age", ageSpinner.selectedItem.toString().toInt())
                        bundle.putString("characterName", nameEditText.text.toString())
                        Navigation.findNavController(view).navigate(R.id.InfoToClass, bundle)

                        /**
                        val database = Firebase.database("https://stormbringersheetmanager-default-rtdb.europe-west1.firebasedatabase.app/").reference
                        val character = Character(
                            nameEditText.text.toString(),
                            ageSpinner.selectedItem.toString().toInt(),
                            genderSpinner.selectedItem.toString(),
                            playerEditText.text.toString()
                        )
                        database.child("Char").child(nameEditText.text.toString()).setValue(character)
                        */

                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Non è stato selezionato un genere!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Non è stato selezionato un nome per il giocatore!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "Non è stato selezionato un nome per il personaggio!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return view
    }

    private fun spinnerFill(array: Array<Int?>) {
        for (i in 1..array.size) {
            array[i - 1] = i + 9
        }
    }
}