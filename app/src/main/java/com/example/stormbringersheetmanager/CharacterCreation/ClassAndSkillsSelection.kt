package com.example.stormbringersheetmanager.CharacterCreation

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
import androidx.fragment.app.FragmentTransaction
import com.example.stormbringersheetmanager.DiceRolls
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
                FOR += DiceRolls.D6()
            }
            ButtonFOR.isClickable = false
            TextFOR.text = FOR.toString()
            statCheck(linearLayout, finalClass, bundle)
        }

        ButtonCOS.setOnClickListener{
            for(i in 1..3){
                COS += DiceRolls.D6()
            }
            ButtonCOS.isClickable = false
            TextCOS.text = COS.toString()
            statCheck(linearLayout, finalClass, bundle)
        }

        ButtonINT.setOnClickListener{
            for(i in 1..3){
                INT += DiceRolls.D6()
            }
            ButtonINT.isClickable = false
            TextINT.text = INT.toString()
            statCheck(linearLayout, finalClass, bundle)
        }

        ButtonMAN.setOnClickListener {
            for(i in 1..3){
                MAN += DiceRolls.D6()
            }
            ButtonMAN.isClickable = false
            TextMAN.text = MAN.toString()
            statCheck(linearLayout, finalClass, bundle)
        }

        ButtonDES.setOnClickListener{
            for(i in 1..3){
                DES += DiceRolls.D6()
            }
            ButtonDES.isClickable = false
            TextDES.text = DES.toString()
            statCheck(linearLayout, finalClass, bundle)
        }

        ButtonFAS.setOnClickListener {
            for(i in 1..3){
                FAS += DiceRolls.D6()
            }
            ButtonFAS.isClickable = false
            TextFAS.text = FAS.toString()
            statCheck(linearLayout, finalClass, bundle)
        }

        ButtonTAG.setOnClickListener {
            for(i in 1..3){
                TAG += DiceRolls.D6()
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

        val nationalityTextView = TextView(requireContext())
        nationalityTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
        nationalityTextView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        nationalityTextView.setPadding(15, 15, 15, 15)
        nationalityTextView.gravity = Gravity.CENTER
        linearLayout.addView(nationalityTextView)

        val nationalityText : TextView = TextView(requireContext())
        nationalityText.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        nationalityText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
        nationalityText.gravity = Gravity.CENTER
        nationalityText.setPadding(15, 15, 15, 15)
        nationalityText.text = "Cliccare sul bottone per selezionare una nazionalità"
        linearLayout.addView(nationalityText)

        val nationalityButton : Button = Button(requireContext())
        nationalityButton.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        nationalityButton.gravity = Gravity.CENTER
        nationalityButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
        nationalityButton.setPadding(15, 15, 15, 15)
        nationalityButton.text = "Nazionalità"
        var nationalityNumber = 0
        var nationality = ""


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
            nationalityButton.isGone = true
            corpSpinner.isEnabled = false
            var bundleFOR = TextFOR.text.toString().toInt()
            var bundleINT = TextINT.text.toString().toInt()
            var bundleCOS = TextCOS.text.toString().toInt()
            var bundleMAN = TextMAN.text.toString().toInt()
            var bundleDES = TextDES.text.toString().toInt()
            var bundleTAG = TextTAG.text.toString().toInt()
            var bundleFAS = TextFAS.text.toString().toInt()
            bundle.putInt("FOR", bundleFOR)
            bundle.putInt("FAS", bundleFAS)
            bundle.putInt("DES", bundleDES)
            bundle.putInt("COS", bundleCOS)
            bundle.putInt("INT", bundleINT)
            bundle.putInt("MAN", bundleMAN)
            bundle.putInt("TAG", bundleTAG)
            bundle.putString("nationality", nationalityText.text.toString())

            bundle.putStringArrayList("class", finalClass)
            var nextFragment = EquipmentSelection()
            nextFragment.arguments = bundle
            var fragmentTransaction : FragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(
                R.id.fragment_container,
                nextFragment
            ).commit()
        }

        val heightSpinner = Spinner(requireContext())
        heightSpinner.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
        )
        //TODO

        var heightWeightPair = heightAndWeight(
            TextTAG.text.toString().toInt(),
            corpText.text.toString()
        )

        var height = heightWeightPair.first
        println("Prova-1")
        for(i in height.indices){
            println(height[i])
        }
        var weigth = heightWeightPair.second
        println("Prova-2")
        for(i in weigth.indices){
            println(weigth[i])
        }
        //TODO

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
                corpText.text = "Corporatura " + corpSpinner.selectedItem.toString()

                var heightWeightPair2 = heightAndWeight(
                    TextTAG.text.toString().toInt(),
                    corpText.text.toString()
                )

                var height = heightWeightPair2.first
                println("Prova1-1")
                for(i in height.indices){
                    println(height[i])
                }
                var weight = heightWeightPair2.second
                println("Prova1-2 " + weight.size)
                for(i in weight.indices){
                    println(weight[i])
                }
                //TODO
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }




        nationalityButton.setOnClickListener(){
            nationalityNumber = DiceRolls.D100()
            when(nationalityNumber){
                in 1..2-> nationality = "Malniboné"
                in 3..5-> nationality = "Pan Tang"
                in 6..8 -> nationality = "Myrrhyn"
                in 9..12 -> nationality = "Dharijor"
                in 13..16 -> nationality = "Jharkor"
                in 17..24 -> nationality = "Shazaar"
                in 25..29 -> nationality = "Tarkesh"
                in 30..37 -> nationality = "Vilmir"
                in 38..44 -> nationality = "Ilmiora"
                in 45..49 -> nationality = "Nadsokor"
                in 50..56 -> nationality = "Solitudine Piangente"
                in 57..60 -> nationality = "Eshmir"
                in 61..67 -> nationality = "Isola delle Città Purpuree"
                in 68..74 -> nationality = "Argimiliar"
                in 75..81 -> nationality = "Pikarayd"
                in 82..88 -> nationality = "Lormyr"
                in 89..95 -> nationality = "Filkhar"
                in 96..97 -> nationality = "Oin"
                in 98..99 -> nationality = "Yu"
                100 -> nationality = "Org"
            }
            nationalityText.text = nationality
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

            when(nationality){
                "Melniboné" ->{
                    nationalityTextView.text = resources.getString(R.string.Melniboné)
                    finalClass.clear()
                    finalClass.add("Nobile")
                    finalClass.add("Guerriero")
                    warriorClass(finalClass)
                    TextINT.text = (TextINT.text.toString().toInt() + DiceRolls.D10()).toString()
                    TextMAN.text = (TextMAN.text.toString().toInt() + (DiceRolls.D6() + DiceRolls.D6())).toString()
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
                    TextINT.text = (TextINT.text.toString().toInt() + DiceRolls.D8()).toString()
                    TextMAN.text = (TextMAN.text.toString().toInt() + DiceRolls.D8()).toString()
                    TextTAG.text = (TextTAG.text.toString().toInt() + 1).toString()
                    if((TextINT.text.toString().toInt() + TextMAN.text.toString().toInt()) >= 32){
                        finalClass.add("Sacerdote/Stregone")
                    } else {
                        finalClass.add("Guerriero")
                        warriorClass(finalClass)
                    }
                    if(DiceRolls.D100() in 1..20){
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
                    TextINT.text = (TextINT.text.toString().toInt() + DiceRolls.D6()).toString()
                    TextMAN.text = (TextMAN.text.toString().toInt() + DiceRolls.D6()).toString()
                    if(bundle.get("gender")!! == "Femmina"){
                        TextFAS.text = (TextFAS.text.toString().toInt() + DiceRolls.D6()).toString()
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
                    TextCOS.text = (TextCOS.text.toString().toInt() + DiceRolls.D4()).toString()
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
                    corpText.text = "Corporatura " + corp
                    bundle.putString("corp", corp)
                    TextFAS.text = (TextFAS.text.toString().toInt() - DiceRolls.D4()).toString()
                    TextDES.text = (TextDES.text.toString().toInt() + DiceRolls.D4()).toString()
                    finalClass.add(classChoice())
                    warriorClass(finalClass)
                    merchantClass(finalClass)
                    sailorClass(finalClass)
                    linearLayout.addView(corpText)
                    linearLayout.addView(confirmButton)
                }
                "Shazaar" -> {
                    nationalityTextView.text = resources.getString(R.string.Shazaar)
                    finalClass.clear()
                    val arrayAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, corpNames)
                    corpSpinner.adapter = arrayAdapter
                    finalClass.add(classChoice())
                    warriorClass(finalClass)
                    merchantClass(finalClass)
                    sailorClass(finalClass)
                    TextCOS.text = (TextCOS.text.toString().toInt() + DiceRolls.D6()).toString()
                    linearLayout.addView(corpSpinner)
                    linearLayout.addView(confirmButton)
                }
                "Tarkesh" -> {
                    nationalityTextView.text = resources.getString(R.string.Tarkesh)
                    //TODO
                    finalClass.clear()
                    TextCOS.text = (TextCOS.text.toString().toInt() + DiceRolls.D4()).toString()
                    if(TextTAG.text.toString().toInt() >= 10) {
                        TextTAG.text = (TextTAG.text.toString().toInt() - 1).toString()
                    }
                    val arrayAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, corpNamesShortHigh)
                    corpSpinner.adapter = arrayAdapter
                    finalClass.add(classChoice())
                    warriorClass(finalClass)
                    merchantClass(finalClass)
                    sailorClass(finalClass)
                    for(i in 0 until finalClass.size){
                        if(finalClass[i] == "Agricoltore" || finalClass[i] == "Cacciatore"){
                            finalClass.removeAt(i)
                            finalClass.add("Marinaio")
                        }
                    }
                    linearLayout.addView(corpSpinner)
                    linearLayout.addView(confirmButton)
                }
                "Vilmir" -> {
                    nationalityTextView.text = resources.getString(R.string.Vilmir)
                    finalClass.clear()
                    val corpTemp = corpRollAlt()
                    corpText.text = "Corporatura " + corpTemp
                    finalClass.add(classChoice())
                    warriorClass(finalClass)
                    merchantClass(finalClass)
                    sailorClass(finalClass)
                    linearLayout.addView(corpText)
                    bundle.putString("corp", corpTemp)
                    linearLayout.addView(confirmButton)
                }
                "Ilmiora" -> {
                    nationalityTextView.text = resources.getString(R.string.Ilmiora)
                    TextFAS.text = (TextFAS.text.toString().toInt() + DiceRolls.D4()).toString()
                    finalClass.clear()
                    val corpTemp = corpRollAlt()
                    corpText.text = "Corporatura " + corpTemp
                    finalClass.add(classChoice())
                    warriorClass(finalClass)
                    merchantClass(finalClass)
                    sailorClass(finalClass)
                    bundle.putString("corp", corpTemp)
                    linearLayout.addView(corpText)
                    linearLayout.addView(confirmButton)
                }
                "Nadsokor" -> {
                    nationalityTextView.text = resources.getString(R.string.Nadsokor)
                    finalClass.clear()
                    finalClass.add("Mendicante")
                    TextCOS.text = (TextCOS.text.toString().toInt() - DiceRolls.D6()).toString()
                    TextFAS.text = (TextFAS.text.toString().toInt() - DiceRolls.D6()).toString()
                    val arrayAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, corpNames)
                    corpSpinner.adapter = arrayAdapter
                    linearLayout.addView(corpSpinner)
                    linearLayout.addView(confirmButton)
                }
                "Solitudine Piangente" -> {
                    nationalityTextView.text =
                        resources.getString(R.string.Solitudine_Piangente)
                    TextFOR.text = (TextFOR.text.toString().toInt() + DiceRolls.D6()).toString()
                    TextDES.text = (TextDES.text.toString().toInt() + DiceRolls.D4()).toString()
                    TextCOS.text = (TextCOS.text.toString().toInt() + DiceRolls.D6()).toString()
                    if(TextTAG.text.toString().toInt() >= 10) {
                        TextTAG.text = (TextTAG.text.toString().toInt() - 1).toString()
                    }
                    finalClass.clear()
                    finalClass.add("Guerriero")
                    finalClass.add("Cacciatore")
                    warriorClass(finalClass)
                    val corpTemp = "normale"
                    bundle.putString("corp", corpTemp)
                    corpText.text = "Corporatura " + corpTemp
                    linearLayout.addView(corpText)
                    linearLayout.addView(confirmButton)
                }
                "Eshmir" -> {
                    nationalityTextView.text = resources.getString(R.string.Eshmir)
                    //TODO
                    finalClass.clear()
                    TextINT.text = (TextINT.text.toString().toInt() + DiceRolls.D4()).toString()
                    TextMAN.text = (TextMAN.text.toString().toInt() + DiceRolls.D6()).toString()
                    if(TextTAG.text.toString().toInt() >= 10){
                        TextTAG.text = (TextTAG.text.toString().toInt() - 2).toString()
                    }
                    if((TextINT.text.toString().toInt() + TextMAN.text.toString().toInt()) >= 32){
                        finalClass.add("Stregone")
                        finalClass.add("Sacerdote")
                        if(TextFOR.text.toString().toInt() >= 13){
                            for(i in 0 until finalClass.size){
                                if(finalClass[i] == "Sacerdote"){
                                    finalClass.removeAt(i)
                                    finalClass.add("Sacerdote Guerriero")
                                }
                            }
                        }
                    } else {
                        finalClass.add(classChoice())
                        warriorClass(finalClass)
                        merchantClass(finalClass)
                        sailorClass(finalClass)
                    }
                    val corpTemp = "normale"
                    bundle.putString("corp", corpTemp)
                    corpText.text = "Corporatura " + corpTemp
                    linearLayout.addView(corpText)
                    linearLayout.addView(confirmButton)
                }
                "Isola delle Città Purpuree" -> {
                    nationalityTextView.text =
                        resources.getString(R.string.Isola_delle_Città_Purpuree)
                    finalClass.clear()
                    TextFOR.text = (TextFOR.text.toString().toInt() + DiceRolls.D4()).toString()
                    TextCOS.text = (TextCOS.text.toString().toInt() + DiceRolls.D6()).toString()
                    val arrayAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, corpNamesShortHigh)
                    corpSpinner.adapter = arrayAdapter
                    finalClass.add(classChoice())
                    warriorClass(finalClass)
                    merchantClass(finalClass)
                    sailorClass(finalClass)
                    if(finalClass.contains("Marinaio")) {
                        for (i in 0 until finalClass.size) {
                            if (finalClass[i] == "Cacciatore") {
                                finalClass.removeAt(i)
                                finalClass.add("Marinaio")
                                sailorClass(finalClass)
                            }
                        }
                    }
                    linearLayout.addView(corpSpinner)
                    linearLayout.addView(confirmButton)
                }
                "Argimiliar" -> {
                    nationalityTextView.text = resources.getString(R.string.Argimillar)
                    finalClass.clear()
                    val corpTemp = "normale"
                    bundle.putString("corp", corpTemp)
                    corpText.text = "Corporatura " + corpTemp
                    finalClass.add(classChoice())
                    warriorClass(finalClass)
                    merchantClass(finalClass)
                    sailorClass(finalClass)
                    linearLayout.addView(corpText)
                    linearLayout.addView(confirmButton)
                }
                "Pikarayd" -> {
                    nationalityTextView.text = resources.getString(R.string.Pikarayd)
                    finalClass.clear()
                    TextFOR.text = (
                            TextFOR.text.toString().toInt() +
                                    (DiceRolls.D4() + DiceRolls.D4())).toString()
                    val arrayAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, corpNamesShortHigh)
                    corpSpinner.adapter = arrayAdapter
                    finalClass.add(classChoice())
                    warriorClass(finalClass)
                    merchantClass(finalClass)
                    sailorClass(finalClass)
                    linearLayout.addView(corpSpinner)
                    linearLayout.addView(confirmButton)
                }
                "Lormyr" -> {
                    nationalityTextView.text = resources.getString(R.string.Lormyr)
                    finalClass.clear()
                    TextINT.text = (TextINT.text.toString().toInt() - DiceRolls.D4()).toString()
                    TextTAG.text = (TextTAG.text.toString().toInt() + 2).toString()
                    val arrayAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, corpNamesShortHigh)
                    corpSpinner.adapter = arrayAdapter
                    finalClass.add(classChoice())
                    warriorClass(finalClass)
                    merchantClass(finalClass)
                    sailorClass(finalClass)
                    linearLayout.addView(corpSpinner)
                    linearLayout.addView(confirmButton)
                }
                "Filkhar" -> {
                    nationalityTextView.text = resources.getString(R.string.Filkhar)
                    finalClass.clear()
                    TextDES.text = (TextDES.text.toString().toInt() + DiceRolls.D4()).toString()
                    val arrayAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, corpNamesShortLow)
                    corpSpinner.adapter = arrayAdapter
                    finalClass.add(classChoice())
                    warriorClass(finalClass)
                    merchantClass(finalClass)
                    sailorClass(finalClass)
                    linearLayout.addView(corpSpinner)
                    linearLayout.addView(confirmButton)
                }
                "Oin" -> {
                    nationalityTextView.text = resources.getString(R.string.Oin)
                    finalClass.clear()
                    if(TextINT.text.toString().toInt() >= 10) {
                        TextINT.text =
                            (TextINT.text.toString().toInt() - DiceRolls.D6()).toString()
                    }
                    if(TextDES.text.toString().toInt() >= 10){
                        TextDES.text = (TextDES.text.toString().toInt() - DiceRolls.D6()).toString()
                    }
                    if(TextMAN.text.toString().toInt() >= 10){
                        TextMAN.text = (TextMAN.text.toString().toInt() - DiceRolls.D6()).toString()
                    }
                    TextCOS.text = (TextCOS.text.toString().toInt() + DiceRolls.D6()).toString()
                    val corpTemp = "robusta"
                    bundle.putString("corp", corpTemp)
                    corpText.text = "Corporatura " + corpTemp
                    finalClass.add(classChoice())
                    warriorClass(finalClass)
                    merchantClass(finalClass)
                    if(!finalClass.contains("Agricoltore")){
                        if(finalClass.contains("Sacerdote")){
                            finalClass.remove("Sacerdote")
                        }
                        if(finalClass.contains("Marianio")){
                            finalClass.remove("Marinaio")
                        }
                        finalClass.add("Agricoltore")
                    }
                    linearLayout.addView(corpText)
                    linearLayout.addView(confirmButton)
                }
                "Yu" -> {
                    nationalityTextView.text = resources.getString(R.string.Yu)
                    finalClass.clear()
                    if(TextINT.text.toString().toInt() >= 10){
                        TextINT.text = (TextINT.text.toString().toInt() - DiceRolls.D6()).toString()
                    }
                    if(TextMAN.text.toString().toInt() >= 10){
                        TextMAN.text = (TextMAN.text.toString().toInt() - DiceRolls.D6()).toString()
                    }
                    if(TextFAS.text.toString().toInt() >= 10){
                        TextFAS.text = (TextFAS.text.toString().toInt() - DiceRolls.D6()).toString()
                    }
                    val corpTemp = "robusta"
                    bundle.putString("corp", corpTemp)
                    corpText.text = "Corporatura " + corpTemp
                    finalClass.add(classChoice())
                    warriorClass(finalClass)
                    if(!finalClass.contains("Cacciatore")){
                        if(finalClass.contains("Sacerdote")){
                            finalClass.remove("Sacerdote")
                        } else if(finalClass.contains("Mercante")){
                            finalClass.remove("Mercante")
                        } else if(finalClass.contains("Marianio")){
                            finalClass.remove("Marianio")
                        }
                        finalClass.add("Cacciatore")
                    }
                    linearLayout.addView(corpText)
                    linearLayout.addView(confirmButton)
                }
                "Org" -> {
                    nationalityTextView.text = resources.getString(R.string.Org)
                    finalClass.clear()
                    if(TextMAN.text.toString().toInt() >= 10){
                        TextMAN.text =
                            (TextMAN.text.toString().toInt() - (DiceRolls.D4() + DiceRolls.D4())).toString()
                    }
                    if(TextFAS.text.toString().toInt() >= 10){
                        TextFAS.text =
                            (TextFAS.text.toString().toInt() - (DiceRolls.D4() + DiceRolls.D4())).toString()
                    }
                    if(TextDES.text.toString().toInt() >= 10){
                        TextDES.text =
                            (TextDES.text.toString().toInt() - DiceRolls.D6()).toString()
                    }
                    if(TextTAG.text.toString().toInt() >= 10){
                        TextTAG.text = (TextTAG.text.toString().toInt() - 2).toString()
                    }
                    TextFOR.text = (TextFOR.text.toString().toInt() + DiceRolls.D4()).toString()
                    TextCOS.text = (TextCOS.text.toString().toInt() + DiceRolls.D8()).toString()
                    when(DiceRolls.D20()){
                        1 -> finalClass.add("Nobile")
                        else -> finalClass.add("Cacciatore")
                    }
                    val corpTemp = "robusta"
                    bundle.putString("corp", corpTemp)
                    corpText.text = "Corporatura " + corpTemp
                    linearLayout.addView(corpText)
                    linearLayout.addView(confirmButton)
                }
            }
            classText.text = finalClassToString(finalClass)
            nationalityText.text = nationality
            nationalityButton.isGone = true


            var heightWeigthPair = heightAndWeight(
                TextTAG.text.toString().toInt(),
                corpText.text.toString()
            )

            var height = heightWeigthPair.first
            println("Prova-1")
            for(i in height.indices){
                println(height[i])
            }
            var weigth = heightWeigthPair.second
            println("Prova-2")
            for(i in weigth.indices){
                println(weigth[i])
            }
        }
        linearLayout.addView(nationalityButton)

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
        var subClass : Int = DiceRolls.D10()
        if(subClass in (9..10)){
            for(i in 0..(finalClass.size - 1)){
                if(finalClass[i] == "Guerriero"){
                    finalClass.add("Assassino")
                }
            }
        }
    }

    private fun merchantClass(finalClass: ArrayList<String>){
        var subClass : Int = DiceRolls.D10()
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
        var subClass : Int = DiceRolls.D10()
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
        val diceShot : Int = DiceRolls.D6()
        when(diceShot){
            1,2 -> corp = "longilinea"
            3,4 -> corp = "normale"
            5,6 -> corp = "robusta"
        }
        return corp
    }

    private fun corpRollAlt() : String{
        var corp : String = ""
        val diceShot : Int = DiceRolls.D6()
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
        val tiroClasse: Int = DiceRolls.D100()
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

    private fun emptyAndFillArrayList(arrayList : ArrayList<Int>, minSize : Int, maxSize : Int) : ArrayList<Int>{
        arrayList.clear()
        for(i in minSize..maxSize){
            arrayList.add(i)
        }
        return arrayList
    }

    private fun heightAndWeight(TAG: Int, corp: String): Pair<ArrayList<Int>, IntArray> {
        var weight: IntArray = IntArray(0)
        var height = ArrayList<Int>()
        when (TAG) {
            1 -> {
                height = emptyAndFillArrayList(height, 0, 30)
                if (corp == "Corporatura longilinea") {
                    weight = (0..5).toIntArray()
                } else if (corp == "Corporatura normale") {
                    weight = (0..7).toIntArray()
                } else if (corp == "Corporatura robusta") {
                    weight = (0..10).toIntArray()
                }
            }
            2 -> {
                height = emptyAndFillArrayList(height, 31, 60)
                if (corp == "Corporatura longilinea") {
                    weight = (6..10).toIntArray()
                } else if (corp == "Corporatura normale") {
                    weight = (8..15).toIntArray()
                } else if (corp == "Corporatura robusta") {
                    weight = (11..20).toIntArray()
                }
            }
            3 -> {
                height = emptyAndFillArrayList(height, 61, 90)
                if (corp == "Corporatura longilinea") {
                    weight = (11..15).toIntArray()
                } else if (corp == "Corporatura normale") {
                    weight = (15..22).toIntArray()
                } else if (corp == "Corporatura robusta") {
                    weight = (21..30).toIntArray()
                }
            }
            4 -> {
                height = emptyAndFillArrayList(height, 91, 105)
                if (corp == "Corporatura longilinea") {
                    weight = (16..20).toIntArray()
                } else if (corp == "Corporatura normale") {
                    weight = (23..30).toIntArray()
                } else if (corp == "Corporatura robusta") {
                    weight = (31..40).toIntArray()
                }
            }
            5 -> {
                height = emptyAndFillArrayList(height, 106, 120)
                if (corp == "Corporatura longilinea") {
                    weight = (21..25).toIntArray()
                } else if (corp == "Corporatura normale") {
                    weight = (31..37).toIntArray()
                } else if (corp == "Corporatura robusta") {
                    weight = (41..50).toIntArray()
                }
            }
            6 -> {
                height = emptyAndFillArrayList(height, 121, 135)
                if (corp == "Corporatura longilinea") {
                    weight = (26..30).toIntArray()
                } else if (corp == "Corporatura normale") {
                    weight = (38..45).toIntArray()
                } else if (corp == "Corporatura robusta") {
                    weight = (51..60).toIntArray()
                }
            }
            7 -> {
                height = emptyAndFillArrayList(height, 136, 150)
                if (corp == "Corporatura longilinea") {
                    weight = (31..35).toIntArray()
                } else if (corp == "Corporatura normale") {
                    weight = (46..52).toIntArray()
                } else if (corp == "Corporatura robusta") {
                    weight = (61..70).toIntArray()
                }
            }
            8 -> {
                height = emptyAndFillArrayList(height, 151, 155)
                if (corp == "Corporatura longilinea") {
                    weight = (36..40).toIntArray()
                } else if (corp == "Corporatura normale") {
                    weight = (53..60).toIntArray()
                } else if (corp == "Corporatura robusta") {
                    weight = (71..80).toIntArray()
                }
            }
            9 -> {
                height = emptyAndFillArrayList(height, 156, 160)
                if (corp == "Corporatura longilinea") {
                    weight = (41..45).toIntArray()
                } else if (corp == "Corporatura normale") {
                    weight = (61..67).toIntArray()
                } else if (corp == "Corporatura robusta") {
                    weight = (81..90).toIntArray()
                }
            }
            10 -> {
                height = emptyAndFillArrayList(height, 161, 165)
                if (corp == "Corporatura longilinea") {
                    weight = (46..50).toIntArray()
                } else if (corp == "Corporatura normale") {
                    weight = (68..75).toIntArray()
                } else if (corp == "Corporatura robusta") {
                    weight = (91..100).toIntArray()
                }
            }
            11 -> {
                height = emptyAndFillArrayList(height, 166, 170)
                if (corp == "Corporatura longilinea") {
                    weight = (51..55).toIntArray()
                } else if (corp == "Corporatura normale") {
                    weight = (76..82).toIntArray()
                } else if (corp == "Corporatura robusta") {
                    weight = (101..110).toIntArray()
                }
            }
            12 -> {
                height = emptyAndFillArrayList(height, 171, 175)
                if (corp == "Corporatura longilinea") {
                    weight = (56..60).toIntArray()
                } else if (corp == "Corporatura normale") {
                    weight = (83..90).toIntArray()
                } else if (corp == "Corporatura robusta") {
                    weight = (111..120).toIntArray()
                }
            }
            13 -> {
                height = emptyAndFillArrayList(height, 176, 180)
                if (corp == "Corporatura longilinea") {
                    weight = (61..65).toIntArray()
                } else if (corp == "Corporatura normale") {
                    weight = (91..97).toIntArray()
                } else if (corp == "Corporatura robusta") {
                    weight = (121..130).toIntArray()
                }
            }
            14 -> {
                height = emptyAndFillArrayList(height, 181, 185)
                if (corp == "Corporatura longilinea") {
                    weight = (66..70).toIntArray()
                } else if (corp == "Corporatura normale") {
                    weight = (98..105).toIntArray()
                } else if (corp == "Corporatura robusta") {
                    weight = (131..140).toIntArray()
                }
            }
            15 -> {
                height = emptyAndFillArrayList(height, 186, 190)
                if (corp == "Corporatura longilinea") {
                    weight = (71..75).toIntArray()
                } else if (corp == "Corporatura normale") {
                    weight = (106..112).toIntArray()
                } else if (corp == "Corporatura robusta") {
                    weight = (141..150).toIntArray()
                }
            }
            16 -> {
                height = emptyAndFillArrayList(height, 191, 195)
                if (corp == "Corporatura longilinea") {
                    weight = (76..80).toIntArray()
                } else if (corp == "Corporatura normale") {
                    weight = (113..120).toIntArray()
                } else if (corp == "Corporatura robusta") {
                    weight = (151..160).toIntArray()
                }
            }
            17 -> {
                height = emptyAndFillArrayList(height, 196, 200)
                if (corp == "Corporatura longilinea") {
                    weight = (81..85).toIntArray()
                } else if (corp == "Corporatura normale") {
                    weight = (121..127).toIntArray()
                } else if (corp == "Corporatura robusta") {
                    weight = (161..170).toIntArray()
                }
            }
            18 -> {
                height = emptyAndFillArrayList(height, 201, 205)
                if (corp == "Corporatura longilinea") {
                    weight = (86..90).toIntArray()
                } else if (corp == "Corporatura normale") {
                    weight = (128..135).toIntArray()
                } else if (corp == "Corporatura robusta") {
                    weight = (171..180).toIntArray()
                }
            }
            19 -> {
                height = emptyAndFillArrayList(height, 206, 210)
                if (corp == "Corporatura longilinea") {
                    weight = (91..95).toIntArray()
                } else if (corp == "Corporatura normale") {
                    weight = (136..142).toIntArray()
                } else if (corp == "Corporatura robusta") {
                    weight = (181..190).toIntArray()
                }
            }
            20 -> {
                height = emptyAndFillArrayList(height, 211, 215)
                if (corp == "Corporatura longilinea") {
                    weight = (96..100).toIntArray()
                } else if (corp == "Corporatura normale") {
                    weight = (143..150).toIntArray()
                } else if (corp == "Corporatura robusta") {
                    weight = (191..200).toIntArray()
                }
            }
            21 -> {
                height = emptyAndFillArrayList(height, 216, 220)
                if (corp == "Corporatura longilinea") {
                    weight = (101..105).toIntArray()
                } else if (corp == "Corporatura normale") {
                    weight = (151..157).toIntArray()
                } else if (corp == "Corporatura robusta") {
                    weight = (201..210).toIntArray()
                }
            }
            22 -> {
                height = emptyAndFillArrayList(height, 221, 225)
                if (corp == "Corporatura longilinea") {
                    weight = (106..110).toIntArray()
                } else if (corp == "Corporatura normale") {
                    weight = (158..165).toIntArray()
                } else if (corp == "Corporatura robusta") {
                    weight = (211..220).toIntArray()
                }
            }
            23 -> {
                height = emptyAndFillArrayList(height, 226, 230)
                if (corp == "Corporatura longilinea") {
                    weight = (111..115).toIntArray()
                } else if (corp == "Corporatura normale") {
                    weight = (166..172).toIntArray()
                } else if (corp == "Corporatura robusta") {
                    weight = (221..230).toIntArray()
                }
            }
            24 -> {
                height = emptyAndFillArrayList(height, 231, 235)
                if (corp == "Corporatura longilinea") {
                    weight = (116..120).toIntArray()
                } else if (corp == "Corporatura normale") {
                    weight = (173..180).toIntArray()
                } else if (corp == "Corporatura robusta") {
                    weight = (231..240).toIntArray()
                }
            }
            else -> {
                height = emptyAndFillArrayList(height, 236, 240)
                if (corp == "Corporatura longilinea") {
                    weight = (121..125).toIntArray()
                } else if (corp == "Corporatura normale") {
                    weight = (181..187).toIntArray()
                } else if (corp == "Corporatura robusta") {
                    weight = (241..250).toIntArray()
                }
            }
        }
        println("Vediamo se sta merda fin qua arriva: " + weight.size)
        return Pair(height, weight)
    }

    fun IntRange.toIntArray(): IntArray {
        if (last < first)
            return IntArray(0)

        val result = IntArray(last - first + 1)
        var index = 0
        for (element in this)
            result[index++] = element
        return result
    }
}