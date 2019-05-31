package valentyn.vcmusicplayer.songs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_song.*
import valentyn.vcmusicplayer.R
import valentyn.vcmusicplayer.data.dataloaders.SongLoader

class SongsFragment : Fragment() {

    private val songAdapter: SongsAdapter = SongsAdapter(ArrayList())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_song, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        song_recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = songAdapter
        }
        songAdapter.updateDataSet(SongLoader.getAllSongs(context))
    }
}