import java.net.*
import java.io.*

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

                val message = "Saludos Quetzallianos!"
                val messageSender = PrintWriter(OutputStreamWriter(socket.getOutputStream()))
                messageSender.println(message)
                messageSender.flush()
                messageSender.close()
                socket.close()
            }
        }catch (e:Exception){
            e.printStackTrace()
            println("Chin! Ocurrió un error quetzalliano!")
        }
    }
}