package com.smody.book.security.jwt

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtTokenProvider(
    @Value("\${token.secret-key}") secretKey: String,
    @Value("\${token.expiration}") val expirationInMilliseconds: Long
) {

    private val signingKey: SecretKey = Keys.hmacShaKeyFor(secretKey.encodeToByteArray())
    fun createToken(payload: Map<String, Any>): String {
        val now = Date()
        val validity = Date(now.time + expirationInMilliseconds)
        return Jwts.builder()
            .addClaims(payload)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(signingKey, SignatureAlgorithm.HS256)
            .compact()
    }

    fun getId(token: String): Long {
        return parseClaimsJws(token)
            .body
            .get("id", Integer::class.java)
            .toLong()
    }

    fun isValidToken(token: String): Boolean {
        return try {
            !parseClaimsJws(token).body.expiration.before(Date())
        } catch (e: JwtException) {
            false
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    private fun parseClaimsJws(token: String): Jws<Claims> {
        return Jwts.parserBuilder()
            .setSigningKey(signingKey)
            .build()
            .parseClaimsJws(token)
    }
}