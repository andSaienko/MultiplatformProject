package domain

sealed interface LoginType {
    data object Unverified : LoginType
    data class Verified(val id: Int) : LoginType
}