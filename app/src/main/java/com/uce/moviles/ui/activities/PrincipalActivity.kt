package com.uce.moviles.ui.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.uce.moviles.R
import com.uce.moviles.databinding.ActivityPrincipalBinding
import com.uce.moviles.ui.adapters.NobelPrizeAdapter
import com.uce.moviles.ui.viewmodels.PrincipalViewModels

class PrincipalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrincipalBinding
    private val adapter = NobelPrizeAdapter()
    private val viewModel: PrincipalViewModels by viewModels()
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
        initObservers()
        initRecyclerView()

        // dialogos:
        dialog = AlertDialog.Builder(this)
            .setMessage(getString(R.string.carga_datos))
            .setTitle(getString(R.string.title_dialog))
            .setPositiveButton(getString(R.string.aceptar)) { _, _ ->
                viewModel.getAllNobelPrizes()
            }
            .setNegativeButton(getString(R.string.cancelar)) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .create()

        dialog.show()
    }

    private fun initObservers() {
        viewModel.listItems.observe(this) {
            binding.animationView.visibility = View.VISIBLE
            adapter.submitList(it)  // submitList es de diffUtil
            binding.animationView.visibility = View.GONE
        }

        viewModel.error.observe(this) {
            adapter.submitList(emptyList())
            //adapter.notifyDataSetChanged()  lo quito xq tengo diffutil
        }
    }

    private fun initRecyclerView() {
        binding.rvUsers.adapter = adapter
        binding.rvUsers.layoutManager =
            LinearLayoutManager(
                this@PrincipalActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
    }

    private fun initListeners() {
        binding.swiperv.setOnRefreshListener {
            viewModel.getAllNobelPrizes()
            binding.swiperv.isRefreshing = false
        }
    }
}