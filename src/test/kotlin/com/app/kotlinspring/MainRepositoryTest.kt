package com.app.kotlinspring

import com.app.kotlinspring.entity.Member
import com.app.kotlinspring.repository.MainRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import javax.transaction.Transactional

@DataJpaTest
internal class MainRepositoryTest(
    @Autowired
    private var mainRepository: MainRepository
) {

    @Test
    @Transactional
    fun `회원 저장 성공 테스트`() {
        val member: Member = Member(1L, "김철수", "password")
        val savedMember = mainRepository.save(member)
        assertThat(savedMember.id).isEqualTo(member.id)
        assertThat(savedMember.name).isEqualTo(member.name)
        assertThat(savedMember.password).isEqualTo(member.password)
    }

}
