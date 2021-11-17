package space.picture.marslandscapes.view.notes


interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition:Int,toPosition:Int)
    fun onItemDismiss(position:Int)
}

