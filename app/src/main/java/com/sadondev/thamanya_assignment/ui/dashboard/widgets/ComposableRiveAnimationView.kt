package com.sadondev.thamanya_assignment.ui.dashboard.widgets


import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import app.rive.runtime.kotlin.RiveAnimationView
import app.rive.runtime.kotlin.controllers.ControllerStateManagement
import app.rive.runtime.kotlin.core.Fit


@OptIn(ControllerStateManagement::class)
@Composable
fun ComposableRiveAnimationView(
    modifier: Modifier = Modifier,
    @RawRes animation: Int,
    stateMachineName: String? = null,
    alignment: app.rive.runtime.kotlin.core.Alignment = app.rive.runtime.kotlin.core.Alignment.CENTER,
    fit: Fit = Fit.CONTAIN,
    isDark: Boolean,
    onInit: (RiveAnimationView) -> Unit = {}
) {


    AndroidView(
        modifier = modifier,
        factory = { context ->
            val rive = RiveAnimationView(context)
            rive.also {
                it.setRiveResource(
                    resId = animation,
                    stateMachineName = stateMachineName,
                    alignment = alignment,
                    fit = fit,
                    autoplay = false,
                )
                it.setBooleanState(stateMachineName!!, "dark", isDark)
            }
        },
        update = { view ->
            {
                onInit(view)
            }
        }
    )
}