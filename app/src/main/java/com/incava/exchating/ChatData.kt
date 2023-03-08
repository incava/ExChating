package com.incava.exchating

data class ChatData(
    val picture : Int, //사용안할것이지만 넣어놓음. 1대1 채팅방식이기 때문. (연습용)
    val name : String,
    val comment : String,
    val type : Int, // 뷰타입 생성.
)
