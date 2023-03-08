package com.incava.exchating

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.incava.exchating.ViewTypes.Companion.LEFT_POSITION
import com.incava.exchating.ViewTypes.Companion.RIGHT_POSITION
import com.incava.exchating.databinding.MyChatItemBinding
import com.incava.exchating.databinding.YourChatItemBinding

/**
 * 리사이클러뷰 어댑터로 채팅을 두개로 나누어 리사이클러뷰로 만드는 클래스
 */
class ChatingAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        var dataList = mutableListOf<ChatData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {
        return when (viewType) {
            LEFT_POSITION -> {// 상대방의 채팅
                val binding =
                    YourChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                LeftViewHolder(binding)
            }

            RIGHT_POSITION -> {//나의 채팅
                val binding =
                    MyChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                RightViewHolder(binding)
            }

            else -> throw RuntimeException("Error") //0 또는 1이 아닌 수가 올 때 예외처리.
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(dataList[position].type){
            LEFT_POSITION-> (holder as LeftViewHolder).bind(dataList[position]) //뷰의 위치를 받아 바인딩.
            RIGHT_POSITION-> (holder as RightViewHolder).bind(dataList[position])
            else -> throw RuntimeException("Error")
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
    override fun getItemViewType(position: Int): Int { //아이템 타입 리턴 -> onCreateViewholder에서 viewType을 가져올때 메서드를 오버라이딩.
        return dataList[position].type
    }

    inner class LeftViewHolder(private val binding: YourChatItemBinding) ://상대방의 화면 뷰홀더
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ChatData) {
            binding.apply {
                tvName.text = data.name
                tvComment.text = data.comment
                Glide.with(binding.root)
                    .load(data.picture)
                    .into(iv)
            }
        }
    }

    inner class RightViewHolder(private val binding: MyChatItemBinding) : //나의 화면 뷰홀더
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ChatData) {
            binding.apply {
                tvName.text = data.name
                tvComment.text = data.comment
                Glide.with(binding.root)
                    .load(data.picture)
                    .into(iv)
            }
        }
    }
}