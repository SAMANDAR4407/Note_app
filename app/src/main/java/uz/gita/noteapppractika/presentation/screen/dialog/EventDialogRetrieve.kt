package uz.gita.noteapppractika.presentation.screen.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.gita.noteapppractika.R

class EventDialogRetrieve : BottomSheetDialogFragment(R.layout.event_dialog_delete) {

    private var retrieveListener : (()-> Unit)? = null
    private var deleteListener : (()-> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.event_dialog_retrieve, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<LinearLayoutCompat>(R.id.lineRetrieve).setOnClickListener {
            retrieveListener?.invoke()
            dismiss()
        }
        view.findViewById<LinearLayoutCompat>(R.id.lineDelete).setOnClickListener {
            deleteListener?.invoke()
            dismiss()
        }
    }

    fun setRetrieveListener(block : () -> Unit){
        retrieveListener = block
    }

    fun setDeleteListener(block : () -> Unit){
        deleteListener = block
    }
}