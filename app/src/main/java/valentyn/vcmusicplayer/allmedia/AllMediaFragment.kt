package valentyn.vcmusicplayer.allmedia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_all_media.*
import valentyn.vcmusicplayer.R

class AllMediaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_all_media, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allMedia_recyclerViewVertical.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = AllMediaCollectionsAdapter(context)
        }
    }
}