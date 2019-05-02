package valentyn.vcmusicplayer.mainview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import valentyn.vcmusicplayer.albums.AlbumsFragment

class MediaPager internal constructor(
    fm: androidx.fragment.app.FragmentManager
) : androidx.fragment.app.FragmentPagerAdapter(fm) {

    private val count = 1

    override fun getItem(position: Int): androidx.fragment.app.Fragment? {
        var fragment: androidx.fragment.app.Fragment? = null
        when (position) {
            0 -> fragment = AlbumsFragment()
        }
        return fragment
    }

    override fun getCount(): Int {
        return count
    }
}