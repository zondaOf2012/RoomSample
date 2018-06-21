package room.arch.zonda.roomwordsample.persistence

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.experimental.async
import room.arch.zonda.roomwordsample.RoomWordApplication

class WordViewModel : ViewModel() {

//    private var repository: WordRepository
//    private var allWords: LiveData<List<Word>>
//
//    init {
//        repository = WordRepository(RoomWordApplication.instance)
//        allWords = repository.getAllWorlds()
//    }

    fun getAllWorlds(): LiveData<List<Word>> {
        return WordRoomDatabase
                .getInstance(RoomWordApplication.instance)
                .wordDao()
                .getAllWords()
    }

    fun insertWord(word: Word) = async {
        WordRoomDatabase
                .getInstance(RoomWordApplication.instance)
                .wordDao()
                .insert(word)
    }
}