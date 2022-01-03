package com.app.kotlinspring.repository

import com.app.kotlinspring.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MainRepository : JpaRepository<Member, Long>
