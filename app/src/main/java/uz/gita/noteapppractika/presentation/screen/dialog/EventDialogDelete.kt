package uz.gita.noteapppractika.presentation.screen.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.gita.noteapppractika.R

class EventDialogDelete : BottomSheetDialogFragment(R.layout.event_dialog_delete) {

    private var clickDeleteButtonListener : (()-> Unit)? = null
    private var clickStarButtonListener : (()-> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.event_dialog_delete, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<LinearLayoutCompat>(R.id.lineDelete).setOnClickListener {
            clickDeleteButtonListener?.invoke()
            dismiss()
        }
        view.findViewById<LinearLayoutCompat>(R.id.lineStarred).setOnClickListener {
            clickStarButtonListener?.invoke()
            dismiss()
        }
    }

    fun setClickDeleteButtonListener(block : () -> Unit){
        clickDeleteButtonListener = block
    }
    fun setClickStarButtonListener(block : () -> Unit){
        clickStarButtonListener = block
    }
}