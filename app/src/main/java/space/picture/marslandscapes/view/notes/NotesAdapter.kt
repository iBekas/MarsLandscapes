package space.picture.marslandscapes.view.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import space.picture.marslandscapes.R
import space.picture.marslandscapes.databinding.ItemCakeBinding
import space.picture.marslandscapes.databinding.ItemNoteBinding
import java.text.SimpleDateFormat
import java.util.*


class NotesAdapter(private var data: MutableList<Pair<ItemNotes,Boolean>>, private var count: Int, private val myLocale: Locale) : RecyclerView.Adapter<BaseHolder>() {


    companion object {
        private const val TYPE_NOTE = 0
        private const val TYPE_CAKE = 1
    }

    fun appendItemNote() {
        data.add(Pair(generateItemNote(), false))
        notifyItemInserted(itemCount - 1)
    }

    fun appendItemCake() {
        data.add(Pair(generateItemCake(), false))
        notifyItemInserted(itemCount - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        return when (viewType) {
            TYPE_NOTE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
                NoteHolder(ItemNoteBinding.bind(view), data)
            }
            TYPE_CAKE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cake, parent, false)
                CakeHolder(ItemCakeBinding.bind(view))
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
                NoteHolder(ItemNoteBinding.bind(view), data)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        (holder).bind(data[position])
    }

    override fun getItemViewType(position: Int): Int {
        return when(data[position].first){
            is ItemNote -> TYPE_NOTE
            is ItemCake -> TYPE_CAKE
        }

    }

    private fun generateItemNote(): ItemNote{
        count++
        return ItemNote(String.format("Заметка №%d", count), SimpleDateFormat("dd.MM.yyyy HH:mm", myLocale).format(Date()))
    }

    private fun generateItemCake(): ItemCake{
        return ItemCake("Вася", "01.01.2021")
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

