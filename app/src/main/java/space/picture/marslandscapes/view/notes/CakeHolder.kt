package space.picture.marslandscapes.view.notes

import space.picture.marslandscapes.databinding.ItemCakeBinding


class CakeHolder(private val binding: ItemCakeBinding): BaseHolder(binding.root) {
    override fun bind(pair: Pair<ItemNotes,Boolean>) {
        if (pair.first is ItemCake) {
            binding.noteName.text = (pair.first as ItemCake).name
            binding.time.text = (pair.first as ItemCake).date
        }
    }
}

