package com.smody.book.security

import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController {

    @GetMapping("/auth/login")
    fun login(@AuthenticationPrincipal oAuthPrincipal: OAuthPrincipal): ResponseEntity<TokenResponse> {
        return ResponseEntity.ok(TokenResponse(oAuthPrincipal))
    }
}