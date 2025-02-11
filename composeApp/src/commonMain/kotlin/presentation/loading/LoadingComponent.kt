package presentation.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun LoadingComponent() {
    Box(
        modifier = Modifier.fillMaxSize().background(color = Color(202020)),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier.size(64.dp).clip(RoundedCornerShape(24.dp)))
        CircularProgressIndicator()
    }
}

@Preview
@Composable
private fun LoadingComponentPreview() {
    BoxWithConstraints {
        LoadingComponent()
    }
}