package com.raywenderlich.android.alltherecipes

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_meal.*
import kotlinx.android.synthetic.main.activity_ingredients__methods.*
import kotlinx.android.synthetic.main.list_ingredient_item.*
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.list_ingredient_item.view.*
import android.content.Intent
import android.provider.MediaStore


class Ingredients_Methods : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients__methods)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
                this,
                R.array.Measure_type,
                R.layout.spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.spinner_item)
            // Apply the adapter to the spinner
            measuretype_spinner.adapter = adapter
        }
        var listIngr = ArrayList<Recipe_Ingredient>()
        var listMethod = ArrayList<Recipe_Method>()


        add_ingr_btn.setOnClickListener {
            when {
                TextUtils.isEmpty(ingr_name_text.text.toString()) -> ingr_name_text.error = "Enter the name of the ingredient"
                TextUtils.isEmpty(ing_quty.text.toString()) -> ing_quty.error = "Enter ingredient amount"
                else ->
                    listIngr.add(Recipe_Ingredient(ing_quty.text.toString(), measuretype_spinner.selectedItem.toString(), ingr_name_text.text.toString()))
                //Toast.makeText(this, "Hiii You clicked me.", Toast.LENGTH_SHORT).show()
            }

            var ingreient_Adapter = RecipeIngredientAdapter(this, listIngr)
            ingrlist.adapter = ingreient_Adapter

        }

       /* method_button.setOnClickListener {
            when {
                TextUtils.isEmpty(methodText.text.toString()) -> methodText.error = "Enter Step by step methods"

            else ->
                listMethod.add(Recipe_Method(methodText.text.toString()))
        }
            var method_Adapter=RecipeMehodAdapter(this,listMethod)
            methodlist.adapter=method_Adapter
    }*/


        /*methodlist.onItemLongClickListener=AdapterView.OnItemLongClickListener { adapterView, view, position, id ->
            //Late initialize an alert dialog object
            lateinit var dialog:AlertDialog
            // Initialize a new instance of alert dialog builder object
            val builder = AlertDialog.Builder(this)
            // Set a title for alert dialog
            builder.setTitle("Delete method.")
            builder.setMessage("Do you wish to delete step "+(1+position))
            // On click listener for dialog buttons
            val dialogClickListener = DialogInterface.OnClickListener{ _, which ->
                when(which){
                    DialogInterface.BUTTON_POSITIVE ->removeMethod(position,listMethod)

                    //DialogInterface.BUTTON_NEGATIVE -> toast("Negative/No button clicked.")
                    //DialogInterface.BUTTON_NEUTRAL -> toast("Neutral/Cancel button clicked.")
                }
            }
            // Set the alert dialog positive/yes button
            builder.setPositiveButton("YES",dialogClickListener)

            // Set the alert dialog negative/no button
            builder.setNegativeButton("NO",dialogClickListener)

            // Set the alert dialog neutral/cancel button
            //builder.setNeutralButton("CANCEL",dialogClickListener)


            // Initialize the AlertDialog using builder object
            dialog = builder.create()

            // Finally, display the alert dialog
            dialog.show()
            true


        }*/
        ingrlist.onItemLongClickListener=AdapterView.OnItemLongClickListener { adapterView, view, position, id ->
            Toast.makeText(this, "Click on " + listIngr[position].label, Toast.LENGTH_SHORT).show();
            // Late initialize an alert dialog object
            lateinit var dialog:AlertDialog
            // Initialize a new instance of alert dialog builder object
            val builder = AlertDialog.Builder(this)
            // Set a title for alert dialog
            builder.setTitle("Delete Ingredient.")
            builder.setMessage("Do you wish to delete this ingredient")
// On click listener for dialog buttons
            val dialogClickListener = DialogInterface.OnClickListener{ _, which ->
                when(which){
                    DialogInterface.BUTTON_POSITIVE ->removeIngredient(position,listIngr)

                    //DialogInterface.BUTTON_NEGATIVE -> toast("Negative/No button clicked.")
                    //DialogInterface.BUTTON_NEUTRAL -> toast("Neutral/Cancel button clicked.")
                }
            }
            // Set the alert dialog positive/yes button
            builder.setPositiveButton("YES",dialogClickListener)

            // Set the alert dialog negative/no button
            builder.setNegativeButton("NO",dialogClickListener)

            // Set the alert dialog neutral/cancel button
            //builder.setNeutralButton("CANCEL",dialogClickListener)


            // Initialize the AlertDialog using builder object
            dialog = builder.create()

            // Finally, display the alert dialog
            dialog.show()
            true
        }




    }
    // Extension function to show toast message
    fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun Context.removeIngredient(position:Int,listIngritem:ArrayList<Recipe_Ingredient>){
        listIngritem.remove(listIngritem.get(position))
        var ingreient_Adapter = RecipeIngredientAdapter(this, listIngritem)
        ingrlist.adapter = ingreient_Adapter
    }
    fun Context.removeMethod(position:Int,listMethoditem:ArrayList<Recipe_Method>){
        listMethoditem.remove(listMethoditem.get(position))
        var method_Adapter = RecipeMehodAdapter(this, listMethoditem)
        methodlist.adapter = method_Adapter
    }


}
