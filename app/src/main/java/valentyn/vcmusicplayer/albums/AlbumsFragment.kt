package valentyn.vcmusicplayer.albums

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.valentin.valentinmusicplayer.data.dataloaders.AlbumLoader
import kotlinx.android.synthetic.main.fragment_album.*
import valentyn.vcmusicplayer.R
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import valentyn.vcmusicplayer.utils.PlayerUtils


class AlbumsFragment : Fragment() {

    private val albumAdapter: AlbumAdapter = AlbumAdapter(ArrayList())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        album_recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = albumAdapter
        }

        albumAdapter.updateDataSet(AlbumLoader.getAllAlbums(context))
    }
}