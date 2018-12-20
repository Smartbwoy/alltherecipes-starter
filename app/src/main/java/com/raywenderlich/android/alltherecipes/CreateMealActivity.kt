package com.raywenderlich.android.alltherecipes

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_meal.*
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.EditText


class CreateMealActivity : AppCompatActivity() {
    private val REQUEST_CAPTURE_IMAGE = 100
    private val REQUEST_CAMERA = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_meal)

        meal_pic_button.setOnClickListener{
            openCameraIntent()
            Toast.makeText(this, "Hiii You clicked me.", Toast.LENGTH_SHORT).show()

        }
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

    private fun openCameraIntent() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED ) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.CAMERA)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.CAMERA),
                        REQUEST_CAMERA)
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        1000)

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
            openCameraPerview()
        }


    }
    private fun openCameraPerview() {
        val pictureIntent = Intent(
                MediaStore.ACTION_IMAGE_CAPTURE
        )
        if (pictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(pictureIntent,
                    REQUEST_CAPTURE_IMAGE)
        }
    }

}
