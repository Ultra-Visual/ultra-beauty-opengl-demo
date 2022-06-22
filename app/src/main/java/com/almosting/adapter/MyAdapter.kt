package com.almosting.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.almosting.ultrabeauty.R


class MyAdapter(private val context: Context, private val titles: List<String>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>(), View.OnClickListener {
    var mSelectIndex = 0
    private var mOnItemClickListener: OnItemClickListener? = null

    fun addOnItemClickListener(onItemClickListener: OnItemClickListener) {
        mOnItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.sample_item_layout, parent, false)
        val myViewHolder = MyViewHolder(view)
        view.setOnClickListener(this)
        return myViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mTitle.text = titles[position]
        if (position == mSelectIndex) {
            holder.mRadioButton.isChecked = true
            holder.mTitle.setTextColor(context.resources.getColor(R.color.colorAccent))
        } else {
            holder.mRadioButton.isChecked = false
            holder.mTitle.text = titles[position]
            holder.mTitle.setTextColor(Color.GRAY)
        }
        holder.itemView.tag = position;
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onClick(v: View) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener!!.onItemClick(v, v.tag as Int)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mRadioButton: RadioButton
        var mTitle: TextView

        init {
            mRadioButton = itemView.findViewById(R.id.radio_btn)
            mTitle = itemView.findViewById(R.id.item_title)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }
}