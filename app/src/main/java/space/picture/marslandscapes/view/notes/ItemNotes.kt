package space.picture.marslandscapes.view.notes

import java.util.*


sealed class ItemNotes

data class ItemNote(val name: String, val time: String): ItemNotes()
data class ItemCake(val name: String, val date: String): ItemNotes()


