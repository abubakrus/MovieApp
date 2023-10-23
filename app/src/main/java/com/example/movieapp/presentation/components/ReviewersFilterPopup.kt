package com.example.movieapp.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.screens.detail.SortByItems

@Composable
fun ReviewersFilterPopup(
    modifier: Modifier = Modifier,
    onClick: (SortByItems) -> Unit,
) {
    DropdownMenu(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 8.dp),
        expanded = true,
        onDismissRequest = {},
    ) {
        DropDownOptionItem(
            textId = R.string.by_rating,
            onClick = { onClick(SortByItems.BY_RATING) },
        )
        Divider()
        DropDownOptionItem(
            textId = R.string.descending_order,
            onClick = { onClick(SortByItems.BY_RATING_DOWN) }
        )
        Divider()
        DropDownOptionItem(
            textId = R.string.new_ones_first,
            onClick = { onClick(SortByItems.BY_ADDED) }
        )
        Divider()
        DropDownOptionItem(
            textId = R.string.first_the_old_ones,
            onClick = { onClick(SortByItems.BY_ADDED_DOWN) }
        )
    }
}

@Composable
fun DropDownOptionItem(
    modifier: Modifier = Modifier,
    @StringRes textId: Int,
    onClick: () -> Unit,
) {
    DropdownMenuItem(
        text = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = textId),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
            Spacer(modifier = modifier.height(8.dp))
        },
        onClick = onClick
    )

}