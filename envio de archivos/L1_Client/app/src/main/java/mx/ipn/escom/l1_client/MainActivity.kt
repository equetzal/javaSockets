package mx.ipn.escom.l1_client

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import client
import java.io.File

private const val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: Int = 7474
private const val READ_REQUEST_CODE: Int = 42

class MainActivity : AppCompatActivity() {
    var host = ""
    var port = 0
    var dataPath:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button1).setOnClickListener{
            host = findViewById<EditText>(R.id.inputHost).text.toString().trim()
            port = findViewById<EditText>(R.id.inputPort).text.toString().trim().toInt()
            socketClient().execute()
        }

        findViewById<Button>(R.id.button2).setOnClickListener{
            findViewById<LinearLayout>(R.id.part3).visibility = View.GONE
            findViewById<LinearLayout>(R.id.part1).visibility = View.VISIBLE

            findViewById<EditText>(R.id.inputHost).text.clear()
            findViewById<EditText>(R.id.inputPort).text.clear()
            findViewById<TextView>(R.id.archivo).text = "Archivo Seleccionado: "
            dataPath = null
        }

        findViewById<Button>(R.id.buttonFile).setOnClickListener{
            // Here, thisActivity is the current activity
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

                // Permission is not granted
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_CONTACTS)) {
                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE)

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            } else {
                // Permission has already been granted
                performFileSearch()
            }
        }
    }

    fun performFileSearch() {
        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            // Filter to only show results that can be "opened", such as a
            // file (as opposed to a list of contacts or timezones)
            addCategory(Intent.CATEGORY_OPENABLE)

            // Filter to show only images, using the image MIME data type.
            // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
            // To search for all documents available via installed storage providers,
            // it would be "*/*".
            type = "*/*"
        }

        startActivityForResult(intent, READ_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            resultData?.data?.also { uri ->
                Log.d("data", "Uri: $uri")
                Log.d("data", "Path: " + FilePath.getPath(this, uri))
                val path = FilePath.getPath(this, uri)
                dataPath = path
                val file = File(path)
                findViewById<TextView>(R.id.archivo).text = "Archivo Seleccionado: " + file.name
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    performFileSearch()
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "No se puede seleccionar el archivo", Toast.LENGTH_LONG).show()
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }


    inner class socketClient : AsyncTask<Void,Void,String>(){
        override fun onPreExecute() {
            findViewById<LinearLayout>(R.id.part1).visibility = View.GONE
            findViewById<LinearLayout>(R.id.part2).visibility = View.VISIBLE
        }

        override fun doInBackground(vararg params: Void?): String? {
            if(dataPath.isNullOrEmpty())
                return "No file selected"
            return client().connectServer(host,port,dataPath!!)
        }

        override fun onPostExecute(result: String?) {
            findViewById<TextView>(R.id.mensajeLeidoDe).text = "Mensaje recibido de $host:$port"
            findViewById<TextView>(R.id.mensaje).text = result

            findViewById<LinearLayout>(R.id.part2).visibility = View.GONE
            findViewById<LinearLayout>(R.id.part3).visibility = View.VISIBLE
        }

    }
}