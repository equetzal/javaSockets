import java.net.*
import java.io.*

class client(){

    fun connectServer(host:String, port:Int, path:String): String? {
        try {
            val socket = Socket(InetAddress.getByName(host), port)
            val file = File(path)

            val dataOutputStream = DataOutputStream(socket.getOutputStream())
            val dataInputStream = DataInputStream(FileInputStream(file))

            dataOutputStream.writeUTF(file.name)
            dataOutputStream.flush()
            dataOutputStream.writeLong(file.length())
            dataOutputStream.flush()

            val bytes = ByteArray(1024)
            val size = file.length()
            var sent = 0
            var n = 0

            while(sent < size){
                n = dataInputStream.read(bytes)
                dataOutputStream.write(bytes, 0, n)
                dataOutputStream.flush()
                sent += n
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
