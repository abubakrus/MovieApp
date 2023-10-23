package com.example.movieapp.presentation.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.domain.models.ReviewsDomain
import com.example.movieapp.presentation.theme.LightBlue
import kotlinx.coroutines.flow.StateFlow


@Composable
fun ReviewsItemList(
    reviewersFlow: StateFlow<List<ReviewsDomain>>, modifier: Modifier = Modifier
) {
    val reviewers = reviewersFlow.collectAsStateWithLifecycle().value
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = reviewers
        ) { item ->
            ReviewsItem(reviewers = item)
        }
    }
}

@Composable
fun ReviewsItem(
    reviewers: ReviewsDomain, modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            AsyncImage(
                model = reviewers.reviewsDetails.avatarPath,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.profile_default_avatar),
                error = painterResource(id = R.drawable.profile_default_avatar),
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = reviewers.reviewsDetails.rating.toString(),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = LightBlue
                ),
                fontWeight = FontWeight.SemiBold,
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = reviewers.author, style = MaterialTheme.typography.bodyMedium.copy(
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black
                ), fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = reviewers.content,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black
                ),
                fontWeight = FontWeight.Light,
            )
        }
    }
}