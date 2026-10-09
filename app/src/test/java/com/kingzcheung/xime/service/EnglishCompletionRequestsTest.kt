package com.kingzcheung.xime.service

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class EnglishCompletionRequestsTest {
    @Test fun `切换纯英文后丢弃在途补全`() {
        val requests = EnglishCompletionRequests()
        val pending = requests.capture()
        requests.invalidate()
        assertFalse(requests.isCurrent(pending))
        assertTrue(requests.isCurrent(requests.capture()))
    }

    @Test fun `关闭再开启同一前缀也不能接受旧结果`() {
        val requests = EnglishCompletionRequests()
        val pending = requests.capture()
        requests.invalidate()
        requests.invalidate()
        assertFalse(requests.isCurrent(pending))
    }
}
