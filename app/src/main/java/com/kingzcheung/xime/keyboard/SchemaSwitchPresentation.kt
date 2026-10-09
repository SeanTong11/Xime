package com.kingzcheung.xime.keyboard

/** 菜单与切换反馈共用当前状态；自定义方案按自己的 states/abbrev 展示。 */
data class SchemaSwitchPresentation(val label: String, val textIcon: String)

fun schemaSwitchPresentation(
    name: String,
    states: List<String>,
    abbrev: List<String>,
    currentIndex: Int,
): SchemaSwitchPresentation {
    val index = if (currentIndex in states.indices) currentIndex else 0
    val state = states.getOrNull(index).orEmpty()
    val label = abbrev.getOrNull(index)?.takeIf { it.isNotBlank() }
        ?: state.ifBlank { name }
    val icon = when (name) {
        "ascii_mode" -> if (index == 0) "中" else "EN"
        "full_shape" -> if (index == 0) "半" else "全"
        "ascii_punct" -> if (index == 0) "，" else ","
        else -> label.firstOrNull()?.toString().orEmpty()
    }
    return SchemaSwitchPresentation(label, icon)
}
