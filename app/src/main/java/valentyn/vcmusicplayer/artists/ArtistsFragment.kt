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
import valentyn.vcmusicplayer.utils.CardViewItemDecoration

class ArtistsFragment : Fragment() {

    private val artistAdapter: ArtistAdapter = ArtistAdapter(ArrayList())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_artist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        artist_recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(activity, resources.getInteger(R.integer.spanCountRV))
            adapter = artistAdapter
            addItemDecoration(
                CardViewItemDecoration(
                    resources.getInteger(R.integer.spaceItemDecorationRV),
                    resources.getInteger(R.integer.spanCountRV)
                )
            )
        }
        artistAdapter.updateDataSet(ArtistLoader.getAllArtists(context))
    }
}