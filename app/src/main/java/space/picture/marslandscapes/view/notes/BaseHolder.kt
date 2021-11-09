package space.picture.marslandscapes.view.notes

import android.view.View
import androidx.recyclerview.widget.RecyclerView


abstract class BaseHolder(view: View): RecyclerView.ViewHolder(view) {
    abstract fun bind(pair: Pair<ItemNotes,Boolean>)
}
