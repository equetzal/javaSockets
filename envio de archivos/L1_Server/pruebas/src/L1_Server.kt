import java.net.*
import java.io.*
import javax.swing.JFileChooser

fun main(){
    println("Jelouu :3")
    L1_Server().start()
}

class L1_Server {

    fun start(){
        try{
            val serverSocket = ServerSocket(12345)
            println("Esperando Cliente...")
            println("Escuchando peticiones desde ${serverSocket.localSocketAddress}")
            while(true){
                val socket = serverSocket.accept()
                println("Se ha establecido una conexión desde ${socket.inetAddress}:${socket.port}")

                val dataInputStream = DataInputStream(socket.getInputStream())
                val bytes = ByteArray(1024)

                val file = "C:/equetzal/" + dataInputStream.readUTF()
                val size = dataInputStream.readLong()
                val dataOutputStream = DataOutputStream(FileOutputStream(file))

                println("Writting File on $file")

                var received = 0
                var n:Int
                var percentage:Int
                while(received < size){
                    n = dataInputStream.read(bytes)
                    dataOutputStream.write(bytes, 0, n)
                    dataOutputStream.flush()
                    received += n
                    percentage = (received*100/size).toInt()
                    println("Leido $percentage%")
                }
                println("Archivo Leido Completamente")

                dataOutputStream.close()
                dataInputStream.close()
                socket.close()
            }
        }catch (e:Exception){
            e.printStackTrace()
            println("Chin! Ocurrió un error quetzalliano!")
        }
    }
}