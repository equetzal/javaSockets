import android.widget.Toast
import java.net.*
import java.io.*

class client(){

    fun listen(host:String, port:Int): String? {
        try {
            val socket = Socket(InetAddress.getByName(host), port)
            val buffer = BufferedReader(InputStreamReader(socket.getInputStream()))
            val message = buffer.readLine()
            buffer.close()
            socket.close()
            return message
        }catch (e:Exception){
            e.printStackTrace()
            return "ERROR_TIMEOUT"
        }
    }

}
