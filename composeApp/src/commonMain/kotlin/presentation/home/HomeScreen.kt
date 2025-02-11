package presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import multiplatformproject.composeapp.generated.resources.Res
import multiplatformproject.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import presentation.home.element.HomeListElement
import presentation.loading.LoadingComponent

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    navigateToDetails: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold { innerPaddings ->

        val scrollState = rememberLazyGridState()
        val focusManager = LocalFocusManager.current


        if (state.isLoading) {
            LoadingComponent()
            return@Scaffold
        }

        Box(modifier = Modifier.padding(horizontal = 16.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .pointerInput(Unit) {
                        detectTapGestures(onTap = { focusManager.clearFocus() })
                    }
            ) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .nestedScroll(object : NestedScrollConnection {
                            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                                focusManager.clearFocus()
                                return Offset.Zero
                            }
                        }),
                    columns = GridCells.Fixed(2),
                    state = scrollState,
                ) {
                    item(span = { GridItemSpan(2) }) {
                        Column {
                            Spacer(modifier = Modifier.size(innerPaddings.calculateTopPadding()))
                            Row(modifier = Modifier.height(64.dp)) {
                                TextField(
                                    modifier = Modifier.weight(1f),
                                    shape = RoundedCornerShape(24.dp),
                                    singleLine = true,
                                    value = state.searchQuery,
                                    onValueChange = {
                                        viewModel.search(it)
                                    },
                                    placeholder = {
                                        Text(text = "Search product")
                                    },
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.Search,
                                            contentDescription = null
                                        )
                                    },
                                    trailingIcon = {
                                        if (state.searchQuery.isNotEmpty()) {
                                            Icon(
                                                modifier = Modifier.clickable {
                                                    viewModel.search("")
                                                    focusManager.clearFocus()
                                                },
                                                imageVector = Icons.Default.Close,
                                                contentDescription = null,
                                            )
                                        }
                                    },
                                    colors = TextFieldDefaults.colors(
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent
                                    ),
                                )
                                Icon(
                                    modifier = Modifier.fillMaxHeight(),
                                    painter = painterResource(Res.drawable.compose_multiplatform),
                                    contentDescription = null
                                )
                            }
                        }
                    }

                    items(items = state.products, key = { product -> product.id }) { product ->
                        HomeListElement(
                            onClick = {
                                focusManager.clearFocus()
                                navigateToDetails.invoke(product.id)
                            },
                            productImageUrl = product.images.first(),
                            productTitle = product.title,
                            productPrice = product.price
                        )
                    }
                }
            }
        }
    }
}