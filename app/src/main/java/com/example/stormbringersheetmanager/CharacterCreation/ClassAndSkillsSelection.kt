package com.example.stormbringersheetmanager.CharacterCreation

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.stormbringersheetmanager.DiceShots
import com.example.stormbringersheetmanager.R

class ClassAndSkillsSelection : Fragment() {

    private lateinit var TextINT : TextView
    private lateinit var ButtonINT : Button
    private lateinit var TextFOR : TextView
    private lateinit var ButtonFOR : Button
    private lateinit var TextCOS : TextView
    private lateinit var ButtonCOS : Button
    private lateinit var TextDES : TextView
    private lateinit var ButtonDES : Button
    private lateinit var TextFAS : TextView
    private lateinit var ButtonFAS : Button
    private lateinit var TextMAN : TextView
    private lateinit var ButtonMAN : Button
    private lateinit var TextTAG : TextView
    private lateinit var ButtonTAG : Button
    private lateinit var classText : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view : View = inflater.inflate(R.layout.fragment_class_and_skills_selection, container, false)

        val finalClass = ArrayList<String>()

        TextINT = view.findViewById(R.id.TextViewINT)
        ButtonINT = view.findViewById(R.id.ButtonINT)
        TextFOR = view.findViewById(R.id.TextViewFOR)
        ButtonFOR = view.findViewById(R.id.ButtonFOR)
        TextDES = view.findViewById(R.id.TextViewDES)
        ButtonDES = view.findViewById(R.id.ButtonDES)
        TextMAN = view.findViewById(R.id.TextViewMAN)
        ButtonMAN = view.findViewById(R.id.ButtonMAN)
        TextCOS = view.findViewById(R.id.TextViewCOS)
        ButtonCOS = view.findViewById(R.id.ButtonCOS)
        TextFAS = view.findViewById(R.id.TextViewFAS)
        ButtonFAS = view.findViewById(R.id.ButtonFAS)
        TextTAG = view.findViewById(R.id.TextViewTAG)
        ButtonTAG = view.findViewById(R.id.ButtonTAG)
        classText = view.findViewById(R.id.classText)

        classText.isGone = true

        var FOR = 0
        var COS = 0
        var INT = 0
        var MAN = 0
        var DES = 0
        var FAS = 0
        var TAG = 0

        val bundle : Bundle = requireArguments()
        var age = bundle.getInt("age")
        var gender = bundle.getString("gender")
        var characterName = bundle.getString("characterName")
        var playerName = bundle.getString("playerName")
        val linearLayout = view.findViewById<LinearLayout>(R.id.linearLayoutVerticalClassMain)

        ButtonFOR.setOnClickListener{
            for(i in 1..3){
                FOR += DiceShots.D6()
            }
            ButtonFOR.isClickable = false
            TextFOR.text = FOR.toString()
            statCheck(linearLayout, finalClass, bundle)
        }

        ButtonCOS.setOnClickListener{
            for(i in 1..3){
                COS += DiceShots.D6()
            }
            ButtonCOS.isClickable = false
            TextCOS.text = COS.toString()
            statCheck(linearLayout, finalClass, bundle)
        }

        ButtonINT.setOnClickListener{
            for(i in 1..3){
                INT += DiceShots.D6()
            }
            ButtonINT.isClickable = false
            TextINT.text = INT.toString()
            statCheck(linearLayout, finalClass, bundle)
        }

        ButtonMAN.setOnClickListener {
            for(i in 1..3){
                MAN += DiceShots.D6()
            }
            ButtonMAN.isClickable = false
            TextMAN.text = MAN.toString()
            statCheck(linearLayout, finalClass, bundle)
        }

        ButtonDES.setOnClickListener{
            for(i in 1..3){
                DES += DiceShots.D6()
            }
            ButtonDES.isClickable = false
            TextDES.text = DES.toString()
            statCheck(linearLayout, finalClass, bundle)
        }

        ButtonFAS.setOnClickListener {
            for(i in 1..3){
                FAS += DiceShots.D6()
            }
            ButtonFAS.isClickable = false
            TextFAS.text = FAS.toString()
            statCheck(linearLayout, finalClass, bundle)
        }

        ButtonTAG.setOnClickListener {
            for(i in 1..3){
                TAG += DiceShots.D6()
            }
            ButtonTAG.isClickable = false
            TextTAG.text = TAG.toString()
            statCheck(linearLayout, finalClass, bundle)
        }

        return view
    }

    private fun nationalityChoice(linearLayout : LinearLayout, finalClass: ArrayList<String>, bundle: Bundle) {

        val FORBACK = TextFOR.text.toString().toInt()
        val COSBACK = TextCOS.text.toString().toInt()
        val INTBACK = TextINT.text.toString().toInt()
        val MANBACK = TextMAN.text.toString().toInt()
        val DESBACK = TextDES.text.toString().toInt()
        val FASBACK = TextFAS.text.toString().toInt()
        val TAGBACK = TextTAG.text.toString().toInt()


        val spinner = Spinner(requireContext())
        spinner.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        spinner.gravity = Gravity.CENTER
        val nationalityNames = arrayOf(
            "Selezionare una nazionalità",
            "Melniboné", "Pan Tang", "Myrrhyn", "Dharijor", "Jharkor", "Shazaar", "Tarkesh", "Vilmir",
            "Ilmiora", "Nadsokor", "Solitudine Piangente", "Eshmir", "Isola delle Città Purpuree",
            "Argimiliar", "Pikarayd", "Lormyr", "Filkhar", "Oin", "Yu", "Org"
        )

        val nationalityAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, nationalityNames)
        spinner.adapter = nationalityAdapter
        linearLayout.addView(spinner)

        val nationalityTextView = TextView(requireContext())
        nationalityTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
        nationalityTextView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        nationalityTextView.setPadding(15, 15, 15, 15)
        nationalityTextView.gravity = Gravity.CENTER
        linearLayout.addView(nationalityTextView)

        val corpText = TextView(requireContext())
        corpText.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        corpText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
        corpText.gravity = Gravity.CENTER
        corpText.setTextColor(getResources().getColor(android.R.color.black))
        corpText.setPadding(15, 15, 15, 15)

        val corpSpinner = Spinner(requireContext())
        corpSpinner.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        val corpNames = arrayOf("longilinea", "normale", "robusta")
        val corpNamesShortHigh = arrayOf("normale", "robusta")
        val corpNamesShortLow = arrayOf("longilinea", "normale")

        val confirmButton = Button(requireContext())
        confirmButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
        confirmButton.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        confirmButton.setPadding(15, 15, 15, 15)
        confirmButton.text = "Conferma"
        confirmButton.gravity = Gravity.CENTER
        confirmButton.setOnClickListener{
            spinner.isEnabled = false
            corpSpinner.isEnabled = false
        }

        corpSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(corpText.isVisible){
                    linearLayout.removeView(corpText)
                }
                if(confirmButton.isVisible){
                    linearLayout.removeView(confirmButton)
                }
                if(bundle.containsKey("corp")){
                    bundle.remove("corp")
                }
                if(corpText.isVisible){
                    linearLayout.removeView(corpText)
                }
                bundle.putString("corp", corpSpinner.selectedItem.toString())
                corpText.text = "Corporatura\n" + corpSpinner.selectedItem.toString()
                linearLayout.addView(corpText)
                linearLayout.addView(confirmButton)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                classText.isGone = false

                if(confirmButton.isVisible){
                    linearLayout.removeView(confirmButton)
                }
                if(corpSpinner.isVisible){
                    linearLayout.removeView(corpSpinner)
                }
                if(bundle.containsKey("corp")){
                    bundle.remove("corp")
                }
                if(corpText.isVisible){
                    linearLayout.removeView(corpText)
                }

                TextFOR.text = FORBACK.toString()
                TextINT.text = INTBACK.toString()
                TextCOS.text = COSBACK.toString()
                TextMAN.text = MANBACK.toString()
                TextTAG.text = TAGBACK.toString()
                TextDES.text = DESBACK.toString()
                TextFAS.text = FASBACK.toString()

                when(spinner.selectedItem.toString()){
                    "Selezionare una nazionalità" -> {
                        nationalityTextView.text = "Selezionare una nazionalità"
                        classText.text = "Selezionare prima una nazionalità"
                        println(classText.text)
                    }
                    "Melniboné" ->{
                        nationalityTextView.text = resources.getString(R.string.Melniboné)
                        finalClass.clear()
                        finalClass.add("Nobile")
                        finalClass.add("Guerriero")
                        warriorClass(finalClass)
                        TextINT.text = (TextINT.text.toString().toInt() + DiceShots.D10()).toString()
                        TextMAN.text = (TextMAN.text.toString().toInt() + (DiceShots.D6() + DiceShots.D6())).toString()
                        TextTAG.text = (TextTAG.text.toString().toInt() + 3).toString()
                        classText.text = finalClassToString(finalClass)
                        corpText.text = "Corporatura longilinea"
                        bundle.putString("corp", "longilinea")
                        linearLayout.addView(corpText)
                        linearLayout.addView(confirmButton)
                    }
                    "Pan Tang" -> {
                        nationalityTextView.text = resources.getString(R.string.Pan_Tang)
                        finalClass.clear()
                        TextINT.text = (TextINT.text.toString().toInt() + DiceShots.D8()).toString()
                        TextMAN.text = (TextMAN.text.toString().toInt() + DiceShots.D8()).toString()
                        TextTAG.text = (TextTAG.text.toString().toInt() + 1).toString()
                        if((TextINT.text.toString().toInt() + TextMAN.text.toString().toInt()) >= 32){
                            finalClass.add("Sacerdote/Stregone")
                        } else {
                            finalClass.add("Guerriero")
                            warriorClass(finalClass)
                        }
                        if(DiceShots.D100() in 1..20){
                            finalClass.add("Nobile")
                        }
                        classText.text = finalClassToString(finalClass)
                        corpText.text = "Corporatura robusta"
                        bundle.putString("corp", "robusta")
                        linearLayout.addView(corpText)
                        linearLayout.addView(confirmButton)
                    }
                    "Myrrhyn" -> {
                        nationalityTextView.text = resources.getString(R.string.Myrrhyn)
                        finalClass.clear()
                        TextINT.text = (TextINT.text.toString().toInt() + DiceShots.D6()).toString()
                        TextMAN.text = (TextMAN.text.toString().toInt() + DiceShots.D6()).toString()
                        if(bundle.get("gender")!! == "Femmina"){
                            TextFAS.text = (TextFAS.text.toString().toInt() + DiceShots.D6()).toString()
                        }
                        if(TextTAG.text.toString().toInt() >= 9){
                            TextTAG.text = (TextTAG.text.toString().toInt() - 2).toString()
                        }
                        finalClass.add(classChoice())
                        println(finalClassToString(finalClass))
                        for(i in 0 until finalClass.size){
                            if(finalClass[i] == "Marinaio" ||
                                finalClass[i] == "Ladro" ||
                                finalClass[i] == "Mendicante"
                            ){
                                finalClass.removeAt(i)
                                finalClass.add("Guerriero")
                                println(finalClassToString(finalClass))
                                warriorClass(finalClass)
                            }
                        }
                        warriorClass(finalClass)
                        merchantClass(finalClass)
                        sailorClass(finalClass)
                        if((TextINT.text.toString().toInt() + TextMAN.text.toString().toInt()) >= 32){
                            finalClass.add("Stregone")
                        }
                        classText.text = finalClassToString(finalClass)
                        corpText.text = "Corporatura longilinea"
                        bundle.putString("corp", "longilinea")
                        linearLayout.addView(corpText)
                        linearLayout.addView(confirmButton)
                    }
                    "Dharijor" -> {
                        classText.text = resources.getString(R.string.Default_Class_Text)
                        nationalityTextView.text = resources.getString(R.string.Dharijor)
                        TextCOS.text = (TextCOS.text.toString().toInt() + DiceShots.D4()).toString()
                        finalClass.clear()
                        finalClass.add(classChoice())
                        warriorClass(finalClass)
                        merchantClass(finalClass)
                        sailorClass(finalClass)
                        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, corpNames)
                        corpSpinner.adapter = arrayAdapter
                        linearLayout.addView(corpSpinner)
                        linearLayout.addView(confirmButton)
                    }
                    "Jharkor" -> {
                        nationalityTextView.text = resources.getString(R.string.Jharkor)
                        finalClass.clear()
                        var corp = corpRoll()
                        corpText.text = "Corporatura\n" + corp
                        bundle.putString("corp", corp)
                        linearLayout.addView(corpText)
                        linearLayout.addView(confirmButton)
                    }
                    "Shazaar" -> {
                        nationalityTextView.text = resources.getString(R.string.Shazaar)
                        //TODO
                        finalClass.clear()
                        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, corpNames)
                        corpSpinner.adapter = arrayAdapter
                        linearLayout.addView(corpSpinner)
                        linearLayout.addView(confirmButton)
                    }
                    "Tarkesh" -> {
                        nationalityTextView.text = resources.getString(R.string.Tarkesh)
                        //TODO
                        finalClass.clear()
                        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, corpNamesShortHigh)
                        corpSpinner.adapter = arrayAdapter
                        linearLayout.addView(corpSpinner)
                        linearLayout.addView(confirmButton)
                    }
                    "Vilmir" -> {
                        nationalityTextView.text = resources.getString(R.string.Vilmir)
                        //TODO
                        finalClass.clear()
                        val corpTemp = corpRollAlt()
                        corpText.text = corpTemp
                        linearLayout.addView(corpText)
                        linearLayout.addView(confirmButton)
                    }
                    "Ilmiora" -> {
                        nationalityTextView.text = resources.getString(R.string.Ilmiora)
                        //TODO
                        finalClass.clear()
                        val corpTemp = corpRollAlt()
                        corpText.text = corpTemp
                        bundle.putString("corp", corpTemp)
                        linearLayout.addView(corpText)
                        linearLayout.addView(confirmButton)
                    }
                    "Nadsokor" -> {
                        nationalityTextView.text = resources.getString(R.string.Nadsokor)
                        //TODO
                        finalClass.clear()
                        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, corpNames)
                        corpSpinner.adapter = arrayAdapter
                        linearLayout.addView(corpSpinner)
                        linearLayout.addView(confirmButton)
                    }
                    "Solitudine Piangente" -> {
                        nationalityTextView.text =
                            resources.getString(R.string.Solitudine_Piangente)
                        //TODO
                        finalClass.clear()
                        val corpTemp = "normale"
                        bundle.putString("corp", corpTemp)
                        corpText.text = corpTemp
                        linearLayout.addView(corpText)
                        linearLayout.addView(confirmButton)
                    }
                    "Eshmir" -> {
                        nationalityTextView.text = resources.getString(R.string.Eshmir)
                        //TODO
                        finalClass.clear()
                        val corpTemp = "normale"
                        bundle.putString("corp", corpTemp)
                        corpText.text = corpTemp
                        linearLayout.addView(corpText)
                        linearLayout.addView(confirmButton)
                    }
                    "Isola delle Città Purpuree" -> {
                        nationalityTextView.text =
                            resources.getString(R.string.Isola_delle_Città_Purpuree)
                        //TODO
                        finalClass.clear()
                        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, corpNamesShortHigh)
                        corpSpinner.adapter = arrayAdapter
                        linearLayout.addView(corpSpinner)
                        linearLayout.addView(confirmButton)
                    }
                    "Argimiliar" -> {
                        nationalityTextView.text = resources.getString(R.string.Argimillar)
                        //TODO
                        finalClass.clear()
                        val corpTemp = "normale"
                        bundle.putString("corp", corpTemp)
                        corpText.text = corpTemp
                        linearLayout.addView(corpText)
                        linearLayout.addView(confirmButton)
                    }
                    "Pikarayd" -> {
                        nationalityTextView.text = resources.getString(R.string.Pikarayd)
                        //TODO
                        finalClass.clear()
                        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, corpNamesShortHigh)
                        corpSpinner.adapter = arrayAdapter
                        linearLayout.addView(corpSpinner)
                        linearLayout.addView(confirmButton)
                    }
                    "Lormyr" -> {
                        nationalityTextView.text = resources.getString(R.string.Lormyr)
                        //TODO
                        finalClass.clear()
                        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, corpNamesShortHigh)
                        corpSpinner.adapter = arrayAdapter
                        linearLayout.addView(corpSpinner)
                        linearLayout.addView(confirmButton)
                    }
                    "Filkhar" -> {
                        nationalityTextView.text = resources.getString(R.string.Filkhar)
                        //TODO
                        finalClass.clear()
                        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, corpNamesShortLow)
                        corpSpinner.adapter = arrayAdapter
                        linearLayout.addView(corpSpinner)
                        linearLayout.addView(confirmButton)
                    }
                    "Oin" -> {
                        nationalityTextView.text = resources.getString(R.string.Oin)
                        //TODO
                        finalClass.clear()
                        val corpTemp = "robusta"
                        bundle.putString("corp", corpTemp)
                        corpText.text = corpTemp
                        linearLayout.addView(corpText)
                        linearLayout.addView(confirmButton)
                    }
                    "Yu" -> {
                        nationalityTextView.text = resources.getString(R.string.Yu)
                        //TODO
                        finalClass.clear()
                        val corpTemp = "robusta"
                        bundle.putString("corp", corpTemp)
                        corpText.text = corpTemp
                        linearLayout.addView(corpText)
                        linearLayout.addView(confirmButton)
                    }
                    "Org" -> {
                        nationalityTextView.text = resources.getString(R.string.Org)
                        //TODO
                        finalClass.clear()
                        val corpTemp = "robusta"
                        bundle.putString("corp", corpTemp)
                        corpText.text = corpTemp
                        linearLayout.addView(corpText)
                        linearLayout.addView(confirmButton)
                    }
                }
                classText.text = finalClassToString(finalClass)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    }

    private fun statCheck(linearLayout : LinearLayout, finalClass: ArrayList<String>, bundle: Bundle){
        if(
            !TextFOR.text.equals("FOR Stat") &&
            !TextCOS.text.equals("COS Stat") &&
            !TextINT.text.equals("INT Stat") &&
            !TextMAN.text.equals("MAN Stat") &&
            !TextDES.text.equals("DES Stat") &&
            !TextFAS.text.equals("FAS Stat") &&
            !TextTAG.text.equals("TAG Stat")
        ) {
            nationalityChoice(linearLayout, finalClass, bundle)
        }
    }

    private fun warriorClass(finalClass: ArrayList<String>){
        var subClass : Int = DiceShots.D10()
        if(subClass in (9..10)){
            for(i in 0..(finalClass.size - 1)){
                if(finalClass[i] == "Guerriero"){
                    finalClass.add("Assassino")
                }
            }
        }
    }

    private fun merchantClass(finalClass: ArrayList<String>){
        var subClass : Int = DiceShots.D10()
        if(subClass in (8..10)){
            for(i in 0..(finalClass.size - 1)){
                if(finalClass[i] == "Mercante"){
                    finalClass.add("Viaggiatore")
                }
            }
        } else {
            for(i in 0..(finalClass.size - 1)){
                if(finalClass[i].equals("Mercante")){
                    finalClass.add("Bottegaio")
                }
            }
        }
    }

    private fun sailorClass(finalClass: ArrayList<String>){
        var subClass : Int = DiceShots.D10()
        if(subClass == 9){
            for(i in 0..(finalClass.size - 1)){
                if(finalClass[i].equals("Marinaio")){
                    finalClass.add("Nostromo")
                }
            }
        } else if (subClass == 10){
            for(i in 0..(finalClass.size - 1)){
                if(finalClass[i].equals("Marinaio")){
                    finalClass.add("Capitano")
                }
            }
        }
    }

    private fun corpRoll() : String{
        var corp : String = ""
        val diceShot : Int = DiceShots.D6()
        when(diceShot){
            1,2 -> corp = "longilinea"
            3,4 -> corp = "normale"
            5,6 -> corp = "robusta"
        }
        return corp
    }

    private fun corpRollAlt() : String{
        var corp : String = ""
        val diceShot : Int = DiceShots.D6()
        when(diceShot){
            1 -> corp = "longilinea"
            in (2..5) -> corp = "normale"
            6 -> corp = "robusta"
        }
        return corp
    }

    private fun finalClassToString(finalClass: ArrayList<String>) : String{
        var finalString = ""
        for(i in 0..(finalClass.size-1)){
            if(i == (finalClass.size - 1)){
                finalString += finalClass[i]
            } else {
                finalString += finalClass[i] + "\n"
            }
        }
        return finalString
    }

    private fun classChoice() : String {
        var classString = ""
        val tiroClasse: Int = DiceShots.D100()
        when (tiroClasse) {
            in 1..20 -> {
                classString = "Guerriero"
            }
            in 21..30 -> {
                classString = "Mercante"
            }
            in 31..45 -> {
                classString = "Marinaio"
            }
            in 46..60 -> {
                classString = "Cacciatore"
            }
            in 61..65 -> {
                classString = "Agricoltore"
            }
            in 66..70 -> {
                classString = "Sacerdote"
            }
            in 71..75 -> {
                classString = "Nobile"
            }
            in 76..85 -> {
                classString = "Ladro"
            }
            in 86..90 -> {
                classString = "Mendicante"
            }
            in 91..100 -> {
                classString = "Artigiano"
            }
        }
        return classString
    }
}