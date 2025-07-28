package com.example.demo

import com.example.demo.proto.UserDetailsProto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import jakarta.servlet.http.HttpServletResponse

@RestController
class UserController {
    @GetMapping("/user/protobuf", produces = ["application/x-protobuf"])
    fun getUserProtobuf(response: HttpServletResponse): ResponseEntity<ByteArray> {
        // Build a hardcoded UserDetails object
        val userDetails = UserDetailsProto.UserDetails.newBuilder()
            .setId(123456789L)
            .setName("John Doe")
            .setEmail("john.doe@example.com")
            .setAge(30)
            .setIsActive(true)
            .setAccountBalance(12345.67)
            .setUserType(UserDetailsProto.UserType.ADMIN)
            .addPhoneNumbers("+1234567890")
            .addPhoneNumbers("+0987654321")
            .setProfilePicture(com.google.protobuf.ByteString.copyFrom(byteArrayOf(1,2,3,4,5)))
            .setAddress("123 Main St, Anytown, USA")
            .build()

        val headers = HttpHeaders()
        headers.contentType = MediaType.parseMediaType("application/x-protobuf")

        // Placeholder: Here you could call an external URL and parse the protobuf response
        // TODO: Integrate with external server/client

        return ResponseEntity.ok()
            .headers(headers)
            .body(userDetails.toByteArray())
    }

    @GetMapping("/user/json", produces = ["application/json"])
    fun getUserJson(): Map<String, Any> {
        // Return the same data as a JSON-compatible map
        return mapOf(
            "id" to 123456789L,
            "name" to "John Doe",
            "email" to "john.doe@example.com",
            "age" to 30,
            "is_active" to true,
            "account_balance" to 12345.67,
            "user_type" to "ADMIN",
            "phone_numbers" to listOf("+1234567890", "+0987654321"),
            "profile_picture" to listOf(1,2,3,4,5), // Represent bytes as list of ints for JSON
            "address" to "123 Main St, Anytown, USA"
        )
    }
} 