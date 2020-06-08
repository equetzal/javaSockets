package mx.ipn.escom.l1_client

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import client

class MainActivity : AppCompatActivity() {
    var host = ""
    var port = 0
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
        }
    }

    inner class socketClient : AsyncTask<Void,Void,String>(){
        override fun onPreExecute() {
            findViewById<LinearLayout>(R.id.part1).visibility = View.GONE
            findViewById<LinearLayout>(R.id.part2).visibility = View.VISIBLE
        }

        override fun doInBackground(vararg params: Void?): String? {
            return client().listen(host,port)
        }

        override fun onPostExecute(result: String?) {
            findViewById<TextView>(R.id.mensajeLeidoDe).text = "Mensaje recibido de $host:$port"
            findViewById<TextView>(R.id.mensaje).text = result

            findViewById<LinearLayout>(R.id.part2).visibility = View.GONE
            findViewById<LinearLayout>(R.id.part3).visibility = View.VISIBLE
        }

    }
}