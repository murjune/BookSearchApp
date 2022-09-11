package org.techtown.booksearchapp.util

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore(name = "myBook_dataStore")
