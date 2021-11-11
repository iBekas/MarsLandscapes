package space.picture.marslandscapes.view.notes


sealed class ItemNotes(open val id: Long)

data class ItemNote(override val id: Long, val name: String, val time: String, var isExpanded: Boolean): ItemNotes(id)
data class ItemCake(override val id: Long, val name: String, val date: String): ItemNotes(id)


