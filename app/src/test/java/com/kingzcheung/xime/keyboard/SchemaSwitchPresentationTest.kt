package com.kingzcheung.xime.keyboard

import org.junit.Assert.assertEquals
import org.junit.Test

class SchemaSwitchPresentationTest {
    @Test fun `中英图标与当前状态一致`() {
        val states = listOf("中文", "西文")
        assertEquals(SchemaSwitchPresentation("中文", "中"), schemaSwitchPresentation("ascii_mode", states, emptyList(), 0))
        assertEquals(SchemaSwitchPresentation("西文", "EN"), schemaSwitchPresentation("ascii_mode", states, emptyList(), 1))
    }

    @Test fun `全角与英文标点不再显示初始图标`() {
        assertEquals("全", schemaSwitchPresentation("full_shape", listOf("半角", "全角"), emptyList(), 1).textIcon)
        assertEquals(",", schemaSwitchPresentation("ascii_punct", listOf("中文标点", "英文标点"), emptyList(), 1).textIcon)
    }

    @Test fun `自定义多状态开关使用当前缩写`() {
        assertEquals(SchemaSwitchPresentation("港", "港"), schemaSwitchPresentation("", listOf("简体", "繁体", "香港字形"), listOf("简", "繁", "港"), 2))
    }

    @Test fun `缺失缩写与非法下标安全回退`() {
        assertEquals("繁体", schemaSwitchPresentation("custom", listOf("简体", "繁体"), listOf("简"), 1).label)
        assertEquals("简体", schemaSwitchPresentation("custom", listOf("简体", "繁体"), emptyList(), -1).label)
        assertEquals("custom", schemaSwitchPresentation("custom", emptyList(), emptyList(), 9).label)
    }
}
