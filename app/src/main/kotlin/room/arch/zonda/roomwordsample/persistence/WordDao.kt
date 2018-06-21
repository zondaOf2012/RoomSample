package room.arch.zonda.roomwordsample.persistence

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

//数据访问对象（data access object，DAO）是为某种类型的数据库或其他持久性机制提供一个抽象介面的对象。
// 通过映射应用程序对持久层的调用，DAO提供一些特定的数据操作，而无需暴露数据库细节
@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: Word)

    @Query("DELETE FROM worldTable")
    fun deleteAll()

    @Query("SELECT * from worldTable ORDER BY word ASC")
    fun getAllWords(): LiveData<List<Word>>
}