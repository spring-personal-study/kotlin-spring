package com.app.kotlinspring.controller

import com.app.kotlinspring.entity.Member
import com.app.kotlinspring.entity.MemberDTO
import com.app.kotlinspring.service.MainService
import org.springframework.http.HttpEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1")
@RestController
class MainController(
    private val mainService: MainService
) {

    @PostMapping("/members")
    fun saveMember(@RequestBody memberDTO: MemberDTO): HttpEntity<Member> {
        return HttpEntity<Member>(mainService.saveMember(memberDTO))
    }

    /**
     *  전체 회원 조회: 미구현
     *  TODO: TEST 먼저 구현
     */
    /*
    @GetMapping("/members")
    fun getAllMember(): ResponseEntity<List<Member>> {
        return ResponseEntity<List<Member>>(mainService.findAllMember(), HttpStatus.OK)
    }
     */

}