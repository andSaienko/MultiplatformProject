package core.designsystem

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun KmpButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isEnable: Boolean = true,
    text: String? = null
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        enabled = isEnable
    ) {
        text?.let {
            Text(text = text)
        }
    }
}