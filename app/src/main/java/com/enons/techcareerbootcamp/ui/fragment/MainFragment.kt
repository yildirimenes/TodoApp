package com.enons.techcareerbootcamp.ui.fragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.enons.techcareerbootcamp.R
import com.enons.techcareerbootcamp.databinding.FragmentMainBinding
import com.enons.techcareerbootcamp.ui.adapter.ToDosAdapter
import com.enons.techcareerbootcamp.ui.viewmodel.MainViewModel
import com.enons.techcareerbootcamp.utils.transition
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        viewModel.toDosList.observe(viewLifecycleOwner){
            val toDosAdapter = ToDosAdapter(requireContext(),it,viewModel)
            binding.toDosRecyclerView.adapter = toDosAdapter

        }

        binding.toDosRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.floatingActionButton.setOnClickListener {
            Navigation.transition(it,R.id.toSaveScreen)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String): Boolean {//Harf girdikçe ve sildikçe çalışır.
                viewModel.search(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {//Klavye ara buttonu ile çalışır.
                viewModel.search(query)  ///bakkk
                return true
            }
        })
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: MainViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadToDos()
    }

}
