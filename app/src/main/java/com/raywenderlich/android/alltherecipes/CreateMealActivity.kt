package com.raywenderlich.android.alltherecipes

import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_meal.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class CreateMealActivity : AppCompatActivity() {
    private val REQUEST_CAPTURE_IMAGE = 1
    private val REQUEST_SELECT_IMAGE_IN_ALBUM = 1
    private val PERMISSION_REQUEST_CODE: Int = 101
    private var mCurrentPhotoPath: String? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_meal)

        meal_pic_button.setOnClickListener{
            showPictureDialog()
        }
        // set on-click listener
        next_button.setOnClickListener {
            // your code to perform when the user clicks on the button
            //Check if recipe input field is empty
            if (TextUtils.isEmpty(recipeName.text.toString())) {
                recipeName.error = "Enter the name of the recipe"
            }
            else {
                val intent = Intent(this, Ingredients_Methods::class.java)
                startActivity(intent)
            }
        }
    }

    private fun showPictureDialog(){
        val items = arrayOf("Select photo from gallery", "Capture photo from camera")
        lateinit var dialog:AlertDialog
        val builder = android.support.v7.app.AlertDialog.Builder(this)
        builder.setTitle("Select Action")
        builder.setItems(items){dialog, which ->
        Toast.makeText(applicationContext, items[which] + " is clicked", Toast.LENGTH_SHORT).show()
            if(items.indexOf(items[which])==0){
                if (checkPersmission()) selectImageInAlbum() else requestPermission()
            }
            else{
                if (checkPersmission()) takePicture() else requestPermission()
            }
        }
        dialog = builder.create()
        dialog.show()
        true
    }
    /*
        private fun getCameraPermisson(){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.CAMERA),
                        REQUEST_CAMERA)
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
                    openCameraPerview()

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            } else {
                // Permission has already been granted
                openCameraPerview()
            }

        }
        private fun  getStroagePermission() {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        REQUEST_STORAGE)
                // Permission is not granted
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                                Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(this,
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                            REQUEST_STORAGE)
                    //openCameraPerview()
                    getCameraPermisson()
                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            } else {
                // Permission has already been granted
                //openCameraPerview()
                getCameraPermisson()
            }
        }*/
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {

                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                        &&grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    takePicture()

                } else {
                    Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show()
                }
                return
            }

            else -> {

            }
        }
    }
    private fun checkPersmission(): Boolean {
        return (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(READ_EXTERNAL_STORAGE, CAMERA),
                PERMISSION_REQUEST_CODE)
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

    @Throws(IOException::class)
    private fun createFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
                "JPEG_${timeStamp}_", /* prefix */
                ".jpg", /* suffix */
                storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            mCurrentPhotoPath = absolutePath
        }
    }
    private fun takePicture() {

        val intent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val file: File = createFile()

        val uri: Uri = FileProvider.getUriForFile(
                this,
                "com.example.android.fileprovider",
                file
        )
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri)
        startActivityForResult(intent, REQUEST_CAPTURE_IMAGE)

    }
    private fun selectImageInAlbum() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"

        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CAPTURE_IMAGE && resultCode == Activity.RESULT_OK) {

            //To get the File for further usage
            val auxFile = File(mCurrentPhotoPath)


            var bitmap : Bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath)
            imgView.setImageBitmap(bitmap)

        }
    }


}
