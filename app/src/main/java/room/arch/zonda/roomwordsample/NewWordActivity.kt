package room.arch.zonda.roomwordsample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_new_word.*
import org.jetbrains.anko.sdk25.listeners.onClick

class NewWordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        buttonSave.onClick {
            if (TextUtils.isEmpty(editWord.text)) {
                val intent = Intent()
                setResult(Activity.RESULT_CANCELED, intent)
            } else {
                val wordKey = editWord.text.toString()
                val intent = Intent()
                intent.putExtra(ExtraNameConstants.sNewWordKeyName, wordKey)
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
    }
}


