# MessageApp

### Implementation Decisions

* Architecture: A Clean Architecture approach was chosen, layered over the MVVM presentation pattern. This ensures maximum separation of concerns, high testability, and maintainability.

* Data Flow: Kotlin Flow was utilized throughout the entire stack (from Room DAO to ViewModel StateFlow) to establish a reactive flow, ensuring the UI always reflects the database state without explicit refresh calls.

* Persistent Storage: Room was selected to serve as the single source of truth, prioritizing offline first support and reliable local data persistence.

* UI: Jetpack Compose was used for the entire UI, allowing for a declarative and highly efficient composition of the chat elements.


### Assumptions

* The app will always have two fixed users (SARAH and JAMES).

* Messages will be generated locally; no network or multi-device sync is required.

* All messages are text-only, no images or attachments.

* Timestamps are accurate and consistent, so spacing and headers logic works correctly.

* Messages are loaded in chronological order (oldest → newest).

* The last message should always scroll into view when opening the chat.

* Small spacing applies if messages are sent by the same user within 20 seconds.

### App Limitations and Future Work

* Missing Features: Group chats, image/media support, message reactions, and read receipts are not implemented.

* Required Optimizations: Implement Paging to load messages in chunks to prevent OutOfMemoryError on large datasets.

* Polish: Refine the keyboard interaction to fully eliminate potential screen jumping.

* Core Feature: Integrate Network Synchronization to enable real-time messaging.
