package valentyn.vcmusicplayer.albums

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_album.*
import valentyn.vcmusicplayer.R
import androidx.recyclerview.widget.GridLayoutManager
import valentyn.vcmusicplayer.data.dataloaders.AlbumLoader
import valentyn.vcmusicplayer.utils.CardViewItemDecoration

class AlbumsFragment : Fragment() {

    private val albumAdapter: AlbumAdapter = AlbumAdapter(ArrayList())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        album_recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(activity, resources.getInteger(R.integer.spanCountRV))
            adapter = albumAdapter
            addItemDecoration(
                CardViewItemDecoration(
                    resources.getInteger(R.integer.spaceItemDecorationRV),
                    resources.getInteger(R.integer.spanCountRV)
                )
            )
        }
        albumAdapter.updateDataSet(AlbumLoader.getAllAlbums(context))
    }
}