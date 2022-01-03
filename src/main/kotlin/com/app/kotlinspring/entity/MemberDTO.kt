package com.app.kotlinspring.entity

class MemberDTO(
    var id: Long,
    var name: String,
    var password: String
) {
    fun toEntity(): Member {
        return Member(id, name, password)
    }
}