package dev.x1opya.lonely_meeting

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val text = ObservableField<String>("hi pidor")
//
    val isLoading = ObservableField<Boolean>(true)

    fun updateData(){
        text.set("ne pidor")
    }



}
