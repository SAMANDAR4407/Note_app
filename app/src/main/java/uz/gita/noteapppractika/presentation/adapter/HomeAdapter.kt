package uz.gita.noteapppractika.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.parseAsHtml
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.gita.noteapppractika.data.model.NoteData
import uz.gita.noteapppractika.databinding.ItemNoteBinding
import uz.gita.noteapppractika.utils.myApply

class HomeAdapter : ListAdapter<NoteData, HomeAdapter.Holder>(NoteCallBack) {
    private var listener: ((NoteData)->Unit)? = null
    private var updateListener: ((NoteData)->Unit)? = null

    fun setListener(block : (NoteData)->Unit){
        listener = block
    }
    fun setUpdateListener(block : (NoteData)->Unit){
        updateListener = block
    }
    object NoteCallBack : DiffUtil.ItemCallback<NoteData>() {
        override fun areItemsTheSame(oldItem: NoteData, newItem: NoteData): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: NoteData, newItem: NoteData): Boolean =
            oldItem.id == newItem.id
    }

    inner class Holder(private val binding: ItemNoteBinding) : ViewHolder(binding.root) {
        init {
            binding.itemView.setOnLongClickListener {
                listener?.invoke(currentList[adapterPosition])
                true
            }
            binding.itemView.setOnClickListener {
                updateListener?.invoke(currentList[adapterPosition])
            }
        }
        fun bind(){
            val data = currentList[adapterPosition]
            binding.myApply {
                txtTitle.text = data.title
                txtContent.text = data.content.parseAsHtml()
                txtData.text = data.createdAt
                txtContent.maxLines = 10
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind()
}