package dev.x1opya.lonely_meeting.models
import com.google.gson.annotations.Expose
import java.text.SimpleDateFormat
import java.util.*

class Post(@Expose var title: String = "", var text: String = "", val owner_id: Int = 0) {

    var image: String = ""
    var date: Long = 0L

//     public fun getDate() : String {
//        val calendar = Calendar.getInstance().apply {
//            timeInMillis = date.toLong()
//        }
//        var formatter:SimpleDateFormat
//        formatter = if(calendar.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR))
//            SimpleDateFormat("dd MMM в HH:mm")
//        else SimpleDateFormat("dd MMM YYYY в HH:mm")
//        return formatter.format(calendar.time)
//    }

}