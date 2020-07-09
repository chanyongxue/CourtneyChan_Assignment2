package com.example.cst407_assignment_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

data class Drink (var espresso: Espresso,
                  var ingredients: List<String>?){
    var name: String = ""
    init {
        when(Pair(espresso.ounces, ingredients)){
            Pair(1, null)-> name = "Espresso"
            Pair(2, null) -> name = "Espresso Doppio"
            Pair(3, null) -> name = "Lungo"
            Pair(2, listOf("1oz Heavy Cream")) -> name = "Cafe Creme"
            Pair(2, listOf("1oz Hot Milk")) -> name = "Cafe Moisette"
            Pair(2, listOf("1oz Foamed Milk")) -> name = "Cafe Cortado"
            Pair(2, listOf("4oz Milk Foam")) -> name = "Dry Cappucino"
            Pair(2, listOf("3oz Hot Water")) -> name = "Caffe Americano"
            Pair(2, listOf("3oz Ice Cream")) -> name = "Caffe Affogato"
            Pair(2, listOf("4oz Brewed Coffee")) -> name = "Black Eye"
            Pair(2, listOf("3oz Whipped Cream")) -> name = "Con Panna"
            Pair(2, listOf("4oz Steamed Milk")) -> name = "Flat White"

            else -> name = "Try pairing your ingredient with 2 oz of espresso instead."
        }
    }
}

data class Espresso (var ounces: Int)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var drink: Drink?

        var inList: List<String>? = null

        var textView = findViewById<TextView>(R.id.txtViewDrinkName)

        val spinner = findViewById<Spinner>(R.id.spinner)

        val ingredients = resources.getStringArray(R.array.Ingredients)

        var currIngredient = ""

        var ozEspresso = 0

        val button1 = findViewById<Button>(R.id.onebutton)
        button1?.setOnClickListener() {
            Toast.makeText(this@MainActivity, R.string._1_oz, Toast.LENGTH_LONG).show()
            ozEspresso = 1
            textView.setText(Drink(Espresso(ozEspresso),inList).name)
        }

        val button2 = findViewById<Button>(R.id.twobutton)
        button2?.setOnClickListener() {
            Toast.makeText(this@MainActivity, R.string._2_oz, Toast.LENGTH_LONG).show()
            ozEspresso = 2
            textView.setText(Drink(Espresso(ozEspresso),inList).name)
        }

        val button3 = findViewById<Button>(R.id.threebutton)
        button3?.setOnClickListener() {
            Toast.makeText(this@MainActivity, R.string._3_oz, Toast.LENGTH_LONG).show()
            ozEspresso = 3
            textView.setText(Drink(Espresso(ozEspresso),inList).name)
        }
            if (spinner != null) {
                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item, ingredients
                )
                spinner.adapter = adapter

                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long

                    ) {
                        Toast.makeText(
                            this@MainActivity,
                            getString(R.string.SelectIngredient) + " " +
                                    "" + ingredients[position], Toast.LENGTH_SHORT
                        ).show()
                        currIngredient = ingredients[position]
                        if (currIngredient != "N/A") inList = listOf(currIngredient)
                        else inList = null
                        textView.setText(Drink(Espresso(ozEspresso),inList).name)
                    }
                }
            }
        }
    }


