# Shutterstock Search App
Application is developed for listing images from Shutterstock with a smooth endless scrolling experience. Users can search and preview images throught the application via Shutterstock's content. Application developed for **Android platform** with **Kotlin** programming language on [MVVM Architecture](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel). 

Before running this application, developer must declare 2 variables in order to fetch images from [Shutterstock Developers](https://developers.shutterstock.com/)
 
 **gradle.properties** 
* API_CONSUMER_KEY="consumer_key"
* API_CONSUMER_SECRET="consumer_secret"

### About its architecture
The structure of the project is based on the [Google Android Architecture](https://developer.android.com/topic/libraries/architecture/index.html)

* The project architecture is Model-View-ViewModel (MVVM) pattern.
* Views will inform the ViewModel of user interactions.
* ViewModels have the responsibility of the communication with the views through reactive events. They also handle the logic with the data needed for the views.
* The project uses native Android ViewModel, it allows data to survive configuration changes such as screen rotations.
* LiveData is used for handling communication between activities/fragments and ViewModels through events. 
* Dependency Injection (Dagger2) is used on the project for using singleton instances to prevent memory allocation for unnecessary classes/objects, to make network requests to Shutterstock API, make communication easy between classes.

### Language and principal tools
* __Kotlin:__  App language  
https://kotlinlang.org/
* __Android Jetpack:__  Collection of Android software components to make development easiersuch as ViewModel, LiveData, AndroidX design components etc.
https://developer.android.com/jetpack/androidx/
* __ConstraintLayout:__  To arrange grid images by their width and height ratios 
https://developer.android.com/jetpack/androidx/
* __RxJava2:__  Reactive programming library  
https://github.com/ReactiveX/RxJava
* __Dagger2:__  Framework for dependencies injection  
https://github.com/google/dagger
* __Retrofit:__  REST API communication
http://square.github.io/retrofit/
* __OkHttp:__  HTTP+HTTP/2 client
http://square.github.io/retrofit/
 * __Picasso:__  Image Loading  
http://square.github.io/picasso/

I have chosen MVVM Architecture for;
- **Better Separation of Concerns**
- The MVVM pattern presents a better separation of concerns by adding view models to the mix. The view model translates the data of the model layer into something the view layer can use. The fragment or activity is no longer responsible for this task.
- **Improved Testability**
- Activities/Fragments are notoriously hard to test because of their relation to the view layer. By migrating data manipulation to the view model, testing becomes much easier. Testing view models is easy. Because a view model doesn’t have a reference to the object it is owned by, it easy to write unit tests for a view model.
- **Transparent Communication**
The responsibilities of the fragment are reduced to controlling the interaction between the view layer and the model layer, glueing both layers together. The view model provides a transparant interface to the view controller, which it uses to populate the view layer and interact with the model layer.

### Trade-offs
The project has [EndlessRecyclerViewScrollListener](https://github.com/Swisyn/ShutterstockImageSearch-MVVM/blob/master/app/src/main/java/com/cuneytayyildiz/shutterstockassignment/utils/InfiniteScrollListener.kt) class for handling endless scroll events. I might use the [Paging library](https://developer.android.com/topic/libraries/architecture/paging/) to consume data from the rest api. I didn't want to use it because of it's complexity and not to create a lot of boilerplate code just to handle paging.
