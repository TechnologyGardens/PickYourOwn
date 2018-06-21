package com.technologygardens.pickyourown.utils

import java.time.Month
import java.time.format.TextStyle
import java.util.*

inline fun minuteFmt(minute: Int): String = minute.toString().padStart(2, '0')
inline fun monthFmt(month: Month): String = month.getDisplayName(TextStyle.FULL, Locale.getDefault())

