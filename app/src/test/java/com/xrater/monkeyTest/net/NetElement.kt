package com.xrater.monkeyTest.net

import com.xrater.monkeyTest.system.SystemElement
import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder


class NetElement(element: SystemElement) {

    val center = element.obj.visibleCenter
    val isCheckable: Boolean = element.obj.isCheckable
    val isClickable: Boolean = element.obj.isClickable
    val isFocusable: Boolean = element.obj.isFocusable
    val isLongClickable: Boolean = element.obj.isLongClickable
    val resourceName: String = element.obj.resourceName
    val className: String = element.obj.className

    override fun equals(other: Any?): Boolean {
        return if (other is NetElement) {
            EqualsBuilder()
                .append(this.isCheckable, other.isCheckable)
                .append(this.isClickable, other.isClickable)
                .append(this.isLongClickable, other.isLongClickable)
                .append(this.isFocusable, other.isFocusable)
                .append(this.isFocusable, other.isFocusable)
                .append(this.resourceName, other.resourceName)
                .append(this.className, other.className)
                .isEquals
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return HashCodeBuilder()
            .append(this.isCheckable)
            .append(this.isClickable)
            .append(this.isFocusable)
            .append(this.isLongClickable)
            .append(this.resourceName)
            .append(this.className)
            .build()
    }

}