package com.example.stormbringersheetmanager.CharacterCreation

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.GridLayout.LayoutParams
import androidx.navigation.Navigation
import com.example.stormbringersheetmanager.DiceRolls
import com.example.stormbringersheetmanager.R
import com.example.stormbringersheetmanager.Utility.Skills

class SkillsCalculatorAndSelection : Fragment() {

    private lateinit var skillValueTextView: TextView
    private lateinit var gridLayout: GridLayout
    private lateinit var availableSkillsText: TextView
    private lateinit var classSkillsList: TextView
    private lateinit var skillConfirmButton: Button

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
        classSkillsList = view.findViewById(R.id.classSkillsList)
        skillConfirmButton = view.findViewById(R.id.skillConfirmButton)
        var defaultText = availableSkillsText.text.toString()

        var availableSkills = (DiceRolls.D6() + 2)
        availableSkillsText.text =
            (availableSkillsText.text.toString() + availableSkills).toString()

        var bundle: Bundle = requireArguments()
        var pairFOR = Pair<String, Int>("FOR", bundle.get("FOR") as Int)
        var pairCOS = Pair<String, Int>("COS", bundle.get("COS") as Int)
        var pairDES = Pair<String, Int>("DES", bundle.get("DES") as Int)
        var pairMAN = Pair<String, Int>("MAN", bundle.get("MAN") as Int)
        var pairINT = Pair<String, Int>("INT", bundle.get("INT") as Int)
        var pairTAG = Pair<String, Int>("TAG", bundle.get("TAG") as Int)
        var pairFAS = Pair<String, Int>("FAS", bundle.get("FAS") as Int)
        var pgClass: ArrayList<String> = bundle.get("class") as ArrayList<String>

        var classe = ""
        if (pgClass.contains("Nobile")) {
            classe = randomClassFinder()
            pgClass.add(
                classe
            )
            if (classe == "Nobile") {
                bundle.putInt("trono", DiceRolls.D6())
            }
        }

        var age: Int = bundle.get("age") as Int

        val pairArrayList = ArrayList<Pair<String, Int>>()
        pairArrayList.add(pairFOR)
        pairArrayList.add(pairCOS)
        pairArrayList.add(pairDES)
        pairArrayList.add(pairMAN)
        pairArrayList.add(pairINT)
        pairArrayList.add(pairTAG)
        pairArrayList.add(pairFAS)

        var attacco = Attacco(pairArrayList)
        var parata = Parata(pairArrayList)
        var dannoMischia = DannoMischia(pairArrayList)
        var dannoLancio = DannoLancio(pairArrayList)
        var agilita = Agilità(pairArrayList)
        var manipolazione = Manipolazione(pairArrayList)
        var percezione = Percezione(pairArrayList)
        var furtivita = Furtività(pairArrayList)
        var conoscenza = Conoscenza(
            pairArrayList,
            pgClass,
            age
        )
        var comunicazione = Comunicazione(pairArrayList)

        skillValueTextView.text = (
                "Attacco: " + attacco +
                        "\nParata: " + parata +
                        "\nDanno Mischia: " + dannoMischia +
                        "\nDanno Lancio: " + dannoLancio +
                        "\nAgilità: " + agilita +
                        "\nManipolazione: " + manipolazione +
                        "\nPercezione: " + percezione +
                        "\nFurtività: " + furtivita +
                        "\n Conoscenza: " + conoscenza +
                        "\nComunicazione: " + comunicazione
                )

        val classSkills = ArrayList<Skills>()

        var finalSkills = ArrayList<Skills>()

        for (i in pgClass.indices) {
            when (pgClass[i]) {
                "Guerriero" -> {
                    if (DiceRolls.D100() < 85) {
                        classSkills.add(
                            Skills("Agilità", "Cavalcare", 10 + agilita, true)
                        )
                    }
                }
                "Assassino" -> {
                    if (DiceRolls.D100() < 85) {
                        classSkills.add(
                            Skills("Agilità", "Cavalcare", 10 + agilita, true)
                        )
                    }
                    classSkills.add(
                        Skills(
                            "Conoscenza",
                            "Conoscenza dei Veleni",
                            30 + percezione,
                            false
                        )
                    )
                    classSkills.add(
                        Skills("Percezione", "Cercare", 25 + percezione, true)
                    )
                    classSkills.add(
                        Skills("Furtività", "Intrufolarsi", 50 + furtivita, true)
                    )
                    classSkills.add(
                        Skills("Furtività", "Nascondersi", 40 + furtivita, true)
                    )
                    classSkills.add(
                        Skills("Furtività", "Agguato", 50 + furtivita, true)
                    )
                    classSkills.add(
                        Skills("Percezione", "Ascoltare", 50 + percezione, true)
                    )
                }
                "Mercante" -> {
                    classSkills.add(
                        Skills(
                            "Conoscenza",
                            "Leggere/Scrivere Lingua Comune",
                            70 + conoscenza,
                            true
                        )
                    )
                    classSkills.add(
                        Skills("Comunicazione", "Persuasione", 50 + comunicazione, true)
                    )
                    classSkills.add(
                        Skills("Comunicazione", "Reputazione", 40 + comunicazione, true)
                    )
                    classSkills.add(
                        Skills("Conoscenza", "Valutare Tesori", 80 + conoscenza, true)
                    )
                }
                "Marinaio" -> {
                    classSkills.add(
                        Skills("Agilità", "Nuotare", 50 + agilita, true)
                    )
                    classSkills.add(
                        Skills(
                            "Manipolazione",
                            "Fare/Disfare Nodi",
                            70 + manipolazione,
                            true
                        )
                    )
                    classSkills.add(
                        Skills("Agilità", "Arrampicarsi", 40 + agilita, true)
                    )
                    classSkills.add(
                        Skills("Percezione", "Equilibrio", 50 + percezione, true)
                    )
                    if (pgClass.contains("Capitano") || pgClass.contains("Nostromo")) {
                        classSkills.add(
                            Skills("Conoscenza", "Navigazione", 80 + conoscenza, true)
                        )
                    }
                }
                "Cacciatore" -> {
                    classSkills.add(
                        Skills(
                            "Manipolazione",
                            "Attivare/Disattivare trappole",
                            50 + manipolazione,
                            true
                        )
                    )
                    classSkills.add(
                        Skills("Furtività", "Agguato", 50 + percezione, true)
                    )
                    classSkills.add(
                        Skills("Percezione", "Seguire Tracce", 50 + percezione, true)
                    )
                }
                "Agricoltore" -> {
                    classSkills.add(
                        Skills("Percezione", "Seguire Tracce", 20 + percezione, true)
                    )
                    classSkills.add(
                        Skills(
                            "Conoscenza",
                            "Conoscenza delle Piante",
                            20 + conoscenza,
                            false
                        )
                    )
                }
                "Sacerdote" -> {
                    classSkills.add(
                        Skills("Conoscenza", "Leggere/Scrivere Lingua Comune", 0, true)
                    )
                    classSkills.add(
                        Skills("Conoscenza", "Leggere/Scrivere Tardo Melniboleano", 0, true)
                    )
                    classSkills.add(
                        Skills("Conoscenza", "Leggere/Scrivere Alto Meniboleano", 0, true)
                    )
                    classSkills.add(
                        Skills(
                            "Conoscenza",
                            "Conoscenza delle Piante",
                            40 + conoscenza,
                            false
                        )
                    )
                    classSkills.add(
                        Skills("Conoscenza", "Pronto Soccorso", 40 + conoscenza, true)
                    )
                    classSkills.add(
                        Skills("Comunicazione", "Persuasione", 25 + comunicazione, true)
                    )
                    classSkills.add(
                        Skills("Comunicazione", "Reputazione", 25 + comunicazione, true)
                    )
                }
                "Nobile" -> {
                    if(i != pgClass.lastIndex) {
                        classSkills.add(
                            Skills("Comunicazione", "Reputazione", 40 + comunicazione, true)
                        )
                    }
                }
                "Ladro" -> {
                    classSkills.add(
                        Skills(
                            "Conoscenza",
                            "Leggere/Scrivere Lingua Comune",
                            25 + conoscenza,
                            true
                        )
                    )
                    classSkills.add(
                        Skills("Agilità", "Arrampicarsi", DiceRolls.D100() + agilita, true)
                    )
                    classSkills.add(
                        Skills("Furtività", "Celare", DiceRolls.D100() + furtivita, true)
                    )
                    classSkills.add(
                        Skills("Agilità", "Saltare", DiceRolls.D100() + agilita, true)
                    )
                    classSkills.add(
                        Skills(
                            "Manipolazione",
                            "Scassinare",
                            DiceRolls.D100() + manipolazione,
                            true
                        )
                    )
                    classSkills.add(
                        Skills("Percezione", "Ascoltare", 70 + percezione, true)
                    )
                    classSkills.add(
                        Skills("Percezione", "Osservare", DiceRolls.D100() + percezione, true),
                    )
                    classSkills.add(
                        Skills("Percezione", "Cercare", DiceRolls.D100() + percezione, true)
                    )
                    classSkills.add(
                        Skills("Furtività", "Intrufolarsi", 50 + furtivita, true)
                    )
                    classSkills.add(
                        Skills("Furtività", "Borseggiare", DiceRolls.D100() + furtivita, true)
                    )
                    classSkills.add(
                        Skills("Conoscenza", "Valutare Tesori", 50 + conoscenza, true)
                    )
                }
                "Mendicante" -> {
                    classSkills.add(
                        Skills("Comunicazione", "Persuasione", 60 + comunicazione, true)
                    )
                    classSkills.add(
                        Skills("Percezione", "Osservare", 60 + percezione, true)
                    )
                    classSkills.add(
                        Skills("Percezione", "Cercare", 25 + manipolazione, true)
                    )
                }
                "Artigiano" -> {
                    classSkills.add(
                        Skills("Conoscenza", "Arte", 70 + conoscenza, true)
                    )
                }
            }
        }

        if(pgClass.contains("Nobile")){
            pgClass.removeAt(pgClass.size - 1)
        }

        val classSkillsNames = ArrayList<String>()

        for (i in classSkills.indices) {
            finalSkills.add(classSkills[i])
            if (classSkills[i].iniziale > 100) {
                classSkills[i].iniziale = 100
            } else if (classSkills[i].iniziale < 0) {
                classSkills[i].iniziale = 0
            }
            classSkillsNames.add(
                classSkills[i].name
            )
        }

        for (i in classSkills.indices) {
            when {
                i == (classSkills.size - 1) -> {
                    classSkillsList.text =
                        classSkillsList.text.toString() + classSkills[i].name + " " + classSkills[i].iniziale
                }
                i < (classSkills.size - 1) -> {
                    classSkillsList.text =
                        classSkillsList.text.toString() + classSkills[i].name + " " + classSkills[i].iniziale + "\n"
                }
                i == 0 -> {
                    classSkillsList.text =
                        classSkills[i].name + " " + classSkills[i].iniziale + "\n"
                }
            }
        }

        val skillsList: ArrayList<Skills> = skillsCreation()

        for (i in skillsList.indices) {
            if (!classSkillsNames.contains((skillsList[i].name))) {
                var skillCheck: Skills
                val params = LayoutParams()
                params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f)
                params.width = 0
                val checkBox = CheckBox(requireContext())
                checkBox.setOnClickListener() {
                    if (availableSkills > 0) {
                        if (checkBox.isChecked) {
                            availableSkills--
                            availableSkillsText.text = defaultText + availableSkills
                        } else if (!checkBox.isChecked) {
                            availableSkills++
                            availableSkillsText.text = defaultText + availableSkills
                        }
                    } else {
                        if (!checkBox.isChecked) {
                            availableSkills++
                            availableSkillsText.text = defaultText + availableSkills
                        } else {
                            checkBox.isChecked = false
                        }
                    }
                    if (checkBox.isChecked) {
                        skillCheck = searchSkill(checkBox.text.toString(), skillsList)
                        when (skillCheck.type) {
                            "Furtività" -> skillCheck.iniziale += ((DiceRolls.D100() + 2 - 1) / 2) + furtivita
                            "Agilità" -> skillCheck.iniziale += ((DiceRolls.D100() + 2 - 1) / 2) + agilita
                            "Manipolazione" -> skillCheck.iniziale += ((DiceRolls.D100() + 2 - 1) / 2) + manipolazione
                            "Percezione" -> skillCheck.iniziale += ((DiceRolls.D100() + 2 - 1) / 2) + manipolazione
                            "Conoscenza" -> skillCheck.iniziale += ((DiceRolls.D100() + 2 - 1) / 2) + conoscenza
                            "Comunicazione" -> skillCheck.iniziale += ((DiceRolls.D100() + 2 - 1) / 2) + comunicazione
                        }
                        if (skillCheck.iniziale < 0) {
                            skillCheck.iniziale = 0
                        } else if (skillCheck.iniziale > 100) {
                            skillCheck.iniziale = 100
                        }
                        finalSkills.add(skillCheck)
                    } else if (!checkBox.isChecked) {
                        finalSkills.remove(
                            searchSkill(checkBox.text.toString(), skillsList)
                        )
                    }
                }
                checkBox.text = (skillsList[i].name)
                checkBox.layoutParams = params
                gridLayout.addView(checkBox)
            }
        }

        skillConfirmButton.setOnClickListener() {
            if (availableSkills == 0) {
                for(i in skillsList.indices){
                    if(!finalSkills.contains(skillsList[i])){
                        when (skillsList[i].type) {
                            "Furtività" -> skillsList[i].iniziale += furtivita
                            "Agilità" -> skillsList[i].iniziale += agilita
                            "Manipolazione" -> skillsList[i].iniziale += manipolazione
                            "Percezione" -> skillsList[i].iniziale += manipolazione
                            "Conoscenza" -> skillsList[i].iniziale += conoscenza
                            "Comunicazione" -> skillsList[i].iniziale += comunicazione
                        }
                        finalSkills.add(
                            skillsList[i]
                        )
                    }
                }
                bundle.putParcelableArrayList("finalSkills", finalSkills)
                Navigation.findNavController(view).navigate(R.id.SkillsToEquipment, bundle)

            } else {
                Toast.makeText(
                    requireContext(),
                    "Ci sono altre abilità selezionabili",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return view
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

    private fun Attacco(pairArrayList: ArrayList<Pair<String, Int>>): Int {
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
        return attacco
    }

    private fun Parata(pairArrayList: ArrayList<Pair<String, Int>>): Int {
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
        return parata
    }

    private fun DannoMischia(pairArrayList: ArrayList<Pair<String, Int>>): Int {
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
        return dannoMischia
    }

    private fun DannoLancio(pairArrayList: ArrayList<Pair<String, Int>>): Int {
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
        return dannoLancio

    }

    private fun Agilità(pairArrayList: ArrayList<Pair<String, Int>>): Int {
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
        return agilità
    }

    private fun Manipolazione(pairArrayList: ArrayList<Pair<String, Int>>): Int {
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
        return manipolazione
    }

    private fun Percezione(pairArrayList: ArrayList<Pair<String, Int>>): Int {
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
        return percezione
    }

    private fun Furtività(pairArrayList: ArrayList<Pair<String, Int>>): Int {
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
        return furtività
    }

    private fun Conoscenza(
        pairArrayList: ArrayList<Pair<String, Int>>,
        pgClass: ArrayList<String>,
        age: Int
    ): Int {
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
        return conoscenza
    }

    private fun Comunicazione(pairArrayList: ArrayList<Pair<String, Int>>): Int {
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
        return comunicazione
    }

    private fun skillsCreation(): ArrayList<Skills> {
        var skillsList: ArrayList<Skills> = ArrayList<Skills>()
        skillsList.add(
            Skills("Furtività", "Intrufolarsi", 10, true)
        )
        skillsList.add(
            Skills("Furtività", "Nascondersi", 10, true)
        )
        skillsList.add(
            Skills("Furtività", "Agguato", 0, true)
        )
        skillsList.add(
            Skills("Furtività", "Celare", 0, true)
        )
        skillsList.add(
            Skills("Furtività", "Borseggiare", 0, true)
        )
        skillsList.add(
            Skills("Agilità", "Cavalcare", 0, true)
        )
        skillsList.add(
            Skills("Agilità", "Nuotare", 0, true)
        )
        skillsList.add(
            Skills("Agilità", "Arrampicarsi", 10, true)
        )
        skillsList.add(
            Skills("Agilità", "Saltare", 10, true)
        )
        skillsList.add(
            Skills("Agilità", "Cadere", 0, true)
        )
        skillsList.add(
            Skills("Agilità", "Schivare", 0, true)
        )
        skillsList.add(
            Skills("Manipolazione", "Fare/Disfare Nodi", 0, true)
        )
        skillsList.add(
            Skills("Manipolazione", "Attivare/Disattivare trappole", 0, true)
        )
        skillsList.add(
            Skills("Manipolazione", "Prestidigitazione", 0, true)
        )
        skillsList.add(
            Skills("Manipolazione", "Giocoleria", 0, true)
        )
        skillsList.add(
            Skills("Manipolazione", "Scassinare", 0, true)
        )
        skillsList.add(
            Skills("Percezione", "Osservare", 10, true)
        )
        skillsList.add(
            Skills("Percezione", "Ascoltare", 10, true)
        )
        skillsList.add(
            Skills("Percezione", "Annusare", 0, true)
        )
        skillsList.add(
            Skills("Percezione", "Gustare", 0, true)
        )
        skillsList.add(
            Skills("Percezione", "Equilibrio", 10, true)
        )
        skillsList.add(
            Skills("Percezione", "Cercare", 0, true)
        )
        skillsList.add(
            Skills("Percezione", "Seguire Tracce", 0, true)
        )
        skillsList.add(
            Skills("Conoscenza", "Arte", 0, true)
        )
        skillsList.add(
            Skills("Conoscenza", "Leggere/Scrivere Lingua Comune", 0, true)
        )
        skillsList.add(
            Skills("Conoscenza", "Leggere/Scrivere Basso Melniboleano", 0, true)
        )
        skillsList.add(
            Skills("Conoscenza", "Leggere/Scrivere Tardo Melniboleano", 0, true)
        )
        skillsList.add(
            Skills("Conoscenza", "Leggere/Scrivere Alto Meniboleano", 0, true)
        )
        skillsList.add(
            Skills("Conoscenza", "Leggere/Scrivere/Parlare Altre Lingue", 0, true)
        )
        skillsList.add(
            Skills("Conoscenza", "Navigazione", 0, true)
        )
        skillsList.add(
            Skills("Conoscenza", "Valutare Tesori", 0, true)
        )
        skillsList.add(
            Skills("Conoscenza", "Artigianato", 0, true)
        )
        skillsList.add(
            Skills("Conoscenza", "Pronto Soccorso", 0, true)
        )
        skillsList.add(
            Skills("Conoscenza", "Cartografia", 0, true)
        )
        skillsList.add(
            Skills("Conoscenza", "Memoria", 0, true)
        )
        skillsList.add(
            Skills("Conoscenza", "Conoscenza dei Veleni", 0, false)
        )
        skillsList.add(
            Skills("Conoscenza", "Conoscenza delle Piante", 0, false)
        )
        skillsList.add(
            Skills("Conoscenza", "Conoscenza della Musica", 0, false)
        )
        skillsList.add(
            Skills("Comunicazione", "Persuasione", 10, true)
        )
        skillsList.add(
            Skills("Comunicazione", "Reputazione", 0, true)
        )
        skillsList.add(
            Skills("Comunicazione", "Oratoria", 0, true)
        )
        skillsList.add(
            Skills("Comunicazione", "Cantare", 0, true)
        )
        return skillsList
    }

    private fun searchSkill(nome: String, skills: ArrayList<Skills>): Skills {
        var skillReturn: Skills = Skills("Nessuno", "Non ci sono abilità selezionate", 0, false)
        for (i in skills.indices) {
            if (skills[i].name == nome) {
                skillReturn = skills[i]
            }
        }
        return skillReturn
    }

    private fun randomClassFinder(): String {
        var className = ""
        when (DiceRolls.D100()) {
            in 1..20 -> {
                className = "Guerriero"
                if (DiceRolls.D10() in 9..10) {
                    className = ("Assassino")
                }
            }
            in 21..30 -> {
                className = ("Mercante")
            }
            in 31..45 -> {
                className = ("Marinaio")
            }
            in 46..60 -> {
                className = ("Cacciatore")
            }
            in 61..65 -> {
                className = ("Agricoltore")
            }
            in 66..70 -> {
                className = ("Sacerdote")
            }
            in 71..75 -> {
                className = ("Nobile")
            }
            in 76..85 -> {
                className = ("Ladro")
            }
            in 86..90 -> {
                className = ("Mendicante")
            }
            in 91..100 -> {
                className = ("Artigiano")
            }
        }
        return className
    }
}