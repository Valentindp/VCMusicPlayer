package valentyn.vcmusicplayer.albums

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.valentin.valentinmusicplayer.models.Album
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_album_list.view.*
import valentyn.vcmusicplayer.R
import valentyn.vcmusicplayer.utils.PlayerUtils

class AlbumAdapter(var list: List<Album>) : RecyclerView.Adapter<AlbumAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_album_list, parent, false)
    )

    override fun getItemCount() = list.size

    fun updateDataSet(arraylist: List<Album>) {
        list = arraylist
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bindData(list[position])
    }


    class ItemHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(item: Album) {

            Picasso.get()
                .load(PlayerUtils.getAlbumArtUri(item.id))
                .fit()
                //.error(R.drawable.ic_error_black_24dp)
                .into(view.album_card_image)

        }
    }
}