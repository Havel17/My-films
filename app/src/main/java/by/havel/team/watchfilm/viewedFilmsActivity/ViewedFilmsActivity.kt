package by.havel.team.watchfilm.viewedFilmsActivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.havel.team.watchfilm.R
import by.havel.team.watchfilm.models.Film
import by.havel.team.watchfilm.repository.LocalRepository
import by.havel.team.watchfilm.wantFilmsActivity.WantFilmsActivity
import kotlinx.android.synthetic.main.activity_viewedfilms.*
import kotlinx.android.synthetic.main.activity_wantfilms.*

class ViewedFilmsActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var viewModel: ViewedFilmsViewModel
    lateinit var viewedFilmsAdapter: ViewedFilmsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewedfilms)

        viewModel = ViewModelProvider (
            this,
            ViewedFilmsViewModelFactory(LocalRepository)
        ).get(ViewedFilmsViewModel::class.java)
        viewModel.filmsLiveData.observe(this, observer)

        viewedFilmsAdapter = ViewedFilmsAdapter()
        viewedfilmrecyclerview.layoutManager = LinearLayoutManager(this)
        viewedfilmrecyclerview.adapter = viewedFilmsAdapter


        wantfilm.setOnClickListener(this)

    }


    private val observer = Observer<List<Film>>{
        viewedFilmsAdapter.films2.addAll(it)
        viewedFilmsAdapter.notifyDataSetChanged()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.wantfilm->{
//                openWantFilmsActivity()
                finish()
            }

        }
    }

    private fun openWantFilmsActivity() {
        val intent = Intent(this, WantFilmsActivity::class.java)
        startActivity(intent)
    }
}
