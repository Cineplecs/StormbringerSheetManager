package com.example.stormbringersheetmanager.CharacterCreation

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.GridLayout.LayoutParams
import com.example.stormbringersheetmanager.DiceRolls
import com.example.stormbringersheetmanager.R
import com.example.stormbringersheetmanager.Utility.Skills

class SkillsCalculatorAndSelection : Fragment() {

    private lateinit var skillValueTextView: TextView
    private lateinit var gridLayout: GridLayout
    private lateinit var availableSkillsText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View =
            inflater.inflate(R.layout.fragment_skills_calculator_and_selection, container, false)

        skillValueTextView = view.findViewById(R.id.skillValueTextView)
        availableSkillsText = view.findViewById(R.id.availableSkillsText)
        gridLayout = view.findViewById(R.id.simpleGridLayout)
        var defaultText = availableSkillsText.text.toString()

        var availableSkills = (DiceRolls.D6() + 2)
        availableSkillsText.text = (availableSkillsText.text.toString() + availableSkills).toString()


        /**
        for (i in 0..10) {
            var params = GridLayout.LayoutParams()
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f)
            params.width = 0
            var checkBox = CheckBox(requireContext())
            checkBox.text = i.toString()
            println(checkBox.text)
            checkBox.layoutParams = params
            gridLayout.addView(checkBox)
        }*/

        var skillList : List<Skills> = skillsCreation()

        for(i in skillList.indices){
            var params = GridLayout.LayoutParams()
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f)
            params.width = 0
            var checkBox = CheckBox(requireContext())
            checkBox.setOnClickListener(){
                if(availableSkills > 0){
                    if(checkBox.isChecked){
                        availableSkills--
                        availableSkillsText.text = defaultText + availableSkills
                    } else if(!checkBox.isChecked){
                        availableSkills++
                        availableSkillsText.text = defaultText + availableSkills
                    }
                } else {
                    if(!checkBox.isChecked){
                        availableSkills++
                        availableSkillsText.text = defaultText + availableSkills
                    } else {
                        checkBox.isChecked = false
                    }
                }
            }
            checkBox.text = skillList[i].name
            checkBox.layoutParams = params
            gridLayout.addView(checkBox)
        }

        var bundle: Bundle = requireArguments()
        var pairFOR = Pair<String, Int>("FOR", bundle.get("FOR") as Int)
        var pairCOS = Pair<String, Int>("COS", bundle.get("COS") as Int)
        var pairDES = Pair<String, Int>("DES", bundle.get("DES") as Int)
        var pairMAN = Pair<String, Int>("MAN", bundle.get("MAN") as Int)
        var pairINT = Pair<String, Int>("INT", bundle.get("INT") as Int)
        var pairTAG = Pair<String, Int>("TAG", bundle.get("TAG") as Int)
        var pairFAS = Pair<String, Int>("FAS", bundle.get("FAS") as Int)
        var pgClass: ArrayList<String> = bundle.get("class") as ArrayList<String>
        var age: Int = bundle.get("age") as Int

        val pairArrayList = ArrayList<Pair<String, Int>>()
        pairArrayList.add(pairFOR)
        pairArrayList.add(pairCOS)
        pairArrayList.add(pairDES)
        pairArrayList.add(pairMAN)
        pairArrayList.add(pairINT)
        pairArrayList.add(pairTAG)
        pairArrayList.add(pairFAS)

        skillValueTextView.text = (
                Attacco(pairArrayList) + "\n" +
                        Parata(pairArrayList) + "\n" +
                        DannoMischia(pairArrayList) + "\n" +
                        DannoLancio(pairArrayList) + "\n" +
                        Agilità(pairArrayList) + "\n" +
                        Manipolazione(pairArrayList) + "\n" +
                        Percezione(pairArrayList) + "\n" +
                        Furtività(pairArrayList) + "\n" + Conoscenza(
                    pairArrayList,
                    pgClass,
                    age
                ) + "\n" +
                        Comunicazione(pairArrayList))

        return view
    }

    private fun classString(finalClass: ArrayList<String>) {
        for (i in 0 until finalClass.size) {
            println(finalClass[i])
        }
    }

    private fun finalClassToString(finalClass: ArrayList<String>): String {
        var finalString = ""
        for (i in 0..(finalClass.size - 1)) {
            if (i == (finalClass.size - 1)) {
                finalString += finalClass[i]
            } else {
                finalString += finalClass[i] + "\n"
            }
        }
        return finalString
    }

    private fun Attacco(pairArrayList: ArrayList<Pair<String, Int>>): String {
        var attacco = 0
        if (pairArrayList[0].second > 12) {
            attacco += (pairArrayList[0].second - 12)
        } else if (pairArrayList[0].second < 9) {
            attacco -= (9 - pairArrayList[0].second)
        }
        if (pairArrayList[4].second > 12) {
            attacco += (pairArrayList[4].second - 12)
        } else if (pairArrayList[4].second < 9) {
            attacco -= (9 - pairArrayList[4].second)
        }
        if (pairArrayList[3].second > 12) {
            attacco += (pairArrayList[3].second - 12)
        } else if (pairArrayList[3].second < 9) {
            attacco -= (9 - pairArrayList[3].second)
        }
        if (pairArrayList[2].second > 12) {
            attacco += (pairArrayList[2].second - 12)
        } else if (pairArrayList[2].second < 9) {
            attacco -= (9 - pairArrayList[2].second)
        }
        return ("Attacco: " + attacco)
    }

    private fun Parata(pairArrayList: ArrayList<Pair<String, Int>>): String {
        var parata: Int = 0
        if (pairArrayList[0].second > 12) {
            parata += (pairArrayList[0].second - 12)
        } else if (pairArrayList[0].second < 9) {
            parata -= (9 - pairArrayList[0].second)
        }
        if (pairArrayList[3].second > 12) {
            parata += (pairArrayList[3].second - 12)
        } else if (pairArrayList[3].second < 9) {
            parata -= (9 - pairArrayList[3].second)
        }
        if (pairArrayList[2].second > 12) {
            parata += (pairArrayList[2].second - 12)
        } else if (pairArrayList[2].second < 9) {
            parata -= (9 - pairArrayList[2].second)
        }
        if (pairArrayList[5].second > 12) {
            parata -= (pairArrayList[5].second - 12)
        } else if (pairArrayList[5].second < 9) {
            parata += (9 - pairArrayList[5].second)
        }
        return ("Parata: " + parata)
    }

    private fun DannoMischia(pairArrayList: ArrayList<Pair<String, Int>>): String {
        var dannoMischia: Int = 0
        var somma = pairArrayList[0].second + pairArrayList[5].second
        when {
            somma in 2..16 -> {
                dannoMischia -= DiceRolls.D6()
            }
            somma in 17..24 -> {
                dannoMischia = dannoMischia
            }
            somma in 25..40 -> {
                dannoMischia += DiceRolls.D6()
            }
            somma in 41..50 -> {
                dannoMischia += (DiceRolls.D6() + DiceRolls.D6())
            }
            somma > 51 -> {
                dannoMischia += (DiceRolls.D6() + DiceRolls.D6() + DiceRolls.D6())
            }
        }
        return ("Danno da mischia: " + dannoMischia)
    }

    private fun DannoLancio(pairArrayList: ArrayList<Pair<String, Int>>): String {
        var dannoLancio: Int = 0
        var somma = pairArrayList[0].second + pairArrayList[5].second
        when {
            somma in 2..16 -> {
                dannoLancio -= DiceRolls.D4()
            }
            somma in 17..24 -> {
                dannoLancio = dannoLancio
            }
            somma in 25..40 -> {
                dannoLancio += DiceRolls.D4()
            }
            somma in 41..50 -> {
                dannoLancio += (DiceRolls.D4() + DiceRolls.D4())
            }
            somma > 51 -> {
                dannoLancio += (DiceRolls.D4() + DiceRolls.D4() + DiceRolls.D4())
            }
        }
        return ("Danno da lancio: " + dannoLancio)

    }

    private fun Agilità(pairArrayList: ArrayList<Pair<String, Int>>): String {
        var agilità: Int = 0
        if (pairArrayList[0].second > 12) {
            agilità += (pairArrayList[0].second - 12)
        } else if (pairArrayList[0].second < 9) {
            agilità -= (9 - pairArrayList[0].second)
        }
        if (pairArrayList[3].second > 12) {
            agilità += (pairArrayList[3].second - 12)
        } else if (pairArrayList[3].second < 9) {
            agilità -= (9 - pairArrayList[3].second)
        }
        if (pairArrayList[2].second > 12) {
            agilità += (pairArrayList[2].second - 12)
        } else if (pairArrayList[2].second < 9) {
            agilità -= (9 - pairArrayList[2].second)
        }
        if (pairArrayList[5].second > 12) {
            agilità -= (pairArrayList[5].second - 12)
        } else if (pairArrayList[5].second < 9) {
            agilità += (9 - pairArrayList[5].second)
        }
        return ("Agilità: " + agilità)
    }

    private fun Manipolazione(pairArrayList: ArrayList<Pair<String, Int>>): String {
        var manipolazione = 0
        if (pairArrayList[0].second > 12) {
            manipolazione += (pairArrayList[0].second - 12)
        } else if (pairArrayList[0].second < 9) {
            manipolazione -= (9 - pairArrayList[0].second)
        }
        if (pairArrayList[4].second > 12) {
            manipolazione += (pairArrayList[4].second - 12)
        } else if (pairArrayList[4].second < 9) {
            manipolazione -= (9 - pairArrayList[4].second)
        }
        if (pairArrayList[3].second > 12) {
            manipolazione += (pairArrayList[3].second - 12)
        } else if (pairArrayList[3].second < 9) {
            manipolazione -= (9 - pairArrayList[3].second)
        }
        if (pairArrayList[2].second > 12) {
            manipolazione += (pairArrayList[2].second - 12)
        } else if (pairArrayList[2].second < 9) {
            manipolazione -= (9 - pairArrayList[2].second)
        }
        return ("Manipolazione: " + manipolazione)
    }

    private fun Percezione(pairArrayList: ArrayList<Pair<String, Int>>): String {
        var percezione = 0
        if (pairArrayList[4].second > 12) {
            percezione += (pairArrayList[4].second - 12)
        } else if (pairArrayList[4].second < 9) {
            percezione -= (9 - pairArrayList[4].second)
        }
        if (pairArrayList[3].second > 12) {
            percezione += (pairArrayList[3].second - 12)
        } else if (pairArrayList[3].second < 9) {
            percezione -= (9 - pairArrayList[3].second)
        }
        return ("Percezione: " + percezione)
    }

    private fun Furtività(pairArrayList: ArrayList<Pair<String, Int>>): String {
        var furtività = 0
        if (pairArrayList[5].second > 12) {
            furtività -= (pairArrayList[5].second - 12)
        } else if (pairArrayList[5].second < 9) {
            furtività += (9 - pairArrayList[5].second)
        }
        if (pairArrayList[4].second > 12) {
            furtività += (pairArrayList[4].second - 12)
        } else if (pairArrayList[4].second < 9) {
            furtività -= (9 - pairArrayList[4].second)
        }
        if (pairArrayList[2].second > 12) {
            furtività += (pairArrayList[2].second - 12)
        } else if (pairArrayList[2].second < 9) {
            furtività -= (9 - pairArrayList[2].second)
        }
        return ("Furtività: " + furtività)
    }

    private fun Conoscenza(
        pairArrayList: ArrayList<Pair<String, Int>>,
        pgClass: ArrayList<String>,
        age: Int
    ): String {
        var conoscenza = 0
        if (pairArrayList[4].second > 12) {
            conoscenza += 2 * (pairArrayList[4].second - 12)
        }
        if (age > 25) {
            if (pgClass.contains("Nobile") && !pgClass.contains("Sacerdote")) {
                conoscenza += 2 * (age - 25)
            } else if (!pgClass.contains("Nobile") && pgClass.contains("Sacerdote")) {
                conoscenza += 3 * (age - 25)
            }
            if (!pgClass.contains("Nobile") && !pgClass.contains("Sacerdote")) {
                conoscenza += (age - 25)
            } else if (pgClass.contains("Nobile") && pgClass.contains("Sacerdote")) {
                conoscenza += 3 * (age - 25)
            }
        }
        return ("Conoscenza: " + conoscenza)
    }

    private fun Comunicazione(pairArrayList: ArrayList<Pair<String, Int>>): String {
        var comunicazione = 0
        if (pairArrayList[3].second > 12) {
            comunicazione += (pairArrayList[3].second - 12)
        } else if (pairArrayList[3].second < 9) {
            comunicazione -= (9 - pairArrayList[3].second)
        }
        if (pairArrayList[4].second > 12) {
            comunicazione += (pairArrayList[4].second - 12)
        } else if (pairArrayList[4].second < 9) {
            comunicazione -= (9 - pairArrayList[4].second)
        }
        if (pairArrayList[6].second > 12) {
            comunicazione += (pairArrayList[6].second - 12)
        } else if (pairArrayList[3].second < 9) {
            comunicazione -= (9 - pairArrayList[6].second)
        }
        return ("Comunicazione: " + comunicazione)
    }

    private fun warriorClass() {
        //TODO
    }

    private fun skillsCreation(): List<Skills> {
        var skillsList: List<Skills> = listOf(
            Skills("Furtività", "Intrufolarsi", 10, true),
            Skills("Furtività", "Nascondersi", 10, true),
            Skills("Furtività", "Agguato", 0, true),
            Skills("Furtività", "Celare", 0, true),
            Skills("Furtività", "Borseggiare", 0, true),
            Skills("Agilità", "Cavalcare", 0, true),
            Skills("Agilità", "Nuotare", 0, true),
            Skills("Agilità", "Arrampicarsi", 10, true),
            Skills("Agilità", "Saltare", 10, true),
            Skills("Agilità", "Cadere", 0, true),
            Skills("Agilità", "Schivare", 0, true),
            Skills("Manipolazione", "Fare/Disfare Nodi", 0, true),
            Skills("Manipolazione", "Attivare/Disattivare trappole", 0, true),
            Skills("Manipolazione", "Prestidigitazione", 0, true),
            Skills("Manipolazione", "Giocoleria", 0, true),
            Skills("Manipolazione", "Scassinare", 0, true),
            Skills("Percezione", "Osservare", 10, true),
            Skills("Percezione", "Ascoltare", 10, true),
            Skills("Percezione", "Annusare", 0, true),
            Skills("Percezione", "Gustare", 0, true),
            Skills("Percezione", "Equilibrio", 10, true),
            Skills("Percezione", "Cercare", 0, true),
            Skills("Percezione", "Seguire Tracce", 0, true),
            Skills("Conoscenza", "Leggere/Scrivere Lingua Comune", 0, true),
            Skills("Conoscenza", "Leggere/Scrivere Basso Melniboleano", 0, true),
            Skills("Conoscenza", "Leggere/Scrivere Tardo Melniboleano", 0, true),
            Skills("Conoscenza", "Leggere/Scrivere/Parlare Altre Lingue", 0, true),
            Skills("Conoscenza", "Navigazione", 0, true),
            Skills("Conoscenza", "Valutare Tesori", 0, true),
            Skills("Conoscenza", "Artigianato", 0, true),
            Skills("Conoscenza", "Pronto Soccorso", 0, true),
            Skills("Conoscenza", "Cartografia", 0, true),
            Skills("Conoscenza", "Memoria", 0, true),
            Skills("Conoscenza", "Conoscenza dei Veleni", 0, false),
            Skills("Conoscenza", "Conoscenza delle Piante", 0, false),
            Skills("Conoscenza", "Conoscenza della Musica", 0, false),
            Skills("Comunicazione", "Persuasione", 10, true),
            Skills("Comunicazione", "Reputazione", 0, true),
            Skills("Comunicazione", "Oratoria", 0, true),
            Skills("Comunicazione", "Cantare", 0, true)
        )
        return skillsList
    }

    private fun skillName(skill : Skills) : String{
        return skill.name
    }

}