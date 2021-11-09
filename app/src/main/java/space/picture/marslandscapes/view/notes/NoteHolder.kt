package space.picture.marslandscapes.view.notes

import space.picture.marslandscapes.databinding.ItemNoteBinding


class NoteHolder(
    private val binding: ItemNoteBinding,
) : BaseHolder(binding.root) {

    override fun bind(itemNotes: ItemNotes) {
        if (itemNotes is ItemNote) {
            binding.noteName.text = itemNotes.name
            binding.time.text = itemNotes.time
        }
    }
}
