# A program written entirely in Kotlin using the principles of Clean Architecture according to the MVVM pattern.

This application is an information application. In this application you can find films and learn
more about this film. These applications are best suited to those who like to watch movies. In this
application, movies are divided into several categories for a convenient interface.Written in
Kotlin using the Jetpack Compose
framework.
Using the
server (https://www.themoviedb.org/login)

## Screenshots

[<img src="meta/screenshots/screenshots_splash_screen" width=160>](meta/screenshots/screenshots_splash_screen.jpg)
[<img src="meta/screenshots/screenshots_home_screen" width=160>](meta/screenshots/screenshots_home_screen.jpg)
[<img src="meta/screenshots/screenshots_search_screen" width=160>](meta/screenshots/screenshots_search_screen.jpg)
[<img src="meta/screenshots/screenshots_search_screen_second" width=160>](meta/screenshots/screenshots_search_screen_second.jpg)
[<img src="meta/screenshots/screenshots_watch_screen" width=160>](meta/screenshots/screenshots_watch_screen.jpg)
[<img src="meta/screenshots/screenshots_watch_screen_second" width=160>](meta/screenshots/screenshots_watch_screen_second.jpg)
[<img src="meta/screenshots/screenshots_detail_screen" width=160>](meta/screenshots/screenshots_detail_screen.jpg)
[<img src="meta/screenshots/screenshots_detail_screen_about_movie" width=160>](meta/screenshots/screenshots_detail_screen_about_movie.jpg)
[<img src="meta/screenshots/screenshots_detail_screen_cast_movie" width=160>](meta/screenshots/screenshots_detail_screen_cast_movie.jpg)
[<img src="meta/screenshots/screenshots_detail_screen_crews_movie" width=160>](meta/screenshots/screenshots_detail_screen_crews_movie.jpg)
[<img src="meta/screenshots/screenshots_detail_screen_review_movie" width=160>](meta/screenshots/screenshots_detail_screen_review_movie.jpg)
[<img src="meta/screenshots/screenshots_detail_screen_review_movie_filter" width=160>](meta/screenshots/screenshots_detail_screen_review_movie_filter.jpg)

## Libraries

* [Jetpack Compose](https://developer.android.com/jetpack/compose) Jetpack Compose is Android’s
  recommended modern toolkit for building native UI. It simplifies and accelerates UI development on
  Android. Quickly bring your app to life with less code, powerful tools, and intuitive Kotlin APIs.

* [Kotlin flows](https://developer.android.com/kotlin/flow) In coroutines, a flow is a type that can
  emit multiple values sequentially, as opposed to suspend functions that return only a single
  value. For example, you can use a flow to receive live updates from a database.

* [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines) Coroutines is a rich library for
  coroutines developed by JetBrains. It contains a number of high-level primitives with support for
  coroutines, which are discussed in this guide, including startup, asynchrony, and others.

* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) Data related to
  the user interface that is not destroyed when the application is rotated. Easily schedule
  asynchronous tasks for optimal execution.

* [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) An interface
  that automatically responds to lifecycle events.

* [Room](https://developer.android.com/jetpack/androidx/releases/room) The Room persistence library
  provides an abstraction layer over SQLite to allow for more robust database access while
  harnessing the full power of SQLite.


* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)Hilt is a
  dependency injection library for Android that reduces the boilerplate of doing manual dependency
  injection in your project. Doing manual dependency injection requires you to construct every class
  and its dependencies by hand, and to use containers to reuse and manage dependencies.

* [Retrofit](https://www.geeksforgeeks.org/retrofit-with-kotlin-coroutine-in-android)Retrofit is a
  type-safe http client which is used to retrieve, update and delete the data from web services.
  Nowadays retrofit library is popular among the developers to use the API key. The Kotlin team
  defines coroutines as “lightweight threads”. They are sort of tasks that the actual threads can
  execute. Coroutines were added to Kotlin in version 1.3 and are based on established concepts from
  other languages. Kotlin coroutines introduce a new style of concurrency that can be used on
  Android to simplify async code. In this article, we will learn about retrofit using Kotlin
  coroutine. So we will be using Retrofit for network requests. Retrofit is a very popular library
  used for working APIs and very commonly used as well. We will learn it by making a simple app
  using an API to get some data using Retrofit.

* [Pager](https://developer.android.com/jetpack/compose/layouts/pager)o flip through content in a
  left and right or up and down manner, you can use the HorizontalPager and VerticalPager
  composables, respectively. These composables have similar functions to ViewPager in the view
  system. By default, the HorizontalPager takes up the full width of the screen, VerticalPager takes
  up the full height, and the pagers only fling one page at a time. These defaults are all
  configurable.

* [Navigation](https://developer.android.com/jetpack/compose/navigation)The Navigation component
  provides support for Jetpack Compose applications. You can navigate between composables while
  taking advantage of the Navigation component’s infrastructure and features.