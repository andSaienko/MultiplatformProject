package core.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun KmpErrorComponent() {
    Box(
        modifier = Modifier.fillMaxSize().background(color = Color(202020)),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier.size(64.dp).clip(RoundedCornerShape(24.dp)))
        Icon(imageVector = Icons.Default.Close, contentDescription = null)
    }
}