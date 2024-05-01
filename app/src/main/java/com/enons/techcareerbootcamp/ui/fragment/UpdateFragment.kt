package com.enons.techcareerbootcamp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.enons.techcareerbootcamp.databinding.FragmentUpdateBinding
import com.enons.techcareerbootcamp.ui.viewmodel.UpdateViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateFragment : Fragment() {
    private lateinit var binding: FragmentUpdateBinding
    lateinit var viewModel: UpdateViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)

        val bundle:UpdateFragmentArgs by navArgs()
        val toDo = bundle.toDo

        binding.editTextName.setText(toDo.name)

        binding.buttonUpdate.setOnClickListener {
            val name = binding.editTextName.text.toString()
            viewModel.update(toDo.id, name)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:UpdateViewModel by viewModels()
        viewModel = tempViewModel
    }
}