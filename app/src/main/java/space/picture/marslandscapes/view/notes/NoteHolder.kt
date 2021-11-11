package space.picture.marslandscapes.view.notes

import android.view.View
import space.picture.marslandscapes.databinding.ItemNoteBinding


class NoteHolder(
    private val binding: ItemNoteBinding,
    private var data: MutableList<ItemNotes>,
    private val notifyItemChange: (Int) -> Unit
) : BaseHolder(binding.root) {

    override fun bind(item: ItemNotes) {
        if (item is ItemNote) {
            binding.noteName.text = item.name
            binding.time.text = item.time
            binding.marsDescriptionTextView.visibility = if (item.isExpanded) View.VISIBLE else View.GONE
            binding.noteName.setOnClickListener { toggleText() }
        }
    }

    private fun toggleText() {
       (data[layoutPosition] as? ItemNote)?.run{
           data[layoutPosition] = this.copy(isExpanded = !this.isExpanded)
        }
        notifyItemChange(layoutPosition)
    }
}
