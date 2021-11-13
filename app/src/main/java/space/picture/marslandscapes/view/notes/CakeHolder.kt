package space.picture.marslandscapes.view.notes

import space.picture.marslandscapes.databinding.ItemCakeBinding


class CakeHolder(private val binding: ItemCakeBinding): BaseHolder(binding.root) {
    override fun bind(item: ItemNotes) {
        if (item is ItemCake) {
            binding.noteName.text = item.name
            binding.time.text = item.date
        }
    }
}

