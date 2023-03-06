package com.smody.book.member.domain

import org.springframework.data.repository.Repository

interface MemberRepository: Repository<Member, Long> {

    fun save(member: Member): Member

    fun findByEmail(email: String): Member?

    fun existsById(id: Long): Boolean
}