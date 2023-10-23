//package com.example.movieapp.presentation.screens.components
//
//import androidx.compose.foundation.isSystemInDarkTheme
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.DateRange
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import com.example.movieapp.R
//import com.example.movieapp.data.cache.models.MovieDetailsCache
//
//class MovieItem(
//    movie: List<MovieDetailsCache>,
//    ) {
//    Row {
//        PosterFilmComponentsForCache(
//            modifier = Modifier
//                .height(160.dp)
//                .width(120.dp),
//            navigateToDetails = {},
//            movie = item,
//        )
//        Column {
//            Text(
//                text = item.title,
//                style = MaterialTheme.typography.bodyLarge.copy(
//                    fontWeight = FontWeight.Normal,
//                    color = if (isSystemInDarkTheme()) Color.White
//                    else Color.Black
//                ),
//            )
//            Spacer(modifier = Modifier.height(22.dp))
//            Row {
//                Icon(
//                    modifier = Modifier.size(16.dp),
//                    imageVector = Icons.Default.DateRange,
//                    contentDescription = null,
//                    tint = if (isSystemInDarkTheme()) Color.White
//                    else Color.Black
//                )
//                Text(
//                    text = item.releaseDate,
//                    style = MaterialTheme.typography.bodyLarge.copy(
//                        fontWeight = FontWeight.Normal,
//                        color = if (isSystemInDarkTheme()) Color.White
//                        else Color.Black
//                    ),
//                )
//            }
//
//            Spacer(modifier = Modifier)
//            Row {
//                Icon(
//                    modifier = Modifier.size(16.dp),
//                    painter = painterResource(id = R.drawable.clock_icon),
//                    contentDescription = null,
//                    tint = if (isSystemInDarkTheme()) Color.White
//                    else Color.Black
//                )
//                Text(
//                    text = "${item.runtime.toString()} min ",
//                    style = MaterialTheme.typography.bodyLarge.copy(
//                        fontWeight = FontWeight.Normal,
//                        color = if (isSystemInDarkTheme()) Color.White
//                        else Color.Black
//                    ),
//                )
//            }
//
//        }
//    }
//}
//
//@Composable
//fun MovieItem(    movie: List<MovieDetailsCache>,
//) {
//
//}