package mx.ipn.escom.l2_client

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.gson.GsonBuilder

class MainActivity : AppCompatActivity() {
    var host = ""
    var port = 0
    var gustoQuetzalliano:GustoQuetzalliano? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<LinearLayout>(R.id.part1).visibility = View.VISIBLE
        findViewById<LinearLayout>(R.id.part2).visibility = View.GONE
        findViewById<LinearLayout>(R.id.part3).visibility = View.GONE
        findViewById<LinearLayout>(R.id.part4).visibility = View.GONE
        findViewById<LinearLayout>(R.id.part5).visibility = View.GONE

        findViewById<Button>(R.id.button1).setOnClickListener{
            host = findViewById<EditText>(R.id.inputHost).text.toString().trim()
            port = findViewById<EditText>(R.id.inputPort).text.toString().trim().toInt()

            findViewById<LinearLayout>(R.id.part1).visibility = View.GONE
            findViewById<LinearLayout>(R.id.part2).visibility = View.VISIBLE
            findViewById<LinearLayout>(R.id.part3).visibility = View.GONE
            findViewById<LinearLayout>(R.id.part4).visibility = View.GONE
            findViewById<LinearLayout>(R.id.part5).visibility = View.GONE
        }

        findViewById<Button>(R.id.button2).setOnClickListener{
            val nombreGusto = findViewById<TextView>(R.id.nombreGusto).text.toString().trim()
            val tipoGusto = findViewById<TextView>(R.id.tipoGusto).text.toString().trim()
            val nivelFelicidad = findViewById<TextView>(R.id.nivelFelicidad).text.toString().trim()
            val nivelMusicalidad = findViewById<TextView>(R.id.nivelMusicalidad).text.toString().trim()
            val esKaskade = findViewById<Switch>(R.id.esKaskade).isChecked

            if(!nombreGusto.isNullOrEmpty() && !tipoGusto.isNullOrEmpty() && !nivelFelicidad.isNullOrEmpty() && !nivelMusicalidad.isNullOrEmpty()){
                gustoQuetzalliano = GustoQuetzalliano(tipoGusto.toInt(), nombreGusto, nivelFelicidad.toFloat(), nivelMusicalidad.toDouble(), esKaskade)

                val gson = GsonBuilder().setPrettyPrinting().create()
                findViewById<TextView>(R.id.json).text = gson.toJson(gustoQuetzalliano)

                findViewById<LinearLayout>(R.id.part1).visibility = View.GONE
                findViewById<LinearLayout>(R.id.part2).visibility = View.GONE
                findViewById<LinearLayout>(R.id.part3).visibility = View.VISIBLE
                findViewById<LinearLayout>(R.id.part4).visibility = View.GONE
                findViewById<LinearLayout>(R.id.part5).visibility = View.GONE
            }else{
                Toast.makeText(this, "Necesitas llenar todos los campos", Toast.LENGTH_LONG)
            }
        }

        findViewById<Button>(R.id.button3).setOnClickListener{
            socketClient().execute()
        }

        findViewById<Button>(R.id.button4).setOnClickListener{
            findViewById<EditText>(R.id.inputHost).text.clear()
            findViewById<EditText>(R.id.inputPort).text.clear()

            findViewById<LinearLayout>(R.id.part1).visibility = View.VISIBLE
            findViewById<LinearLayout>(R.id.part2).visibility = View.GONE
            findViewById<LinearLayout>(R.id.part3).visibility = View.GONE
            findViewById<LinearLayout>(R.id.part4).visibility = View.GONE
            findViewById<LinearLayout>(R.id.part5).visibility = View.GONE
        }
    }

    inner class socketClient : AsyncTask<Void, Void, String>(){
        override fun onPreExecute() {
            findViewById<LinearLayout>(R.id.part1).visibility = View.GONE
            findViewById<LinearLayout>(R.id.part2).visibility = View.GONE
            findViewById<LinearLayout>(R.id.part3).visibility = View.GONE
            findViewById<LinearLayout>(R.id.part4).visibility = View.VISIBLE
            findViewById<LinearLayout>(R.id.part5).visibility = View.GONE
        }

        override fun doInBackground(vararg params: Void?): String? {
            return L2_Client().connectServer(host,port,gustoQuetzalliano!!)
        }

        override fun onPostExecute(result: String?) {
            findViewById<TextView>(R.id.mensajeLeidoDe).text = "Objeto enviado a $host:$port"
            findViewById<TextView>(R.id.mensaje).text = result

            findViewById<LinearLayout>(R.id.part1).visibility = View.GONE
            findViewById<LinearLayout>(R.id.part2).visibility = View.GONE
            findViewById<LinearLayout>(R.id.part3).visibility = View.GONE
            findViewById<LinearLayout>(R.id.part4).visibility = View.GONE
            findViewById<LinearLayout>(R.id.part5).visibility = View.VISIBLE
        }

    }
}