package presentation.home

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import core.designsystem.KmpInputField
import org.koin.compose.viewmodel.koinViewModel
import presentation.home.element.HomeListElement
import core.designsystem.LoadingComponent

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
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().pointerInput(Unit) {
                    detectTapGestures(onTap = { focusManager.clearFocus() })
                }) {
                LazyVerticalGrid(
                    modifier = Modifier.nestedScroll(object : NestedScrollConnection {
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
                            Row(
                                modifier = Modifier.height(60.dp).padding(horizontal = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                KmpInputField(
                                    modifier = Modifier.weight(1f),
                                    value = state.searchQuery,
                                    onValueChanged = { viewModel.search(it) },
                                    singleLine = true,
                                    placeholderText = "Search product",
                                    leadingIcon = Icons.Default.Search,
                                    trailingIcon = if (state.searchQuery.isNotEmpty()) Icons.Default.Close else null,
                                    onTrailingIconClick = {
                                        viewModel.search("")
                                        focusManager.clearFocus()
                                    }
                                )
                                Icon(
                                    modifier = Modifier.padding(8.dp).size(42.dp),
                                    imageVector = Icons.Default.Face,
                                    contentDescription = null,
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

                    item(span = { GridItemSpan(2) }) {
                        Spacer(modifier = Modifier.height(innerPaddings.calculateBottomPadding()))
                    }
                }
            }
        }
    }
}