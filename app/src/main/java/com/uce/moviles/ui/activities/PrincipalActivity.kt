package com.uce.moviles.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        initVariables("")
        initRecyclerView()
        initObservers()
        initListeners()
        viewModel.getAllNobelPrizes()

        switpeRecyclerView()

    }

    private fun initVariables(mensajeError: String) {
        dialog = AlertDialog.Builder(this)
            .setTitle(getString(R.string.title_dialog))
            .setMessage(mensajeError)
            .setPositiveButton(getString(R.string.aceptar)) { dialog, _ ->
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.cancelar)) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false) // el usuario no puede dar click fuera y debe escoger la opcion.
            .create()

    }

    private fun initObservers() {
        viewModel.listItems.observe(this) {
            binding.animationView.visibility = View.VISIBLE
            adapter.submitList(it)  // submitList es de diffUtil
            binding.animationView.visibility = View.GONE
        }

        viewModel.error.observe(this) {
            adapter.submitList(emptyList())
            initVariables(it)
            dialog.show()

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

        binding.etFiltro.addTextChangedListener { filtro ->
            val listFilter = adapter.currentList.toList().filter { item ->
                item.category.en.contains(filtro.toString())
            }
            adapter.submitList(listFilter)

            if (filtro.isNullOrBlank()) {
                viewModel.getAllNobelPrizes()

            }
        }
    }

    private fun switpeRecyclerView() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val list = adapter.currentList.toMutableList()
                list.removeAt(position)
                adapter.submitList(list)
            }
        }).attachToRecyclerView(binding.rvUsers)
    }
}