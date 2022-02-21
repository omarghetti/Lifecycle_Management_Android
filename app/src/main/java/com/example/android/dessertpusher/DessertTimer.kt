package com.example.android.dessertpusher

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.*
import timber.log.Timber

/**
 * This is a class representing a timer that you can start or stop. The secondsCount outputs a count of
 * how many seconds since it started, every one second.
 *
 * -----
 *
 * Handler and Runnable are beyond the scope of this lesson. This is in part because they deal with
 * threading, which is a complex topic that will be covered in a later lesson.
 *
 * If you want to learn more now, you can take a look on the Android Developer documentation on
 * threading:
 *
 * https://developer.android.com/guide/components/processes-and-threads
 *
 */
class DessertTimer(): DefaultLifecycleObserver {

    // The number of seconds counted since the timer started
    private var secondsCount = 0

    /**
     * [Handler] is a class meant to process a queue of messages (known as [android.os.Message]s)
     * or actions (known as [Runnable]s)
     */
    private lateinit var runnable: Runnable

    override fun onStart(owner: LifecycleOwner) {
        runnable = Runnable {
            secondsCount++
            Timber.i("Timer is at : $secondsCount")
            // postDelayed re-adds the action to the queue of actions the Handler is cycling
            // through. The delayMillis param tells the handler to run the runnable in
            // 1 second (1000ms)
            Handler(Looper.getMainLooper()).postDelayed(runnable, 1000)
        }

        // This is what initially starts the timer
        Handler(Looper.getMainLooper()).postDelayed(runnable, 1000)
    }

    override fun onStop(owner: LifecycleOwner) {
        Handler(Looper.getMainLooper()).removeCallbacks(runnable)
    }
}