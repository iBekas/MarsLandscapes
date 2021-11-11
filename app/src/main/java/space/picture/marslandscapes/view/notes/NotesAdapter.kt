package space.picture.marslandscapes.view.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import space.picture.marslandscapes.R
import space.picture.marslandscapes.databinding.ItemCakeBinding
import space.picture.marslandscapes.databinding.ItemNoteBinding
import java.text.SimpleDateFormat
import java.util.*


class NotesAdapter(
    private var data: MutableList<ItemNotes>,
    private var count: Int,
    private val myLocale: Locale
) : RecyclerView.Adapter<BaseHolder>() {

    private var idNote: Long = 0
    private var idCake: Long = 0

    companion object {
        private const val TYPE_NOTE = 0
        private const val TYPE_CAKE = 1
    }

    fun appendItemNote() {
        data.add(generateItemNote())
        notifyItemInserted(itemCount - 1)
    }

    fun appendItemCake() {
        data.add(generateItemCake())
        notifyItemInserted(itemCount - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        return when (viewType) {
            TYPE_NOTE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
                NoteHolder(ItemNoteBinding.bind(view), data, notifyItemChange)
            }
            TYPE_CAKE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cake, parent, false)
                CakeHolder(ItemCakeBinding.bind(view))
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
                NoteHolder(ItemNoteBinding.bind(view), data, notifyItemChange)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        (holder).bind(data[position])
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is ItemNote -> TYPE_NOTE
            is ItemCake -> TYPE_CAKE
        }

    }

    private fun generateItemNote(): ItemNote {
        count++
        idNote++
        return ItemNote(
            id = idNote,
            name = String.format("Заметка №%d", count),
            time = SimpleDateFormat("dd.MM.yyyy HH:mm", myLocale).format(Date()), isExpanded = false
        )
    }

    private fun generateItemCake(): ItemCake {
        idCake++
        return ItemCake(id = idCake, name = "Вася", date = "01.01.2021")
    }

    private val notifyItemChange = fun(layoutPosition: Int) {
        notifyItemChanged(layoutPosition)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

