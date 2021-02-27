/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.MainViewModel
import com.example.androiddevchallenge.R
import dev.chrisbanes.accompanist.coil.CoilImage

const val DetailsTitleTestTag = "PuppyDetailsTitle"

@Composable
fun PuppyDetailsScreen(
    id: Long,
    upPress: () -> Unit
) {
    val mainViewModel = viewModel<MainViewModel>()
    val puppy = mainViewModel.puppies.single { it.id == id }
    val imageDesc = stringResource(R.string.photo_description, puppy.name)

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Box {
            CoilImage(
                data = puppy.photoUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .scrim(listOf(Color(0x80000000), Color(0x33000000)))
                    .aspectRatio(4f / 3f),
                contentDescription = imageDesc,
                contentScale = ContentScale.Crop,
                loading = {
                    Box(modifier = Modifier.background(Color.LightGray))
                }
            )
            TopAppBar(
                title = {
                    Text(
                        text = puppy.name,
                        modifier = Modifier.testTag(DetailsTitleTestTag)
                    )
                },
                navigationIcon = {
                    val navUpDesc = stringResource(R.string.up_button_description)
                    IconButton(onClick = upPress) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = navUpDesc
                        )
                    }
                },
                backgroundColor = Color.Transparent,
                contentColor = Color.White,
                elevation = 0.dp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = puppy.name,
            style = MaterialTheme.typography.h3,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.subtitle, puppy.age, puppy.type),
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.puppy_description),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}

fun Modifier.scrim(colors: List<Color>): Modifier = drawWithContent {
    drawContent()
    drawRect(Brush.verticalGradient(colors))
}
