package room.arch.zonda.roomwordsample

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex

class RoomWordApplication : Application() {

    companion object {
        lateinit var instance: RoomWordApplication
            private set
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}