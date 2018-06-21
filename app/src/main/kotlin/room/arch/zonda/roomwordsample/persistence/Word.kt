package room.arch.zonda.roomwordsample.persistence

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "worldTable")
data class Word(@PrimaryKey
                @ColumnInfo(name = "word")
                val wordKey: String)