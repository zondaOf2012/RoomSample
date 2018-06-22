package room.arch.zonda.roomwordsample.persistence

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import android.content.Context
import kotlinx.coroutines.experimental.async

@Database(entities = [Word::class] , version = 1, exportSchema = true)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {

        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getInstance(context: Context): WordRoomDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also {
                        INSTANCE = it
                    }
                }

        private val MIGRATION_1_2 = object : Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE users ADD COLUMN last_update INTEGER")
            }
        }

        private fun buildDatabase(context: Context) =
                Room
                        //配置构建数据库基本参数
                        .databaseBuilder(
                                context.applicationContext,
                                WordRoomDatabase::class.java,
                                "WordSample.db")
                        //数据库迁移设置为丢弃之前版本的数据
                        .fallbackToDestructiveMigration()
                        //预加载数据库
                        .addCallback(object : RoomDatabase.Callback() {

                            lateinit var wordDao: WordDao

                            //onOpen
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                wordDao = getInstance(context).wordDao()
                                populateDb()
                            }

                            private fun populateDb() = async {
                                wordDao.deleteAll()
                                var word = Word("hello")
                                wordDao.insert(word)
                                word = Word("world")
                                wordDao.insert(word)
                            }
                        })
                        .addMigrations(MIGRATION_1_2)
                        //正式创建数据库
                        .build()
    }
}