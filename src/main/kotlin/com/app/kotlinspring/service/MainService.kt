package com.app.kotlinspring.service

import com.app.kotlinspring.entity.Member
import com.app.kotlinspring.entity.MemberDTO
import com.app.kotlinspring.repository.MainRepository
import org.springframework.stereotype.Service

@Service
class MainService(
    var mainRepository: MainRepository
) {

    fun saveMember(memberDTO: MemberDTO): Member {
        return mainRepository.save(memberDTO.toEntity())
    }
}
