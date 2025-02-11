package presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import core.designsystem.KmpButton
import core.designsystem.KmpInputField
import domain.LoginType
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun AuthScreen(
    viewModel: AuthViewModel = koinViewModel(),
    navigateForward: (LoginType) -> Unit
) {

    AuthContent(
        viewModel = viewModel,
        navigateForward = navigateForward
    )
}

@Composable
private fun AuthContent(
    viewModel: AuthViewModel,
    navigateForward: (LoginType) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier.background(Color(202020)),
    ) { innerPaddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings)
                .padding(horizontal = 16.dp)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { focusManager.clearFocus() })
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            KmpInputField(
                modifier = Modifier.padding(bottom = 8.dp),
                value = state.email,
                onValueChanged = {
                    viewModel.updateEmail(it)
                },
                placeholderText = "Email",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )
            KmpInputField(
                modifier = Modifier.padding(bottom = 8.dp),
                value = state.password,
                onValueChanged = {
                    viewModel.updatePassword(it)
                },
                placeholderText = "Password",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
            )
            KmpButton(
                onClick = { navigateForward.invoke(LoginType.Verified(1)) },
                isEnable = state.isLoginAvailable,
                text = "Login"
            )
            KmpButton(
                onClick = { navigateForward.invoke(LoginType.Unverified) },
                text = "Login as guest"
            )
        }
    }
}

//@Preview
//@Composable
//private fun AuthPreview() {
//    AuthContent()
//}