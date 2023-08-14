package uz.gita.noteapppractika.presentation.screen.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.noteapppractika.R
import uz.gita.noteapppractika.databinding.ScreenFavoriteBinding
import uz.gita.noteapppractika.presentation.adapter.FavoriteAdapter
import uz.gita.noteapppractika.presentation.screen.dialog.EventDialogRetrieve
import uz.gita.noteapppractika.presentation.screen.dialog.EventDialogStarred
import uz.gita.noteapppractika.presentation.screen.favorite.viewmodel.FavoriteViewModelImpl

class FavoriteScreen : Fragment(R.layout.screen_favorite) {

    private val binding by viewBinding(ScreenFavoriteBinding::bind)
    private val viewModel by viewModels<FavoriteViewModelImpl>()
    private val adapter = FavoriteAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter.setListener { data ->
            val bottomDialog = EventDialogStarred()
            bottomDialog.setClickButtonListener {
                viewModel.unsave_note(data)
                bottomDialog.dismiss()
            }
            bottomDialog.show(parentFragmentManager, "")
        }

        binding.recyclerView.adapter = adapter


        viewModel.starNotesLiveData.observe(viewLifecycleOwner){
            if (it.isEmpty()){
                binding.placeholder.visibility = View.VISIBLE
            }
            adapter.submitList(it)
        }
    }

}