package dev.x1opya.lonely_meeting

interface ArrayCallback<T> {
    fun onReady(list: ArrayList<T>)
    fun onFailure(msg: String)
}