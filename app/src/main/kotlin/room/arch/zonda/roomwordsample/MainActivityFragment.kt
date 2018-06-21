package room.arch.zonda.roomwordsample

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.view.*
import room.arch.zonda.roomwordsample.persistence.Word
import room.arch.zonda.roomwordsample.persistence.WordViewModel

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var wordViewModel: WordViewModel
    private lateinit var wordListAdapter: WordListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val mainView = inflater.inflate(R.layout.fragment_main, container, false)

        recyclerView = mainView.recycleView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            wordListAdapter = WordListAdapter()
            adapter = wordListAdapter
        } as RecyclerView

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        wordViewModel.getAllWorlds().observe(this, Observer<List<Word>> {
            wordListAdapter.submitList(it)
        })

        return mainView
    }
}
