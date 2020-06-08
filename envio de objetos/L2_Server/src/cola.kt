import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

fun main(){
    println("Hello World!")

    var queue : Queue<Int> = LinkedList<Int>()
    queue.add(3)
    queue.add(2)
    queue.add(10)

    val jsonQueue = Gson().toJson(queue)
    println(jsonQueue)

    val queueType = object : TypeToken< LinkedList<Int> >(){}.type
    queue = Gson().fromJson(jsonQueue, queueType)

    queue.remove()
    queue.remove()
    println(queue)

}


