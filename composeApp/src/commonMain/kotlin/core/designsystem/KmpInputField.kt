package core.designsystem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun KmpInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit,
    placeholderText: String? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onTrailingIconClick: () -> Unit = { }
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        singleLine = true,
        value = value,
        onValueChange = onValueChanged,
        placeholder = {
            placeholderText?.let {
                Text(text = it)
            }
        },
        leadingIcon = {
            leadingIcon?.let {
                Icon(
//                    imageVector = Icons.Default.Search,
                    imageVector = leadingIcon,
                    contentDescription = null
                )
            }
        },
        trailingIcon = {
            trailingIcon?.let {
                Icon(
                    modifier = Modifier.clickable {
                        onTrailingIconClick.invoke()
//                        viewModel.search("")
//                        focusManager.clearFocus()
                    },
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                )
            }
//            if (state.searchQuery.isNotEmpty()) {
//                Icon(
//                    modifier = Modifier.clickable {
//                        viewModel.search("")
//                        focusManager.clearFocus()
//                    },
//                    imageVector = Icons.Default.Close,
//                    contentDescription = null,
//                )
//            }
        },
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
    )
}