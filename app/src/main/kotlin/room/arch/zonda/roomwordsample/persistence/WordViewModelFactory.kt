package room.arch.zonda.roomwordsample.persistence

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class WordViewModelFactory(private val dataSource: WordDao) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            return WordViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}