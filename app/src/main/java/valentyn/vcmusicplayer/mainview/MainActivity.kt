package valentyn.vcmusicplayer.mainview

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import valentyn.vcmusicplayer.R
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE_PERMISSION_READ_EXTERNAL_STORAGE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        media_pager.apply {
            adapter = MediaPager(supportFragmentManager)

           /* addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
                override fun onPageSelected(position: Int) {
                    bottomBar.selectTabAtPosition(position, true)
                }
                override fun onPageScrollStateChanged(state: Int) {}
            }) */

        }

        bottomBar.setOnTabSelectListener { tabId ->
            when (tabId) {
                R.id.allMedia_tab -> media_pager.currentItem = 0
                R.id.songs_tab -> media_pager.currentItem = 1
                R.id.albums_tab -> media_pager.currentItem = 2
                R.id.artists_tab -> media_pager.currentItem = 3
            }
        }
    }

    override fun onStart() {
        super.onStart()

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_CODE_PERMISSION_READ_EXTERNAL_STORAGE
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE_PERMISSION_READ_EXTERNAL_STORAGE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    // permission not granted
                    finish()
                }
                return
            }
        }
    }
}
