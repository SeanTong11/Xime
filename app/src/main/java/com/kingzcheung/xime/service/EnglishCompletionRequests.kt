package com.kingzcheung.xime.service

import java.util.concurrent.atomic.AtomicLong

/** 模式切换后，即使重新键入同一前缀，也不能接收切换前的补全结果。 */
internal class EnglishCompletionRequests {
    private val generation = AtomicLong()
    fun capture(): Long = generation.get()
    fun invalidate() { generation.incrementAndGet() }
    fun isCurrent(request: Long): Boolean = request == generation.get()
}
