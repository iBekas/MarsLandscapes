package space.picture.marslandscapes.view.notes

import android.view.View
import space.picture.marslandscapes.databinding.ItemNoteBinding


class NoteHolder(
    private val binding: ItemNoteBinding, private var data: MutableList<Pair<ItemNotes,Boolean>>
) : BaseHolder(binding.root) {

    override fun bind(pair: Pair<ItemNotes,Boolean>) {
        if (pair.first is ItemNote) {
            binding.noteName.text = (pair.first as ItemNote).name
            binding.time.text = (pair.first as ItemNote).time
            binding.marsDescriptionTextView.visibility = if (pair.second) View.VISIBLE else View.GONE
        }
    }

//    private fun toggleText() {
//        data[layoutPosition] = data[layoutPosition].let {
//            it.first to !it.second
//        }
//        notifyItemChanged(layoutPosition)
//    }
}
