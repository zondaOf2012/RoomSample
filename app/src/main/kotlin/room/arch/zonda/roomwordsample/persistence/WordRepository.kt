package room.arch.zonda.roomwordsample.persistence

import android.app.Application
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.experimental.async

class WordRepository(application: Application) {

    private var wordDao: WordDao = WordRoomDatabase.getInstance(application).wordDao()

    private var allWords: LiveData<List<Word>>

    init {
        allWords = wordDao.getAllWords()
    }

    fun getAllWorlds(): LiveData<List<Word>> {
        return allWords
    }

    fun insertWord(word: Word) = async {
        wordDao.insert(word)
    }
}