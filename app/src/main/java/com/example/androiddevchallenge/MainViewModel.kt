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
package com.example.androiddevchallenge

import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.model.Puppy

class MainViewModel : ViewModel() {
    val puppies = listOf(
        Puppy(1, "Alfa", "dachshund", "40 days", "https://images.pexels.com/photos/4490129/pexels-photo-4490129.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500"),
        Puppy(2, "Bravo", "bulldog", "38 days", "https://images.pexels.com/photos/4587998/pexels-photo-4587998.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500"),
        Puppy(3, "Charlie", "husky", "32 days", "https://images.pexels.com/photos/3478875/pexels-photo-3478875.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500"),
        Puppy(4, "Delta", "border collie", "43 days", "https://images.pexels.com/photos/3908821/pexels-photo-3908821.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500"),
        Puppy(5, "Echo", "husky", "46 days", "https://images.pexels.com/photos/5877544/pexels-photo-5877544.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500"),
    )
}
