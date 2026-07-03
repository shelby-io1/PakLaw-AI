# PakLaw AI

PakLaw AI is an Android application designed to serve as an AI-powered legal literacy, library, study, and emergency aid helper for Pakistan.

## Features

- **AI Legal Assistant**: Engage in conversations with an AI assistant powered by Google Gemini to get information about legal topics.
- **User Authentication**: Secure sign-up and log-in functionality for personalized experience.
- **Chat History**: Automatically saves your conversations with the AI to Firestore for persistence.
- **Library & Study**: Access to legal resources and study materials.
- **Local Persistence**: Uses Room database for local data management.

## Project Structure

- `app/src/main/java/com/example/ui/`: Contains the UI components built with Jetpack Compose, organized by screens (`LoginScreen`, `SignUpScreen`, `MainAppScreen`) and the `PakLawViewModel` for business logic.
- `app/src/main/java/com/example/data/`: Handles the data layer, including:
  - `Database.kt`: Local Room database implementation.
  - `FirestoreManager.kt`: Interaction with Firebase Firestore to store chat history and user profiles.
  - `GeminiClient.kt`: Integration with the Google Gemini API.
  - `LibraryData.kt`: Data models for legal resources.

## How it Works

1. **Authentication**: Users create an account or log in via `SignUpScreen` or `LoginScreen`.
2. **Chat**: Once authenticated, the user can chat with the AI assistant on the `MainAppScreen`.
3. **Data Storage**: When a user chats, the application uses `FirestoreManager` to save the conversation to Firebase Firestore.
4. **Local Data**: Essential user data and cached information are stored locally using the Room database (`Database.kt`).
5. **AI Interaction**: Conversations are sent to and from the AI model via `GeminiClient` using the Google Gemini API.
