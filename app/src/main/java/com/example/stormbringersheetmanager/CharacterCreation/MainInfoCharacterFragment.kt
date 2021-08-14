package com.example.stormbringersheetmanager.CharacterCreation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentTransaction
import com.example.stormbringersheetmanager.R

class MainInfoCharacterFragment : Fragment() {

    private lateinit var nationalityTextView : TextView
    private lateinit var buttonProsegui : Button
    private lateinit var nameEditText: EditText
    private lateinit var playerEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_main_info_character, container, false)

        nationalityTextView = view.findViewById(R.id.nationalityTextView)
        buttonProsegui = view.findViewById(R.id.buttonProsegui)
        nameEditText = view.findViewById(R.id.NameEditText)
        playerEditText = view.findViewById(R.id.playerEditText)

        val ageSpinner = view.findViewById<Spinner>(R.id.ageSpinner)
        val nationalitySpinner = view.findViewById<Spinner>(R.id.nationalitySpinner)
        val genderSpinner = view.findViewById<Spinner>(R.id.genderSpinner)

        val genderNames = arrayOf("Selezionare un genere", "Male", "Female")
        val nationalityNames = arrayOf(
            "Selezionare una nazionalità",
            "Melniboné", "Pan Tang", "Myrrhyn", "Dharijor", "Jharkor", "Shazaar", "Tarkesh", "Vilmir",
            "Ilmiora", "Nadsokor", "Solitudine Piangente", "Eshmir", "Isola delle Città Purpuree",
            "Argimiliar", "Pikarayd", "Lormyr", "Filkhar", "Oin", "Yu", "Org"
        )
        val ageYears = arrayOfNulls<Int>(90)
        spinnerFill(ageYears)

        //val nationalityDescription

        val ageAdapter = ArrayAdapter<Int>(requireContext(), android.R.layout.simple_spinner_item, ageYears)
        val genderAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genderNames)
        val nationalityAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, nationalityNames)

        ageSpinner.adapter = ageAdapter
        genderSpinner.adapter = genderAdapter
        nationalitySpinner.adapter = nationalityAdapter

        nationalitySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var nationalityString : String = nationalitySpinner.selectedItem.toString()
                when(nationalityString){
                    "Selezionare una nazionalità" ->
                        nationalityTextView.text = resources.getString(R.string.NationalityTextViewStandard)
                    "Melniboné" ->
                        nationalityTextView.text = resources.getString(R.string.Melniboné)
                    "Pan Tang" ->
                        nationalityTextView.text = resources.getString(R.string.Pan_Tang)
                    "Myrrhyn" ->
                        nationalityTextView.text = resources.getString(R.string.Myrrhyn)
                    "Dharijor" ->
                        nationalityTextView.text = resources.getString(R.string.Dharijor)
                    "Jharkor" ->
                        nationalityTextView.text = resources.getString(R.string.Jharkor)
                    "Shazaar" ->
                        nationalityTextView.text = resources.getString(R.string.Shazaar)
                    "Tarkesh" ->
                        nationalityTextView.text = resources.getString(R.string.Tarkesh)
                    "Vilmir" ->
                        nationalityTextView.text = resources.getString(R.string.Vilmir)
                    "Ilmiora" ->
                        nationalityTextView.text = resources.getString(R.string.Ilmiora)
                    "Nadsokor" ->
                        nationalityTextView.text = resources.getString(R.string.Nadsokor)
                    "Solitudine Piangente" ->
                        nationalityTextView.text = resources.getString(R.string.Solitudine_Piangente)
                    "Eshmir" ->
                        nationalityTextView.text = resources.getString(R.string.Eshmir)
                    "Isola delle Città Purpuree" ->
                        nationalityTextView.text = resources.getString(R.string.Isola_delle_Città_Purpuree)
                    "Argimiliar" ->
                        nationalityTextView.text = resources.getString(R.string.Argimillar)
                    "Pikarayd" ->
                        nationalityTextView.text = resources.getString(R.string.Pikarayd)
                    "Lormyr" ->
                        nationalityTextView.text = resources.getString(R.string.Lormyr)
                    "Filkhar" ->
                        nationalityTextView.text = resources.getString(R.string.Filkhar)
                    "Oin" ->
                        nationalityTextView.text = resources.getString(R.string.Oin)
                    "Yu" ->
                        nationalityTextView.text = resources.getString(R.string.Yu)
                    "Org" ->
                        nationalityTextView.text = resources.getString(R.string.Org)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


        }

        buttonProsegui.setOnClickListener(){
            if(!nameEditText.text.isNullOrEmpty()){
                if(!playerEditText.text.isNullOrEmpty()){
                    if(!nationalitySpinner.selectedItem.equals("Selezionare una nazionalità")){
                        if(!genderSpinner.selectedItem.equals("Selezionare un genere")){
                            var bundle : Bundle = Bundle()
                            bundle.putString("nationality", nationalitySpinner.selectedItem.toString())
                            bundle.putString("gender", genderSpinner.selectedItem.toString())
                            bundle.putInt("age", ageSpinner.selectedItem.toString().toInt())
                            bundle.putString("characterName", nameEditText.text.toString())
                            bundle.putString("playerName", playerEditText.text.toString())
                            var nextFragment = ClassAndSkillsSelection()
                            nextFragment.arguments = bundle
                            var fragmentTransaction : FragmentTransaction = parentFragmentManager.beginTransaction()
                            fragmentTransaction.replace(
                                R.id.fragment_container,
                                nextFragment
                            ).commit()
                        } else {Toast.makeText(requireContext(), "Non è stato selezionato un genere!", Toast.LENGTH_SHORT).show()}
                    } else {Toast.makeText(requireContext(), "Non è stata selezionata una nazionalità", Toast.LENGTH_SHORT).show()}
                } else {Toast.makeText(requireContext(), "Non è stato selezionato un nome per il giocatore!", Toast.LENGTH_SHORT).show()}
            } else {Toast.makeText(requireContext(), "Non è stato selezionato un nome per il personaggio!", Toast.LENGTH_SHORT).show()}
        }

        return view
    }

    private fun spinnerFill(array : Array<Int?>){
        for(i in 1..array.size){
            array[i - 1] = i + 9
        }
    }
}