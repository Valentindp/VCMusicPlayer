package valentyn.vcmusicplayer.artists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_artist.*
import valentyn.vcmusicplayer.R
import valentyn.vcmusicplayer.data.dataloaders.ArtistLoader
import valentyn.vcmusicplayer.utils.SpacesItemDecoration

class ArtistsFragment : Fragment() {

    private val albumAdapter: ArtistAdapter = ArtistAdapter(ArrayList())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        artist_recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(activity, R.integer.spanCountRV)
            adapter = albumAdapter
            addItemDecoration(SpacesItemDecoration(R.integer.spaceItemDecorationRV, R.integer.spanCountRV))
        }
        albumAdapter.updateDataSet(ArtistLoader.getAllArtists(context))
    }
}