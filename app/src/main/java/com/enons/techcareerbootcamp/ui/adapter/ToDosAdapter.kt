package com.enons.techcareerbootcamp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.enons.techcareerbootcamp.data.entity.ToDos
import com.enons.techcareerbootcamp.databinding.CardDesignBinding
import com.enons.techcareerbootcamp.ui.viewmodel.MainViewModel
import com.enons.techcareerbootcamp.utils.transition
import com.google.android.material.snackbar.Snackbar
import com.enons.techcareerbootcamp.ui.fragment.MainFragmentDirections


class ToDosAdapter(var mContext:Context,var toDosList:List<ToDos>,var viewModel: MainViewModel)
    : RecyclerView.Adapter<ToDosAdapter.CardDesignHolder>() {

    inner class CardDesignHolder(var binding:CardDesignBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val binding = CardDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardDesignHolder(binding)
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {//position : 0,1,2
        val toDo = toDosList.get(position)
        val desing = holder.binding
        desing.textViewName.text = toDo.name

        desing.cardViewRow.setOnClickListener {
            val toUpdateScreen = MainFragmentDirections.toUpdateScreen(toDo = toDo)
            Navigation.transition(it,toUpdateScreen)
        }

        desing.imageViewDelete.setOnClickListener {
            Snackbar.make(it,"Do you want to delete ${toDo.name} ?",Snackbar.LENGTH_SHORT)
                .setAction("YES"){
                    viewModel.delete(toDo.id)
                }.show()
        }
    }

    override fun getItemCount(): Int {//size : 3
        return toDosList.size
    }


}