package presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.auth.model.AuthScreenState

class AuthViewModel : ViewModel() {

    private val _state = MutableStateFlow(AuthScreenState())
    val state = _state.asStateFlow()

    fun updateEmail(email: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    email = email,
                    isLoginAvailable = it.password.length >= 8 && email.contains("@")
                )
            }
        }
    }

    fun updatePassword(password: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    password = password,
                    isLoginAvailable = password.length >= 8 && it.email.contains("@")
                )
            }
        }
    }
}