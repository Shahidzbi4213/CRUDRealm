
# **CRUD Operations with Realm DB**

A simple Android project demonstrating how to perform **CRUD (Create, Read, Update, Delete)** operations using **Realm DB** and **Jetpack Compose** for building the UI. This project helps you understand the basics of **Realm DB** for local data storage and showcases the use of **Jetpack Compose** to build modern Android UIs.

---

## **Features**

- **Create**: Insert new records into the Realm database.
- **Read**: Retrieve and display data from the database.
- **Update**: Modify existing records in the database.
- **Delete**: Remove data entries from the database.
- **Offline Support**: Realm DB provides seamless offline support with no need for an internet connection.

---

## **Technologies Used**

- **Kotlin**: Modern programming language for Android development.
- **Realm DB**: A fast and efficient local database for mobile apps.
- **Jetpack Compose**: A modern UI toolkit for building native UIs in Android.
- **Jetpack Libraries**: Used for architecture, UI components, and lifecycle management.

---

## **Installation**

 Clone the repository:
   ```bash
   git clone https://github.com/Shahidzbi4213/CRUDRealm.git
   ```


---

## **Usage**

### **1. Add Realm to Your Project**

In your **`build.gradle`** file (project-level), apply the Realm plugin:

```gradle
plugins {
    id 'io.realm.kotlin' version '1.6.1' apply false
}
```

In your **`build.gradle`** file (app-level), add the following dependencies:

```gradle
dependencies {
    implementation 'io.realm.kotlin:library-base:1.6.1' // Make sure to use the latest version
}
```

### **2. Defining Realm Models**

Define your Realm object models to store data in the database. Here's an example of a simple model called `Note`:

```kotlin
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import io.realm.kotlin.types.annotations.Required
import org.bson.types.ObjectId

open class Note : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var title: String = ""
    var description: String = ""
}
```

### **3. Performing CRUD Operations**

#### **Create:**

```kotlin
fun createNote(realm: Realm) {
    val note = Note().apply {
        title = "Sample Title"
        description = "Sample description for the note."
    }
    realm.write {
        copyToRealm(note)
    }
}
```

#### **Read:**

```kotlin
fun getNotes(realm: Realm): List<Note> {
    return realm.query(Note::class).find()
}
```

#### **Update:**

```kotlin
fun updateNote(realm: Realm, noteId: ObjectId, newTitle: String) {
    val note = realm.query(Note::class).find().firstOrNull { it._id == noteId }
    if (note != null) {
        realm.write {
            note.title = newTitle
        }
    }
}
```

#### **Delete:**

```kotlin
fun deleteNote(realm: Realm, noteId: ObjectId) {
    val note = realm.query(Note::class).find().firstOrNull { it._id == noteId }
    if (note != null) {
        realm.write {
            delete(note)
        }
    }
}
```

---

## **UI with Jetpack Compose**

This project utilizes **Jetpack Compose** for building the user interface. Compose offers a modern, declarative way to create UIs in Android applications. Here's a simple example of how to display the list of notes using **Jetpack Compose**:

```kotlin
@Composable
fun NotesScreen(notes: List<Note>) {
    LazyColumn {
        items(notes) { note ->
            Text(text = note.title)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = note.description)
        }
    }
}
```

---

## **Contributing**

We welcome contributions! Feel free to fork the repo, make your changes, and submit a pull request.

To contribute:

1. Fork the repository.
2. Create a new branch for your changes.
3. Make your changes and commit them.
4. Push your changes to your fork.
5. Create a pull request describing the changes you made.

---

## **Contact**

For any questions or feedback, feel free to open an issue or contact me directly:

- **Email**: shahid.iqbal4213@gmail.com
- **GitHub**: [GitHub Profile](https://github.com/shahidzbi4213)
