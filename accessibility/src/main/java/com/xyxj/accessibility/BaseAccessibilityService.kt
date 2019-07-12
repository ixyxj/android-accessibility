package com.xyxj.robot.dingding

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.os.Handler
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

/**
 * created by ixyxj on 2018/6/21 10:17
 * Copyright (c) 2018 in FORETREE
 */
abstract class BaseAccessibilityService : AccessibilityService() {
    protected val mHandler = Handler()

    abstract fun onAccessibilityEvent(event: AccessibilityEvent, window: AccessibilityNodeInfo)

    override fun onServiceConnected() {
        serviceInfo = AccessibilityServiceInfo().apply {
            eventTypes = AccessibilityEvent.TYPES_ALL_MASK
            feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC
            packageNames = getPackageNames()
            notificationTimeout = getNotificationTimeout()
        }
        Log.d("==>", "==>onServiceConnected")
    }

    protected fun getPackageNames(): Array<out String>? = PackageEnum.values().map { it.packageName }.toTypedArray()

    protected fun getNotificationTimeout(): Long = 100

    override fun onInterrupt() {

    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        onAccessibilityEvent(event, rootInActiveWindow ?: return)
    }
}