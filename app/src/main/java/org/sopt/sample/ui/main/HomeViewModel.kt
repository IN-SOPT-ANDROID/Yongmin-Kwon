package org.sopt.sample.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.R

class HomeViewModel : ViewModel() {
    val prevPressedBottomNaviId = MutableLiveData<Int>(R.id.menu_home)
}