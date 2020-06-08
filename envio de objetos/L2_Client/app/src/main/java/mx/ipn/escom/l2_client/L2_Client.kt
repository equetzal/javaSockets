package mx.ipn.escom.l2_client

import android.util.Log
import com.google.gson.Gson
import org.json.JSONObject
import java.io.*
import java.net.InetAddress
import java.net.Socket

class L2_Client {

    fun connectServer(host:String, port:Int, jsonObject: GustoQuetzalliano): String? {
        try {
            val socket = Socket(InetAddress.getByName(host), port)
            val jsonString = Gson().toJson(jsonObject)
            val file = File.createTempFile(jsonObject.nombreDelGusto, ".json")
            val writer = FileWriter(file)
            writer.write(jsonString)
            writer.close()

            val dataOutputStream = DataOutputStream(socket.getOutputStream())
            val dataInputStream =  DataInputStream(FileInputStream(file))

            dataOutputStream.writeUTF(file.name)
            dataOutputStream.flush()
            dataOutputStream.writeLong(file.length())
            dataOutputStream.flush()

            val bytes = ByteArray(1024)
            val size = file.length()
            var sent:Long = 0
            var n = 0

            while(sent < size){
                n = dataInputStream.read(bytes)
                dataOutputStream.write(bytes, 0, n)
                dataOutputStream.flush()
                sent += n
                Log.d("Sending File", "Progress= " + (sent/size)*100)
            }

            dataInputStream.close()
            dataOutputStream.close()
            socket.close()
            return "Archivo enviado con éxito"
        }catch (e:Exception){
            e.printStackTrace()
            return "ERROR"
        }
    }

}