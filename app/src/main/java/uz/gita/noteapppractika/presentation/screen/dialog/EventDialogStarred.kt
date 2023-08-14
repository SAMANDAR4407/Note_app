package uz.gita.noteapppractika.presentation.screen.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.gita.noteapppractika.R

class EventDialogStarred : BottomSheetDialogFragment(R.layout.event_dialog_starred) {

    private var clickButtonListener : (()-> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.event_dialog_starred, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<LinearLayoutCompat>(R.id.lineRetrieve).setOnClickListener {
            clickButtonListener?.invoke()
            dismiss()
        }
    }

    fun setClickButtonListener(block : () -> Unit){
        clickButtonListener = block
    }
}