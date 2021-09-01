package com.example.stormbringersheetmanager.CharacterCreation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import com.example.stormbringersheetmanager.R
import com.example.stormbringersheetmanager.Utility.Weapon
import com.example.stormbringersheetmanager.Utility.RangedWeapon
import java.nio.file.WatchEvent

class EquipmentSelection : Fragment() {

    private lateinit var gridLayout: GridLayout
    private lateinit var weaponTextView: TextView
    private lateinit var availableWeaponsText: TextView
    private lateinit var equipmentConfirmButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_equipment_selection, container, false)

        gridLayout = view.findViewById(R.id.weaponGrid)
        weaponTextView = view.findViewById(R.id.weaponTextView)
        availableWeaponsText = view.findViewById(R.id.availableWeaponsText)
        equipmentConfirmButton = view.findViewById(R.id.equipmentConfirmButton)

        val bundle: Bundle = requireArguments()
        val FOR: Int = bundle.getInt("FOR")
        val DES: Int = bundle.getInt("DES")
        val pgClass = bundle.getStringArrayList("class")

        var weapons: ArrayList<Weapon> = weaponCreation()

        var availableWeapon = 0

        for (i in pgClass!!.indices) {
            when (pgClass[i]) {
                "Guerriero" -> availableWeapon = 3
                "Mercante" -> availableWeapon = 1
                "Marinaio" -> availableWeapon = 1
                "Cacciatore" -> availableWeapon = 1
                "Agricoltore" -> availableWeapon = 1
                "Sacerdote" -> availableWeapon = 0
                "Nobile" -> availableWeapon = 2
                "Ladro" -> availableWeapon = 0
                "Mendicante" -> availableWeapon = 0
                "Artigiano" -> availableWeapon = 0
            }
        }

        var finalWeapon = ArrayList<Weapon>()
        var defaultText = "Armi selezionabili: "
        availableWeaponsText.text = defaultText + availableWeapon

        for (i in weapons.indices) {
            val params = GridLayout.LayoutParams()
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f)
            params.width = 0
            val checkBox = CheckBox(requireContext())
            checkBox.text = weapons[i].name

            checkBox.setOnClickListener() {
                if (checkBox.isChecked) {

                    weaponTextView.text = weaponToString(weapons[i])
                    if (availableWeapon > 0) {
                        if (weapons[i].requestFOR != null) {
                            if (FOR >= weapons[i].requestFOR!! && DES >= weapons[i].requestDES) {
                                availableWeapon--
                                availableWeaponsText.text = defaultText + availableWeapon
                                finalWeapon.add(weapons[i])
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "Il personaggio non ha le caratteristiche necessarie",
                                    Toast.LENGTH_SHORT
                                ).show()
                                checkBox.isChecked = false
                            }
                        } else if (weapons[i].requestFOR == null) {
                            if (DES >= weapons[i].requestDES) {
                                availableWeapon--
                                availableWeaponsText.text = defaultText + availableWeapon
                                finalWeapon.add(weapons[i])
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "Il personaggio non ha le caratteristiche necessarie",
                                    Toast.LENGTH_SHORT
                                ).show()
                                checkBox.isChecked = false
                            }
                        }
                    } else if (availableWeapon == 0) {
                        checkBox.isChecked = false
                    }


                } else if (!checkBox.isChecked) {

                    finalWeapon.remove(searchWeapon(checkBox.text.toString(), weapons))
                    availableWeapon++
                    availableWeaponsText.text = defaultText + availableWeapon

                }
            }

            if (pgClass.contains("Guerriero")) {
                gridLayout.addView(checkBox)
            } else if (pgClass.contains("Mercante")) {
                gridLayout.addView(checkBox)
            } else if (pgClass.contains("Marinaio")) {
                gridLayout.addView(checkBox)
            } else if (pgClass.contains("Cacciatore")) {
                if (checkBox.text.toString() == "Arco d'osso melniboleano" ||
                    checkBox.text.toString() == "Arco del deserto" ||
                    checkBox.text.toString() == "Fionda" ||
                    checkBox.text.toString() == "Giavellotto" ||
                    checkBox.text.toString() == "Pietre" ||
                    checkBox.text.toString() == "Scure da lancio" ||
                    checkBox.text.toString() == "Targa (da lancio)"
                ) {

                } else {
                    if(checkBox.text.toString() == "Arco lungo"){
                        checkBox.isChecked = true
                        checkBox.isClickable = false
                    }
                    gridLayout.addView(checkBox)
                }
            } else if (pgClass.contains("Agricoltore")) {
                if (checkBox.text.toString() == "Accetta" ||
                    checkBox.text.toString() == "Batone (2 mani)" ||
                    checkBox.text.toString() == "Clava"
                ) {
                    gridLayout.addView(checkBox)
                } else {

                }
            } else if (pgClass.contains("Sacerdote")) {
                if (checkBox.text.toString() != "Pugnale") {

                } else {
                    checkBox.isChecked = true
                    checkBox.isClickable = false
                    gridLayout.addView(checkBox)
                }
            } else if (pgClass.contains("Nobile")) {
                gridLayout.addView(checkBox)
            } else if (pgClass.contains("Ladro")){
                if (checkBox.text.toString() != "Pugnale") {

                } else {
                    checkBox.isChecked = true
                    checkBox.isClickable = false
                    gridLayout.addView(checkBox)
                }
            } else if (pgClass.contains("Mendicante")) {

            } else if (pgClass.contains("Artigiano")){
                Toast.makeText(requireContext(), "L'equipaggiamento e le abilità dell'artigiano andranno decise " +
                        "con il master", Toast.LENGTH_LONG).show()
            }

        }

        equipmentConfirmButton.setOnClickListener() {
            bundle.putParcelableArrayList("weapon", finalWeapon)
            Navigation.findNavController(view).navigate(R.id.equipmentToSheet)
        }

        return view
    }


    fun weaponToString(weapon: Weapon): String {
        return (
                weapon.name + "\n" +
                        "Forza: " + weapon.requestFOR +
                        "\nDestrezza: " + weapon.requestDES +
                        "\nDanno: " + weapon.damage +
                        "\nLunghezza/Gittata: " + weapon.length +
                        "\nPrezzo: " + weapon.price +
                        "\nPunti salute arma: " + weapon.healthPoints
                )
    }

    private fun weaponCreation(): ArrayList<Weapon> {
        var listOfWeapon: ArrayList<Weapon> = ArrayList<Weapon>()
        listOfWeapon.add(
            Weapon("Accetta", 7, 9, "1D6 + 1", "5", "125")
        )
        listOfWeapon.add(
            Weapon("Ascia da battaglia", 9, 9, "1D8 + 2", "75", "200")
        )
        listOfWeapon.add(
            Weapon("Ascia lormyriana (2 mani)", 13, 11, "3D6", "165", "400")
        )
        listOfWeapon.add(
            Weapon("Ascia marinara (2 mani)", 11, 9, "2D6 + 2", "120", "250")
        )
        listOfWeapon.add(
            Weapon("Bastone (2 mani)", 9, 9, "1D8", "180-210", "50")
        )
        listOfWeapon.add(
            Weapon("Clava", 7, 7, "1D6", "60-120", "0-5")
        )
        listOfWeapon.add(
            Weapon("Daga", 7, 7, "1D6", "75", "125")
        )
        listOfWeapon.add(
            Weapon("Falcione", 8, 8, "1D6 + 2", "90", "225")
        )
        listOfWeapon.add(
            Weapon("Giavellotto", null, 10, "1D6", "60-90", "175")
        )
        listOfWeapon.add(
            Weapon("Lancia (1 mano)", 9, 7, "1D6 + 1", "120-150", "50")
        )
        listOfWeapon.add(
            Weapon("Lancia lunga (2 mani)", 11, 9, "1D10 + 1", "210-240", "100")
        )
        listOfWeapon.add(
            Weapon("Mazza", 7, 7, "1D6 + 2", "60", "75")
        )
        listOfWeapon.add(
            Weapon("Mazza pesante (2 mani)", 13, 7, "1D8 + 2", "75-90", "200")
        )
        listOfWeapon.add(
            Weapon("Parma (scudo)", null, 12, "1D4", "30 (diametro)", "50")
        )
        listOfWeapon.add(
            Weapon("Picca filhariana (2 mani)", 11, 7, "2D6 + 1", "270-300", "150")
        )
        listOfWeapon.add(
            Weapon("Pugnale", null, 3, "1D4 + 2", "15-45", "100")
        )
        listOfWeapon.add(
            Weapon("Scimitarra", 9, 9, "1D8 + 1", "75-105", "210")
        )
        listOfWeapon.add(
            Weapon("Scudo equestre", 8, 9, "1D6", "90", "100")
        )
        listOfWeapon.add(
            Weapon("Scudo grande", 12, 6, "1D6 + 2", "150", "125")
        )
        listOfWeapon.add(
            Weapon("Spada", 9, 7, "1D8 + 1", "105", "250")
        )
        listOfWeapon.add(
            Weapon("Spadone (2 mani)", 11, 13, "2D8", "150-180", "750")
        )
        listOfWeapon.add(
            Weapon("Targa (scudo)", 8, 7, "1D6", "90 (diametro)", "75")
        )
        listOfWeapon.add(
            Weapon("Arco d'osso melniboleano", 11, 13, "2D6 + 1", "135", "750")
        )
        listOfWeapon.add(
            Weapon("Arco del deserto", 13, 11, "1D10 + 2", "135", "600")
        )
        listOfWeapon.add(
            Weapon("Arco lungo", 9, 9, "1D8 + 1", "90", "250")
        )
        listOfWeapon.add(
            Weapon("Fionda", null, 11, "1D6 + 1", "90", "25")
        )
        listOfWeapon.add(
            Weapon("Giavellotto", 7, 10, "1D8 + 2", "30", "75")
        )
        listOfWeapon.add(
            Weapon("Pietre", null, 5, "2D4", "30", null)
        )
        listOfWeapon.add(
            Weapon("Scure da lancio", 9, 12, "1D8 + 2", "15", "150")
        )
        listOfWeapon.add(
            Weapon("Targa (da lancio)", 16, 7, "2D4", "6", "75")
        )

        return listOfWeapon
    }

    private fun searchWeapon(name: String, weaponList: ArrayList<Weapon>): Weapon {
        var weapon = Weapon("Nessuna", 0, 0, "Nessuno", "Nessuno", "Nessuno")
        for (i in weaponList.indices) {
            if (weaponList[i].name == name) {
                weapon = weaponList[i]
            }
        }
        return weapon
    }


}

