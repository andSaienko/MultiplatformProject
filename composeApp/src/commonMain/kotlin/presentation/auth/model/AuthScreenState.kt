package presentation.auth.model

data class AuthScreenState(
    val email: String = "",
    val password: String = "",
    val isLoginAvailable: Boolean = false
)
