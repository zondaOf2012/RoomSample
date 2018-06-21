package room.arch.zonda.roomwordsample

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recyclerview_item.view.*
import room.arch.zonda.roomwordsample.persistence.Word

class WordListAdapter : ListAdapter<Word,
        WordListAdapter.WordViewHolder>(WordDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): WordViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(
                R.layout.recyclerview_item,
                parent,
                false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder,
                                  position: Int) {
        holder.bindTo(getItem(position))
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindTo(word: Word) {
            itemView.textView.text = word.wordKey
        }
    }

    class WordDiffCallback : DiffUtil.ItemCallback<Word>() {

        override fun areItemsTheSame(oldItem: Word?, newItem: Word?): Boolean {
            return oldItem?.wordKey == newItem?.wordKey
        }

        override fun areContentsTheSame(oldItem: Word?, newItem: Word?): Boolean {
            return oldItem == newItem
        }
    }
}