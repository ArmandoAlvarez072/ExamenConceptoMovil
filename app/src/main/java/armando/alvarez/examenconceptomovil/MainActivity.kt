package armando.alvarez.examenconceptomovil

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import armando.alvarez.examenconceptomovil.data.util.Resource
import armando.alvarez.examenconceptomovil.databinding.ActivityMainBinding
import armando.alvarez.examenconceptomovil.presentation.adapter.NasaAdapter
import armando.alvarez.examenconceptomovil.presentation.viewmodel.NasaViewModel
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: NasaViewModel by viewModels()

    @Inject
    lateinit var adapter: NasaAdapter

    private var dialogIsDisplayed = false
    private var dialogLoadingIsDisplayed = false
    private var loadingDialog: LoadingDialogFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        configRecyclerView()
        setListeners()

    }

    private fun setListeners() {
        binding.btnSearch.setOnClickListener {
            if (binding.etItemNumber.text.toString().isEmpty()) {
                showAlert( getString(R.string.error_empty))
            } else if (binding.etItemNumber.text.toString().toInt() > 10) {
                showAlert( getString(R.string.error_quantity))
            } else {
                getImages()
            }
        }
    }

    private fun configRecyclerView() {
        binding.rvNasa.apply {
            adapter = this@MainActivity.adapter
            layoutManager = GridLayoutManager(
                this@MainActivity, 1, GridLayoutManager.VERTICAL, false
            )
        }
    }

    private fun getImages() {

        viewModel.imagesResponse = MutableLiveData()
        viewModel.getImages(binding.etItemNumber.text.toString().toInt())

        viewModel.imagesResponse.observe(this) { response ->
            when (response) {
                is Resource.Success -> {
                    hideLoading()
                    response.data?.let {
                        adapter.differ.submitList(it)
                    }
                }

                is Resource.Error -> {
                    hideLoading()
                    Handler(Looper.getMainLooper()).postDelayed({
                        showAlert(
                            response.message!!
                        )
                    }, 200)
                }

                is Resource.Loading -> {
                    showLoading()
                }
            }
        }


    }

    private fun showLoading() {
        if (!dialogLoadingIsDisplayed) {
            dialogLoadingIsDisplayed = true
            loadingDialog = LoadingDialogFragment()
            loadingDialog?.show(
                supportFragmentManager,
                LoadingDialogFragment::class.java.simpleName
            )
        }
    }

    private fun hideLoading() {
        loadingDialog?.dismiss()
        loadingDialog = null
        dialogLoadingIsDisplayed = false
    }

    @SuppressLint("InflateParams")
    fun showAlert(
        message: String
    ) {
        val layout = layoutInflater.inflate(R.layout.dialog_fragment_alert, null)

        val displayRectangle = Rect()
        val window: Window = this.window
        window.decorView.getWindowVisibleDisplayFrame(displayRectangle)
        layout.minimumWidth = (displayRectangle.width() * 0.9f).toInt()

        val dialog = Dialog(this)
        dialog.setContentView(layout)
        val btn = layout.findViewById<Button>(R.id.btnAlertAccept)
        btn.setOnClickListener {
            dialogIsDisplayed = false
            dialog.dismiss()
        }

        val text = layout.findViewById<MaterialTextView>(R.id.tvAlertBody)
        text.text = message

        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        if (!dialogIsDisplayed) {
            dialog.show()
            dialogIsDisplayed = true
        }
    }

}