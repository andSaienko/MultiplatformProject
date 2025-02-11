package presentation.details

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DetailsScreen(
    viewModel: DetailsViewModel = koinViewModel<DetailsViewModel>(),
    onBackClicked: () -> Unit
) {

    val state by viewModel.product.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TextButton(onClick = onBackClicked) {
                        Text(text = "Back", fontSize = 16.sp)
                    }
                },
            )
        }
    ) { paddingValues ->
        val scrollState = rememberScrollableState { 0f }
        Column(modifier = Modifier.padding(paddingValues).scrollable(scrollState, orientation = Orientation.Vertical)) {
            state?.images?.let {
                LazyRow {
                    items(
                        items = it,
                        key = {
                            it.hashCode()
                        }) { imageUrl ->
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(24.dp),
                            elevation = CardDefaults.cardElevation(2.dp)
                        ) {
                            AsyncImage(
                                model = imageUrl,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = state?.category?.name.toString())
                Text(text = state?.title.toString(), fontWeight = FontWeight.Bold, fontSize = 22.sp)
                Text(text = state?.description.toString())
            }
        }
    }
}