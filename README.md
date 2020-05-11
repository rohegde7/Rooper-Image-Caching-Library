# Rooper-Image-Library

### Features:
* Downloading images with Caching mechanism

### Working:
* ___cachedImages___ (declared in the CachingUtil)is used to cache the images downloaded. This is cleared in 2 cases - 1. When the application is ___low on memory___ 2. OnDestroy of the last activity - which in this case is ___ImageLoadingActivity___

### Add jitpack in your root build.gradle at the end of repositories:

```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
### Add the dependency
```
dependencies {
	        implementation 'com.github.rohegde7:Rooper-Image-Library:1.0.1'
}
```

### Tech and Architecture:
* We have used __Kotlin__ as the primary programming language
* The feature is shipped as a __library__
* The app module takes advantage of __MVVM architecture__
* __Activity, ViewModel, Repository, LiveData, ObservableFields, DataBinding__ are the basic building blocks of this architecture
* All the UI related work like showing Toast, starting a new activity, showing the progress bar, etc is done in the Activity. 
* Business logic is written in the ViewModel. Also the observable fields are present in the ViewModel which are observed from the XML when we use data binding. 
* All API calls, DB calls, File operation and major calculation or operation happen from within the repository.
* It's a 1 way reference flow from Activity -> View Model -> Repository. For ViewModel/Repository to communicate back in the chain we use live data which is observed by the concerned entity.
* Constraint Layout has been used to design the UI

### Libraries used:
* Retrofit2
* RxJava/RxAndroid
* Gson
