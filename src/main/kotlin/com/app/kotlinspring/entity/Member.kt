package com.app.kotlinspring.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Member(
    @Id
    @Column(name = "member_id")
    var id: Long,
    var name: String,
    var password: String
) {
    override fun toString(): String {
        return "Member(id=$id, name='$name', password='$password')"
    }
}