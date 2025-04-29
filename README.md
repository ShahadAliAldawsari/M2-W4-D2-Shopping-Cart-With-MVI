<H1>Shopping Cart Application with MVI Architecture</H1>

<h2>Project Overview</h2>
This project is a Shopping Cart application built using Jetpack Compose and following the MVI (Model-View-Intent) architecture pattern. The goal of this application is to provide a simple, maintainable, and reactive UI for managing items in a shopping cart.<br>

<h3>Key Features:</h3>
- Add Items: The user can add items to the cart from a predefined list.<br>
- Update Quantity: The user can increase or decrease the quantity of items in the cart.<br>
- Remove Items: The user can remove items completely from the cart.<br>
- Cart Management: The app displays the cart items, including their names and quantities, with a loading indicator while fetching data. <br>

<h2>MVI Architecture</h2>
<h3>Model:</h3>
- The Model layer includes the data source (CartLocalDataSource), repository (CartRepositoryImpl), and use cases (GetCartItemsUseCase, AddItemToCartUseCase, RemoveItemFromCartUseCase, UpdateItemQuantityUseCase).<br>
- Use Cases are the core business logic, fetching data from repositories and providing it to the ViewModel.<br>
<h3>View:</h3>
- The View is implemented using Jetpack Compose. It includes the UI that is displayed to the user, such as a list of items in the cart, buttons to add or remove items, and a floating action button (FAB) to trigger actions.<br>
- The View observes the State from the ViewModel and updates the UI accordingly (e.g., error, or the list of items).<br>
<h3>Intent:</h3>
- Intent is the action that a user can perform, like AddItem, RemoveItem, or UpdateQuantity.<br>
- The ViewModel processes these intents and emits new states based on the action taken. The View sends intents to the ViewModel whenever an action occurs (like clicking a button to add an item).<br>

<h2>Running the Project</h2>
1- Clone the repository.<br>
2- Open the project in Android Studio.<br>
3- Make sure you have an emulator running or a physical device connected.<br>
4- Run the project.<br>
