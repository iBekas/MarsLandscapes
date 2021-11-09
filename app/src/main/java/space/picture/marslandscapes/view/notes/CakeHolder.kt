package space.picture.marslandscapes.view.notes

import space.picture.marslandscapes.databinding.ItemCakeBinding


class CakeHolder(private val binding: ItemCakeBinding): BaseHolder(binding.root) {
    override fun bind(itemNotes: ItemNotes) {
        if (itemNotes is ItemCake) {
            binding.noteName.text = itemNotes.name
            binding.time.text = itemNotes.date
        }
    }
}

