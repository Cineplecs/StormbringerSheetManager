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
import com.example.stormbringersheetmanager.Utility.MeleeWeapon
import com.example.stormbringersheetmanager.Utility.RangedWeapon

class EquipmentSelection : Fragment() {

    private lateinit var meleeWeaponText: TextView
    private lateinit var meleeWeaponSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_equipment_selection, container, false)

        meleeWeaponText = view.findViewById(R.id.meleeWeaponText)
        meleeWeaponSpinner = view.findViewById(R.id.meleeWeaponSpinner)

        val bundle: Bundle = requireArguments()
        val pgClass = bundle.get("class")

        var armiMischia: ArrayList<MeleeWeapon> = meleeWeaponCreation()

        val weaponNames = arrayOfNulls<String>(armiMischia.size)
        for (i in armiMischia.indices) {
            weaponNames[i] = armiMischia[i].name
        }

        val weaponAdapter =
            object : ArrayAdapter<String>(requireContext(), R.layout.spinner_item, weaponNames) {
                override fun isEnabled(position: Int): Boolean {
                    //TODO
                    return false
                }
            }
        meleeWeaponSpinner.adapter = weaponAdapter

        meleeWeaponSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                for (i in armiMischia.indices) {
                    if (armiMischia[i].name == meleeWeaponSpinner.selectedItem) {
                        meleeWeaponText.text = meleeWeaponToString(armiMischia[i])
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        return view
    }


    fun meleeWeaponToString(weapon: MeleeWeapon): String {
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

    private fun meleeWeaponCreation(): ArrayList<MeleeWeapon> {
        var listOfMeleeWeapon: ArrayList<MeleeWeapon> = ArrayList<MeleeWeapon>()
        listOfMeleeWeapon.add(
            MeleeWeapon("Accetta", 7, 9, "1D6 + 1", "5", "125")
        )
        listOfMeleeWeapon.add(
            MeleeWeapon("Ascia da battaglia", 9, 9, "1D8 + 2", "75", "200")
        )
        listOfMeleeWeapon.add(
            MeleeWeapon("Ascia lormyriana (2 mani)", 13, 11, "3D6", "165", "400")
        )
        listOfMeleeWeapon.add(
            MeleeWeapon("Ascia marinara (2 mani)", 11, 9, "2D6 + 2", "120", "250")
        )
        listOfMeleeWeapon.add(
            MeleeWeapon("Bastone (2 mani)", 9, 9, "1D8", "180-210", "50")
        )
        listOfMeleeWeapon.add(
            MeleeWeapon("Calcio", null, null, "1D6", "piede", null)
        )
        listOfMeleeWeapon.add(
            MeleeWeapon("Clava", 7, 7, "1D6", "60-120", "0-5")
        )
        listOfMeleeWeapon.add(
            MeleeWeapon("Daga", 7, 7, "1D6", "75", "125")
        )
        listOfMeleeWeapon.add(
            MeleeWeapon("Falcione", 8, 8, "1D6 + 2", "90", "225")
        )
        listOfMeleeWeapon.add(
            MeleeWeapon("Giavellotto", null, 10, "1D6", "60-90", "175")
        )
        listOfMeleeWeapon.add(
            MeleeWeapon("Lancia (1 mano)", 9, 7, "1D6 + 1", "120-150", "50")
        )
        listOfMeleeWeapon.add(
            MeleeWeapon("Lancia lunga (2 mani)", 11, 9, "1D10 + 1", "210-240", "100")
        )
        listOfMeleeWeapon.add(
            MeleeWeapon("Mazza", 7, 7, "1D6 + 2", "60", "75")
        )
        listOfMeleeWeapon.add(
            MeleeWeapon("Mazza pesante (2 mani)", 13, 7, "1D8 + 2", "75-90", "200")
        )
        listOfMeleeWeapon.add(
            MeleeWeapon("Parma (scudo)", null, 12, "1D4", "30 (diametro)", "50")
        )
        listOfMeleeWeapon.add(
            MeleeWeapon("Picca filhariana (2 mani)", 11, 7, "2D6 + 1", "270-300", "150")
        )
        listOfMeleeWeapon.add(
            MeleeWeapon("Pugnale", null, 3, "1D4 + 2", "15-45", "100")
        )
        listOfMeleeWeapon.add(
            MeleeWeapon("Pugno", null, null, "1D3", "mano", null)
        )
        listOfMeleeWeapon.add(
            MeleeWeapon("Scimitarra", 9, 9, "1D8 + 1", "75-105", "210")
        )
        listOfMeleeWeapon.add(
            MeleeWeapon("Scudo equestre", 8, 9, "1D6", "90", "100")
        )
        listOfMeleeWeapon.add(
            MeleeWeapon("Scudo grande", 12, 6, "1D6 + 2", "150", "125")
        )
        listOfMeleeWeapon.add(
            MeleeWeapon("Spada", 9, 7, "1D8 + 1", "105", "250")
        )
        listOfMeleeWeapon.add(
            MeleeWeapon("Spadone (2 mani)", 11, 13, "2D8", "150-180", "750")
        )
        listOfMeleeWeapon.add(
            MeleeWeapon("Targa (scudo)", 8, 7, "1D6", "90 (diametro)", "75")
        )
        listOfMeleeWeapon.add(
            MeleeWeapon("Testata", null, null, "1D4", "testa", null)
        )
        return listOfMeleeWeapon
    }

    private fun rangedWeaponCreation(): ArrayList<RangedWeapon>{
        var listOfRangedWeapon = ArrayList<RangedWeapon>()

        listOfRangedWeapon.add(
            RangedWeapon("Arco d'osso melniboleano", 11, 13, "2D6 + 1", 135, 750)
        )
        listOfRangedWeapon.add(
            RangedWeapon("Arco del deserto", 13, 11, "1D10 + 2", 135, 600)
        )
        listOfRangedWeapon.add(
            RangedWeapon("Arco lungo", 9, 9, "1D8 + 1", 90, 250)
        )
        listOfRangedWeapon.add(
            RangedWeapon("Fionda", null, 11, "1D6 + 1", 90, 25)
        )
        listOfRangedWeapon.add(
            RangedWeapon("Giavellotto", 7, 10, "1D8 + 2", 30, 75)
        )
        listOfRangedWeapon.add(
            RangedWeapon("Lancia", 9, 10, "2D6", 15, 50)
        )
        listOfRangedWeapon.add(
            RangedWeapon("Parma (scudo)", 6, 12, "1D6", 9, 50)
        )
        listOfRangedWeapon.add(
            RangedWeapon("Pietre", null, 5, "2D4", 30, null)
        )
        listOfRangedWeapon.add(
            RangedWeapon("Pugnale", null, 6, "1D4 + 2", 15, 100)
        )
        listOfRangedWeapon.add(
            RangedWeapon("Scure da lancio", 9, 12, "1D8 + 2", 15, 150)
        )
        listOfRangedWeapon.add(
            RangedWeapon("Targa", 16, 7, "2D4", 6, 75)
        )

        return listOfRangedWeapon
    }

}

