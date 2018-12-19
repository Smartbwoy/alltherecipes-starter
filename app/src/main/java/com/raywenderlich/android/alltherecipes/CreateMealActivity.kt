package com.raywenderlich.android.alltherecipes

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_meal.*
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.EditText


class CreateMealActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_meal)
        //val spinner: Spinner = findViewById(R.id.spinner)


        // get reference to button
       //val btn_next_step = findViewById<Button>(R.id.next_button)
        // set on-click listener
        next_button.setOnClickListener {
            // your code to perform when the user clicks on the button

            //Check if recipe input field is empty
            if (TextUtils.isEmpty(recipeName.text.toString())) {
                recipeName.error = "Enter the name of the recipe"
            }
            else {
               // Toast.makeText(this@CreateMealActivity, "Hiii You clicked me.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Ingredients_Methods::class.java)
                startActivity(intent)
                //section_1.visibility = View.INVISIBLE;
                //section_2.visibility = View.VISIBLE;
            }
        }
    }

}
