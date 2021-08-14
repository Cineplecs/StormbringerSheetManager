package com.example.stormbringersheetmanager.CharacterCreation

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        var FOR : Int
        var COS : Int
        var INT : Int
        var MAN : Int
        var DES : Int
        var FAS : Int
        var bundle : Bundle = requireArguments()
        var nationality = bundle.getString("nationality")
        var age = bundle.getInt("age")
        var gender = bundle.getString("gender")
        var characterName = bundle.getString("characterName")
        var playerName = bundle.getString("playerName")
        val linearLayout = view.findViewById<LinearLayout>(R.id.linearLayoutVerticalClassMain)

        when(nationality){
            //TODO
        }

        return view
    }

    fun classChoice(linearLayout : LinearLayout, finalClass : ArrayList<String>){
        val classButton : Button = Button(requireContext())
        val classText : TextView = TextView(requireContext())
        classText.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        classText.text = "Premere il pulsante per ottenere una classe"
        classText.gravity = Gravity.CENTER
        classText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30F)
        classText.setPadding(0, 15, 0, 15)
        classText.setTextColor(Color.BLACK)
        linearLayout.addView(classText)
        classButton.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        classButton.text = "Class Calculator"
        classButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
        classButton.setOnClickListener(){
            var tiroClasse : Int = DiceShots.D100()
            when(tiroClasse) {
                in 1..20 -> classText.text = "Guerriero"
                in 21..30 -> classText.text = "Mercante"
                in 31..45 -> classText.text = "Marinaio"
                in 46..60 -> classText.text = "Cacciatore"
                in 61..65 -> classText.text = "Agricoltore"
                in 66..70 -> classText.text = "Sacerdote"
                in 71..75 -> classText.text = "Nobile"
                in 76..85 -> classText.text = "Ladro"
                in 86..90 -> classText.text = "Mendicante"
                in 91..100 -> classText.text = "Artigiano"
            }
            finalClass.add(classText.toString())
        }
        classButton.setBackgroundColor(Color.BLUE)
        classButton.setTextColor(Color.BLACK)
        classButton.gravity = Gravity.CENTER
        classButton.setPadding(0, 15, 0, 15)
        linearLayout.addView(classButton)
    }
}