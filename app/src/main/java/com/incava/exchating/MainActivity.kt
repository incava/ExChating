package com.incava.exchating

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.incava.exchating.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter: ChatingAdapter
    val datas = mutableListOf<ChatData>() // 톡방에 담을 배열
    val names = arrayListOf("ingi","ingi친구1","오올","성동오랑씨") //친구이름배열
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = ChatingAdapter() //어댑터 객체생성
        binding.apply {
            rcv.adapter = adapter //어댑터 연결.
            btnMy.setOnClickListener {// 나의 배열
                datas.add(ChatData(R.drawable.mine,"ingan",et.text.toString(),1))
                et.text?.clear()
                adapter.notifyItemChanged(datas.size-1) //노티파이해서 알려주기. 어차피 삭제가 없어서 계속 쌓임. 그러므로 마지막에만 알려주면 됨.
                rcv.scrollToPosition(datas.size-1)

            }
            btnYour.setOnClickListener {
                datas.add(ChatData(R.drawable.yours,names[Random.nextInt(names.size)],et.text.toString(),0))
                et.text?.clear()
                adapter.notifyItemChanged(datas.size-1) //노티파이해서 알려주기. 어차피 삭제가 없어서 계속 쌓임. 그러므로 마지막에만 알려주면 됨.
                rcv.scrollToPosition(datas.size-1)
            }

        }
        datas.apply {
            add(ChatData(R.drawable.yours,"ingi","안녕하세용~",0))
            add(ChatData(R.drawable.mine,"ingan","하이여!",1))
            add(ChatData(R.drawable.yours,"오올","굿모닝",0))
            add(ChatData(R.drawable.yours,"오올","반갑",0))
            add(ChatData(R.drawable.mine,"ingan","오늘은 몇시에 일어날건가요?",1))
        }
        adapter.dataList = datas //데이터 전달. 생성자로 줄걸그랫나..
        adapter.notifyDataSetChanged() //노티파이해서 알려주기.

    }



}