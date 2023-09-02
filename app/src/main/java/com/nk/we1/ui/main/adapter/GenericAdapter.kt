/**
 * Created by frisovanwaveren on 09/08/2017.
 */
package com.nk.we1.ui.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nk.we1.utils.extensions.inflate

class GenericAdapter<T>(
    private val createViewHolder: (View) -> AbstractViewHolder<T>,
    private val clickListener: ClickListener<T>?,
    private val itemLayoutID: Int,
    private val touchListener: TouchListener<T>? = null
) : RecyclerView.Adapter<GenericAdapter.AbstractViewHolder<T>>() {
    var items: List<T> = listOf()

    override fun onBindViewHolder(holder: AbstractViewHolder<T>, position: Int) {

        holder.setItem(
            items[position],
            clickListener,
            position == 0,
            position == items.size - 1,
            touchListener
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder<T> =
        createViewHolder(parent.inflate(itemLayoutID))

    override fun getItemCount() = items.size

    override fun setHasStableIds(hasStableIds: Boolean) {
        this.setHasStableIds(true)
    }

    abstract class AbstractViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        protected var clickListener: ClickListener<T>? = null
        protected var touchListener: TouchListener<T>? = null
        protected var isFirst: Boolean? = null
        protected var isLast: Boolean? = null
        protected var item: T? = null
        abstract fun bindItem(item: T)


        fun setItem(
            item: T,
            clickListener: ClickListener<T>?,
            isFirst: Boolean,
            isLast: Boolean,
            touchListener: TouchListener<T>? = null
        ) {
            this.clickListener = clickListener
            this.touchListener = touchListener
            this.isFirst = isFirst
            this.isLast = isLast
            this.item = item
            bindItem(item)
        }
    }

    /*override fun getItemId(position: Int): Long {
       return items[position].hashCode().toLong()
   }*/


    interface ClickListener<T> {
        fun onClicked(item: T)
    }

    interface TouchListener<T> {
        fun onTouched(item: View)
    }
}