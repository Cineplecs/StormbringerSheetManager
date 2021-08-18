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
import com.example.stormbringersheetmanager.Weapons.MeleeWeapon

class EquipmentSelection : Fragment() {

    lateinit var weaponText : TextView
    lateinit var weaponSpinner : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_equipment_selection, container, false)

        weaponText = view.findViewById(R.id.weaponText)
        weaponSpinner = view.findViewById(R.id.weaponSpinner)

        val bundle : Bundle = requireArguments()
        val pgClass = bundle.get("class")

        var armiMischia : List<MeleeWeapon> = meleeWeaponCreation()

        val weaponNames = arrayOfNulls<String>(armiMischia.size)
        for(i in armiMischia.indices){
            weaponNames[i] = armiMischia[i].name
        }

        val weaponAdapter = object : ArrayAdapter<String>(requireContext(), R.layout.spinner_item, weaponNames){
            override fun isEnabled(position: Int): Boolean {
                //TODO
                return false
            }
        }
        weaponSpinner.adapter = weaponAdapter

        weaponSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                for(i in armiMischia.indices){
                    if(armiMischia[i].name == weaponSpinner.selectedItem){
                        weaponText.text = weaponToString(armiMischia[i])
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        return view
    }


    fun weaponToString(weapon: MeleeWeapon) : String {
        return (
                weapon.name + "\n" +
                        "Forza: " + weapon.requestFOR +
                        "\nDestrezza: " + weapon.requestDES +
                        "\nDanno: " + weapon.damage +
                        "\nLunghezza: " + weapon.length +
                        "\nPrezzo: " + weapon.price +
                        "\nPunti salute arma: " + weapon.healthPoints
                )
    }

    private fun meleeWeaponCreation() : List<MeleeWeapon>{
        var listOfMeleeWeapon : List<MeleeWeapon> = listOf(
            MeleeWeapon("Accetta", 7, 9, "1D6 + 1", "5", "125"),
            MeleeWeapon("Ascia da battaglia", 9, 9, "1D8 + 2", "75", "200"),
            MeleeWeapon("Ascia lormyriana (2 mani)", 13, 11, "3D6", "165", "400"),
            MeleeWeapon("Ascia marinara (2 mani)", 11, 9, "2D6 + 2", "120", "250"),
            MeleeWeapon("Bastone (2 mani)", 9, 9, "1D8", "180-210", "50"),
            MeleeWeapon("Calcio", null, null, "1D6", "piede", null),
            MeleeWeapon("Clava", 7, 7, "1D6", "60-120", "0-5"),
            MeleeWeapon("Daga", 7, 7, "1D6", "75", "125")
            )
        return listOfMeleeWeapon
    }

}

