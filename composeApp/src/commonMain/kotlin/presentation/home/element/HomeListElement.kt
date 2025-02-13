package presentation.home.element

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import multiplatformproject.composeapp.generated.resources.Res
import multiplatformproject.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeListElement(
    onClick: () -> Unit,
    productImageUrl: String,
    productTitle: String,
    productPrice: Int
) {
    Box(modifier = Modifier.padding(horizontal = 4.dp)) {
        Card(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth()
                .clickable {
                    onClick.invoke()
                },
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(18.dp)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = productImageUrl,
                    contentDescription = null,
                    error = painterResource(Res.drawable.compose_multiplatform)
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = productTitle,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "$productPrice$",
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeListElementPreview() {
    HomeListElement(
        onClick = {},
        productImageUrl = "qwe",
        productTitle = "title",
        productPrice = 40
    )
}