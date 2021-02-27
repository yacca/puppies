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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.MainViewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Puppy
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun PuppyListScreen(selectPuppy: (Long) -> Unit) {
    val mainViewModel = viewModel<MainViewModel>()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.puppy_list_title)) })
        }
    ) { innerPadding: PaddingValues ->
        rememberScrollState(0)
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(mainViewModel.puppies.size) {
                PuppyListItem(
                    mainViewModel.puppies[it], selectPuppy
                )
            }
        }
    }
}

const val ListItemTestTag = "PuppyListItem"

@Composable
fun PuppyListItem(puppy: Puppy, selectPuppy: (Long) -> Unit) {
    Row(
        modifier = Modifier
            .testTag(ListItemTestTag)
            .fillMaxWidth()
            .clickable(onClick = { selectPuppy(puppy.id) })
            .padding(8.dp)
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        val imageDesc = stringResource(R.string.photo_description, puppy.name)
        CoilImage(
            data = puppy.photoUrl, modifier = Modifier.size(56.dp),
            contentDescription = imageDesc,
            contentScale = ContentScale.Crop,
            loading = {
                Box(modifier = Modifier.background(Color.LightGray))
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Row {
                Text(text = puppy.name, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                TypeChip(text = puppy.type)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = puppy.age)
        }
    }
}

@Composable
fun TypeChip(text: String, modifier: Modifier = Modifier) {
    Surface(color = MaterialTheme.colors.primary, shape = CircleShape, modifier = modifier) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}
