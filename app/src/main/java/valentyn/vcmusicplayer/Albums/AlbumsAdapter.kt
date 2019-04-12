package valentyn.vcmusicplayer.Albums

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.valentin.valentinmusicplayer.models.Album

class AlbumAdapter(list: List<Album>) : RecyclerView.Adapter<AlbumAdapter.ItemHolder>() {

    private var list: List<Album> = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class ItemHolder(val view: View) : RecyclerView.ViewHolder(view) {}
}