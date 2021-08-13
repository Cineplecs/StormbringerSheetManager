package com.example.stormbringersheetmanager.CharacterCreation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.example.stormbringersheetmanager.R

class MainInfoCharacterFragment : Fragment() {

    private lateinit var nationalityTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view : View = inflater.inflate(R.layout.fragment_main_info_character, container, false)

        nationalityTextView = view.findViewById(R.id.nationalityTextView)

        val ageSpinner = view.findViewById<Spinner>(R.id.ageSpinner)
        val nationalitySpinner = view.findViewById<Spinner>(R.id.nationalitySpinner)
        val genderSpinner = view.findViewById<Spinner>(R.id.genderSpinner)

        val genderNames = arrayOf("Select a gender", "Male", "Female")
        val nationalityNames = arrayOf(
            "Selezionare una nazionalità",
            "Melniboné", "Pan Tang", "Myrrhyn", "Dharijor", "Jharkor", "Shazaar", "Tarkesh", "Vilmir",
            "Ilmiora", "Nadsokor", "Solitudine Piangente", "Eshmir", "Isola delle Città Purpuree",
            "Argimiliar", "Pikarayd", "Lormyr", "Filkhar", "Oin", "Yu", "Org"
        )
        val ageYears = arrayOfNulls<Int>(90)
        spinnerFill(ageYears)

        //val nationalityDescription

        var ageAdapter = ArrayAdapter<Int>(requireContext(), android.R.layout.simple_spinner_item, ageYears)
        var genderAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genderNames)
        var nationalityAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, nationalityNames)

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
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


        }

        return view
    }

    private fun spinnerFill(array : Array<Int?>){
        for(i in 1..array.size){
            array[i - 1] = i + 9
        }
    }
}