# KotlinMoviesApp

KotlinMoviesApp is a simple Android application built with Kotlin, showcasing a list of movies with features like movie details, favoriting, and syncing with the internet. The app uses modern Android development practices and follows Clean Architecture principles to ensure scalability and maintainability.

## Features

- **Movie Listing**: The home screen displays a list of movies, which can be filtered by different categories.
- **Movie Details**: Detailed information for each movie is provided on a separate screen.
- **Favorite Movies**: Users can favorite movies, and these are saved locally using Room Database.
- **Offline Syncing**: An internet checker is used to sync the movie data when the app detects an active connection.
- **Optional Sync Feature**: Users can choose sync intervals of never, every 8 hours, or daily, allowing for customizable data management.
  
## Screens

1. **Home Screen**: Displays a list of movies that changes according to the selected category.
2. **Movie Details Screen**: Provides detailed information about a selected movie.
3. **Favorites**: A favoriting feature that allows users to save movies locally.

## Tech Stack

### 1. **Kotlin**: 
   - The entire app is built using Kotlin.

### 2. **Jetpack Compose**: 
   - For building the UI in a declarative manner.

### 3. **Dagger Hilt**: 
   - Used for Dependency Injection to manage app dependencies.

### 4. **Room Database**: 
   - Used for local data storage, allowing favorite movies to be saved offline.

### 5. **Retrofit & Gson**: 
   - Retrofit is used for network requests, and Gson is used for parsing JSON responses.

### 6. **Coroutines**: 
   - For handling asynchronous operations, such as API requests.

### 7. **Coil**: 
   - Image loading library for loading movie posters efficiently.

### 8. **LiveData & ViewModel**: 
   - LiveData is used to observe data changes, while ViewModel handles the UI-related data in a lifecycle-conscious way.

### 9. **Shimmer Effect**: 
   - Used to show loading placeholders while the movie data is being fetched from the API.

### 10. **Splash API**: 
   - To provide a smooth user experience with a splash screen when the app starts.

### 11. **Internet Checker**: 
   - Ensures that the app can check for connectivity and syncs movies when a connection is available.

### 12. **Chucker Interceptor**: 
   - Used for monitoring network requests, providing insights into API interactions.

### 13. **Clean Architecture**: 
   - The project follows Clean Architecture principles, separating the app into layers (presentation, domain, and data) to ensure scalability and maintainability.

## Screenshots
<p float="left">
  <img src="https://github.com/user-attachments/assets/cffa6f3a-9725-4be1-9856-015757e0b0c9" width="200" />
  <img src="https://github.com/user-attachments/assets/0f6587e9-632e-4a95-88de-fb9e55643843" width="200" />
</p>
<p float="left">
  <img src="https://github.com/user-attachments/assets/d508ebb2-a6b4-484b-b6fe-b2cb1161109e" width="200" />
  <img src="https://github.com/user-attachments/assets/853820f7-d7b7-4587-a4c4-829b590cf8e0" width="200" />
</p>
<p float="left">
  <img src="https://github.com/user-attachments/assets/c63942c4-b968-4d58-9d20-3553536bf169" width="200" />
  <img src="https://github.com/user-attachments/assets/f021476c-8d2d-4825-801b-9cef5d293206" width="200" />
</p>
<p float="left">
  <img src="https://github.com/user-attachments/assets/e5d7dc98-4e79-4282-9494-a0741d28058d" width="200" />
  <img src="https://github.com/user-attachments/assets/60b6d788-ac20-45e3-9f36-49405505b756" width="200" />
</p>
<p float="left">
  <img src="https://github.com/user-attachments/assets/39521869-1884-471f-ad31-ca4902f00552" width="200" />
</p>
