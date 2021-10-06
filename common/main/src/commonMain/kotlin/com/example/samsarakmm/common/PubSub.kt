package com.example.samsarakmm.common

interface Subscriber<Event> {
    fun onUpdate(event: Event)
}

abstract class Publisher<Event> {
    private val subscribers: MutableList<Subscriber<Event>> = mutableListOf()

    fun notify(event: Event) {
        subscribers.forEach { it.onUpdate(event) }
    }

    fun subscribe(subscriber: Subscriber<Event>) {
        subscribers.add(subscriber)
    }

    fun clearSubscriptions() {
        subscribers.clear()
    }
}