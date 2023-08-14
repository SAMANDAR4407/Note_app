package uz.gita.noteapppractika.presentation.screen.info

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.noteapppractika.BuildConfig
import uz.gita.noteapppractika.R
import uz.gita.noteapppractika.databinding.ScreenInfoBinding
import uz.gita.noteapppractika.utils.myApply

class InfoScreen : Fragment(R.layout.screen_info){
    private val binding by viewBinding(ScreenInfoBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.pp.movementMethod = LinkMovementMethod.getInstance()

        binding.myApply{
            requireActivity().addMenuProvider(object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.info_menu, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    return when (menuItem.itemId) {
                        R.id.share_app -> {
                            share()
                            true
                        }
                        else -> {
                            false
                        }
                    }
                }
            }, viewLifecycleOwner)
        }
    }

    private fun share() {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name)
            var shareMessage = "Note App".trim() + "\n"
            shareMessage = "${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}".trimIndent()
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        } catch (e: java.lang.Exception) {
            e.toString()
        }
    }

}