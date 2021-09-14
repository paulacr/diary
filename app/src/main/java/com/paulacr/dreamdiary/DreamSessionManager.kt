package com.paulacr.dreamdiary

import com.paulacr.dreamdiary.data.repository.DreamListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.Continuation
import kotlin.coroutines.suspendCoroutine

class DreamSessionManager<Dream> {

    var cachedDreamId = -1L
}