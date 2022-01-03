package com.app.kotlinspring

import com.app.kotlinspring.entity.Member
import com.app.kotlinspring.entity.MemberDTO
import com.app.kotlinspring.repository.MainRepository
import com.app.kotlinspring.service.MainService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

internal class MainServiceTest {
    private val mainRepository: MainRepository = mockk()
    private val mainService: MainService = MainService(mainRepository)

    @Test
    fun `회원 저장 테스트`() {
        // 준비
        val memberDTO = MemberDTO(1L, "김철수", "password123")
        val savedMember = Member(1L, "김철수", "password123")
        every { mainRepository.save(any<Member>()) }.returns (savedMember)

        // 실행
        val savedMemberEntity = mainService.saveMember(memberDTO)

        // 검증
        assertNotNull(savedMember)
        verify { mainRepository.save(any<Member>()) }
        assertThat(savedMemberEntity).isEqualTo(savedMember)
    }

}