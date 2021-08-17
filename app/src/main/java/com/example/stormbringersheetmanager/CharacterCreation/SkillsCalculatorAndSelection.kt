package com.example.stormbringersheetmanager.CharacterCreation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stormbringersheetmanager.DiceRolls
import com.example.stormbringersheetmanager.R

class SkillsCalculatorAndSelection : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_skills_calculator_and_selection, container, false)

        var bundle : Bundle = requireArguments()
        var pairFOR = Pair<String, Int>("FOR", bundle.get("FOR") as Int)
        var pairCOS = Pair<String, Int>("COS", bundle.get("COS") as Int)
        var pairDES = Pair<String, Int>("DES", bundle.get("DES") as Int)
        var pairMAN = Pair<String, Int>("MAN", bundle.get("MAN") as Int)
        var pairINT = Pair<String, Int>("INT", bundle.get("INT") as Int)
        var pairTAG = Pair<String, Int>("TAG", bundle.get("TAG") as Int)
        var pairFAS = Pair<String, Int>("FAS", bundle.get("FAS") as Int)
        var pgClass : ArrayList<String> = bundle.get("class") as ArrayList<String>
        var age : Int = bundle.get("age") as Int

        val pairArrayList = ArrayList<Pair<String, Int>>()
        pairArrayList.add(pairFOR)
        pairArrayList.add(pairCOS)
        pairArrayList.add(pairDES)
        pairArrayList.add(pairMAN)
        pairArrayList.add(pairINT)
        pairArrayList.add(pairTAG)
        pairArrayList.add(pairFAS)

        return view
    }

    private fun classString(finalClass : ArrayList<String>){
        for(i in 0 until finalClass.size){
            println(finalClass[i])
        }
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

    private fun Attacco(pairArrayList : ArrayList<Pair<String, Int>>) : Int{
        var attacco = 0
        if(pairArrayList[0].second > 12){
            attacco += (pairArrayList[0].second - 12)
        } else if (pairArrayList[0].second < 9){
            attacco -= (9 - pairArrayList[0].second)
        }
        if(pairArrayList[4].second > 12){
            attacco += (pairArrayList[4].second - 12)
        } else if (pairArrayList[4].second < 9){
            attacco -= (9 - pairArrayList[4].second)
        }
        if(pairArrayList[3].second > 12){
            attacco += (pairArrayList[3].second - 12)
        } else if (pairArrayList[3].second < 9){
            attacco -= (9 - pairArrayList[3].second)
        }
        if(pairArrayList[2].second > 12){
            attacco += (pairArrayList[2].second - 12)
        } else if (pairArrayList[2].second < 9){
            attacco -= (9 - pairArrayList[2].second)
        }
        return attacco
    }

    private fun Parata(pairArrayList : ArrayList<Pair<String, Int>>) : Int{
        var parata : Int = 0
        if(pairArrayList[0].second > 12){
            parata += (pairArrayList[0].second - 12)
        } else if (pairArrayList[0].second < 9){
            parata -= (9 - pairArrayList[0].second)
        }
        if(pairArrayList[3].second > 12){
            parata += (pairArrayList[3].second - 12)
        } else if (pairArrayList[3].second < 9){
            parata -= (9 - pairArrayList[3].second)
        }
        if(pairArrayList[2].second > 12){
            parata += (pairArrayList[2].second - 12)
        } else if (pairArrayList[2].second < 9){
            parata -= (9 - pairArrayList[2].second)
        }
        if(pairArrayList[5].second > 12){
            parata -= (pairArrayList[5].second - 12)
        } else if (pairArrayList[5].second < 9){
            parata += (9 - pairArrayList[5].second)
        }
        return parata
    }

    private fun DannoMischia(pairArrayList : ArrayList<Pair<String, Int>>) : Int{
        var dannoMischia : Int = 0
        var somma = pairArrayList[0].second + pairArrayList[5].second
        when{
            somma in 2..16 ->{
                dannoMischia -= DiceRolls.D6()
            }
            somma in 17..24 -> {dannoMischia = dannoMischia}
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
        return dannoMischia
    }

    private fun DannoLancio(pairArrayList : ArrayList<Pair<String, Int>>) : Int{
        var dannoLancio : Int = 0
        var somma = pairArrayList[0].second + pairArrayList[5].second
        when{
            somma in 2..16 ->{
                dannoLancio -= DiceRolls.D4()
            }
            somma in 17..24 -> {dannoLancio = dannoLancio}
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
        return dannoLancio

    }

    private fun Agilità(pairArrayList : ArrayList<Pair<String, Int>>) : Int{
        var agilità : Int = 0
        if(pairArrayList[0].second > 12){
            agilità += (pairArrayList[0].second - 12)
        } else if (pairArrayList[0].second < 9){
            agilità -= (9 - pairArrayList[0].second)
        }
        if(pairArrayList[3].second > 12){
            agilità += (pairArrayList[3].second - 12)
        } else if (pairArrayList[3].second < 9){
            agilità -= (9 - pairArrayList[3].second)
        }
        if(pairArrayList[2].second > 12){
            agilità += (pairArrayList[2].second - 12)
        } else if (pairArrayList[2].second < 9){
            agilità -= (9 - pairArrayList[2].second)
        }
        if(pairArrayList[5].second > 12){
            agilità -= (pairArrayList[5].second - 12)
        } else if (pairArrayList[5].second < 9){
            agilità += (9 - pairArrayList[5].second)
        }
        return agilità
    }

    private fun Manipolazione(pairArrayList : ArrayList<Pair<String, Int>>) : Int{
        var manipolazione = 0
        if(pairArrayList[0].second > 12){
            manipolazione += (pairArrayList[0].second - 12)
        } else if (pairArrayList[0].second < 9){
            manipolazione -= (9 - pairArrayList[0].second)
        }
        if(pairArrayList[4].second > 12){
            manipolazione += (pairArrayList[4].second - 12)
        } else if (pairArrayList[4].second < 9){
            manipolazione -= (9 - pairArrayList[4].second)
        }
        if(pairArrayList[3].second > 12){
            manipolazione += (pairArrayList[3].second - 12)
        } else if (pairArrayList[3].second < 9){
            manipolazione -= (9 - pairArrayList[3].second)
        }
        if(pairArrayList[2].second > 12){
            manipolazione += (pairArrayList[2].second - 12)
        } else if (pairArrayList[2].second < 9){
            manipolazione -= (9 - pairArrayList[2].second)
        }
        return manipolazione
    }

    private fun Percezione(pairArrayList : ArrayList<Pair<String, Int>>) : Int{
        var percezione = 0
        if(pairArrayList[4].second > 12){
            percezione += (pairArrayList[4].second - 12)
        } else if(pairArrayList[4].second < 9){
            percezione -= (9 - pairArrayList[4].second)
        }
        if(pairArrayList[3].second > 12){
            percezione += (pairArrayList[3].second - 12)
        } else if (pairArrayList[3].second < 9){
            percezione -= (9 - pairArrayList[3].second)
        }
        return percezione
    }

    private fun Furtività(pairArrayList : ArrayList<Pair<String, Int>>) : Int{
        var furtività = 0
        if(pairArrayList[5].second > 12){
            furtività -= (pairArrayList[5].second - 12)
        } else if (pairArrayList[5].second < 9){
            furtività += (9 - pairArrayList[5].second)
        }
        if(pairArrayList[4].second > 12){
            furtività += (pairArrayList[4].second - 12)
        } else if(pairArrayList[4].second < 9) {
            furtività -= (9 - pairArrayList[4].second)
        }
        if(pairArrayList[2].second > 12) {
            furtività += (pairArrayList[2].second - 12)
        } else if(pairArrayList[2].second < 9) {
            furtività -= (9 - pairArrayList[2].second)
        }
        return furtività
    }

    private fun Conoscenza(
        pairArrayList : ArrayList<Pair<String, Int>>,
        pgClass : ArrayList<String>,
        age : Int
    ) : Int{
        var conoscenza = 0
        if(pairArrayList[4].second > 12){
            conoscenza += 2*(pairArrayList[4].second - 12)
        }
        if(age > 25){
            if(pgClass.contains("Nobile") && !pgClass.contains("Sacerdote")){
                conoscenza += 2*(age - 25)
            } else if (!pgClass.contains("Nobile") && pgClass.contains("Sacerdote")) {
                conoscenza += 3*(age - 25)
            }
            if(!pgClass.contains("Nobile") && !pgClass.contains("Sacerdote")){
                conoscenza += (age - 25)
            } else if(pgClass.contains("Nobile") && pgClass.contains("Sacerdote")){
                conoscenza += 3*(age - 25)
            }
        }
        return conoscenza
    }

    private fun Comunicazione(pairArrayList : ArrayList<Pair<String, Int>>) : Int{
        var comunicazione = 0
        if(pairArrayList[3].second > 12){
            comunicazione += (pairArrayList[3].second - 12)
        } else if(pairArrayList[3].second < 9){
            comunicazione -= (9 - pairArrayList[3].second)
        }
        if(pairArrayList[4].second > 12){
            comunicazione += (pairArrayList[4].second - 12)
        } else if(pairArrayList[4].second < 9){
            comunicazione -= (9 - pairArrayList[4].second)
        }
        if(pairArrayList[6].second > 12){
            comunicazione += (pairArrayList[6].second - 12)
        } else if(pairArrayList[3].second < 9){
            comunicazione -= (9 - pairArrayList[6].second)
        }
        return comunicazione
    }

    private fun warriorClass(){

    }

}