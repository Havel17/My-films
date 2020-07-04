package by.havel.team.watchfilm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.v
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewedfilms.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.viewedfilms -> {
                openViewedFilmsActivity()
            }
        }

    }


    fun openViewedFilmsActivity() {
        val intent = Intent(this, ViewedFilmsActivity::class.java)
        startActivity(intent)
    }


}