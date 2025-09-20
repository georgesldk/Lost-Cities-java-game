# Anazitontas ta Xamena Minoika Anaktera - Design Document

"Anazitontas ta Xamena Minoika Anaktera" is a Java-based board game implemented using Object-Oriented Programming (OOP) principles and the Model-View-Controller (MVC) design pattern. This document outlines the system's architecture, how to compile and run the project, key development decisions, challenges faced, and the testing strategy employed.

## System Architecture (MVC Pattern)

The core of the system is structured around the MVC pattern, with distinct packages for `model`, `view`, and `controller`.

### Model Package

Contains the core game logic and data. These classes hold no GUI-specific elements.

### View Package

Responsible for the graphical user interface (GUI) and visual representation of the game state. These classes extend Swing components.

### Controller Package

Acts as an intermediary, orchestrating interactions between the Model and View. It handles game logic, player turns, and updates the GUI based on model changes.

## How to Compile and Run

This project requires a Java Development Kit (JDK) 11 or newer.

### Using an IDE (e.g., IntelliJ IDEA, Eclipse, NetBeans)
1.  **Import the Project**: Open your IDE and import the project as an existing Java project.
2.  **Ensure Dependencies**: All necessary libraries (import JUnit Library if necessary).
3.  **Build/Compile**: Your IDE should automatically compile the project. If not, trigger a "Build Project" command.
4.  **Run**: Locate the main.java class and run it directly from the IDE.

## Development Decisions and Challenges

### Design Philosophy (MVC)
The decision to strictly adhere to the Model-View-Controller (MVC) pattern was paramount. This allowed for a clean separation between the game's core logic (Model), its visual representation (View), and the interaction handling (Controller).

*   **Benefits**: Enhanced maintainability, easier debugging (components can be tested independently), and improved extensibility for future features or alternative UIs. For example, changing a visual element in the View does not require modifying the game rules in the Model.
*   **Implementation**: The Model classes contain no Swing components, focusing purely on game state and rules. The View classes are responsible for displaying this state and listening for user input. The Controller translates user input into Model actions and updates the View based on Model changes.


### Challenges Encountered
*   **Complex Card Logic**: Implementing the ascending/equal card rule, Ariadne's Thread exceptions, and Minotaur attacks, while ensuring correct state management across turns, required careful design and thorough testing.
*   **Multi-threaded Timer (Bonus 2)**: Integrating the turn timer without blocking the main event dispatch thread (EDT) involved understanding `SwingUtilities.invokeLater` and concurrent programming concepts in Java. Running the timer in its own thread and safely updating the GUI was a key learning curve.
*   **Game State Serialization (Bonus 3)**: Implementing save/load functionality required careful consideration of which objects needed to be serialized and how to reconstruct the entire game state (including player hands, board layout, pawn positions, and collected items) upon loading. This involved making many game-related classes `Serializable`.

## JUnit Tests

Comprehensive JUnit tests were developed to ensure the correctness and robustness of the core game logic, particularly for the Model components.

*   **Benefits**: JUnit tests were crucial during development for identifying and fixing bugs early, ensuring that changes to one part of the code did not inadvertently break other functionalities.


## Missing features
Graphical representation of pawns, csv file implementation.

## Conclusion

"Anazitontas ta Xamena Minoika Anaktera" is a functional board game demonstrating a solid understanding of object-oriented programming principles, design patterns, and Java GUI development.

## Developers

Georgios Marios Souladakis
University of Crete, Department of Computer Science
HY252 – Object-Oriented Programming (Winter Semester 2024-2025)