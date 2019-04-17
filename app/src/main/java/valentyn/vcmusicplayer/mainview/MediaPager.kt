package valentyn.vcmusicplayer.mainview

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import valentyn.vcmusicplayer.albums.AlbumsFragment

class MediaPager internal constructor(
    fm: FragmentManager
) : FragmentPagerAdapter(fm) {

    private val count = 1

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = AlbumsFragment()
        }
        return fragment
    }

    override fun getCount(): Int {
        return count
    }
}