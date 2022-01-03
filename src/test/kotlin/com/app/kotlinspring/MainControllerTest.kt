package com.app.kotlinspring

import com.app.kotlinspring.controller.MainController
import com.app.kotlinspring.entity.MemberDTO
import com.app.kotlinspring.service.MainService
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder
import org.springframework.web.filter.CharacterEncodingFilter


@ExtendWith(SpringExtension::class)
@WebMvcTest(value = [MainController::class])
internal class MainControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var mainService: MainService

    @Autowired
    private lateinit var mapper: ObjectMapper

    @BeforeEach
    fun init() {
        mockMvc = MockMvcBuilders.standaloneSetup(MainController(mainService))
            .addFilters<StandaloneMockMvcBuilder>(CharacterEncodingFilter("UTF-8", true))
            .build()
    }

    @Test
    fun `메인 컨트롤러의 POST saveMember() 테스트_회원 저장 성공`() {
        val content: Map<String, String> = mapOf<String, String>(
            "id" to "1",
            "name" to "김철수",
            "password" to "password123"
        )
        val json = mapper.writeValueAsString(content)
        val deserializedMemberDTO = mapper.readValue(json, MemberDTO::class.java)

        every { mainService.saveMember(any<MemberDTO>()) } returns (deserializedMemberDTO.toEntity())

        mockMvc.perform(
            post("/v1/members")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .content(json)
        )
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(deserializedMemberDTO.id))
            .andExpect(jsonPath("$.name").value(deserializedMemberDTO.name))
            .andExpect(jsonPath("$.password").value(deserializedMemberDTO.password))
    }
}