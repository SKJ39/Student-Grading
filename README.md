# Student-Grading
A Java-based student grading system that reads student quiz scores from a file, calculates class-wide statistics (low, high, average), and serializes individual student grade reports to disk. The project features custom exception handling and a high-level API for data access.

## Component Architecture
The system is broken down into several key classes:

`Student`
This class is the core data model for a single student.
  - **Variables**: Stores a private `SID` (int) and a private `scores[]` (int array) for quiz scores
  - **Functionality**:
    - Includes constructors to initialize a student object
    - Provides getters and setters  for student data
    - Contains a `print()` method to display the student's ID and scores
    - Implements `Serialization`

`Statistics`
This class calculates and stores aggregate statistics for the quizzes across all students.
  - **Variables**: Stores private arrays for `lowscores`, `highscores`, and `avgscores`
  - **Functionality**:
    - Includes constructors to initialize the object
    - Contains methods to find the statistics from an array of `Student` objects
    - Provides getters to retrieve specific stats
    - Includes `print()` methods to display the calculated statistics
    - Implements `Serialization`

`StudentGrade`
This class acts as a container that bundles a `Student` object with the overall `Statistics` object. This combined object is used for serialization.
  - **Variables**: Holds a private `Student` instance (`stu`) and a private `Statistics` instance (`stat`)
  - **Functionality**:
    - Provides constructors, getters, and setters for its variables
    - `print()`: Calls the print methods of both `stu` and `stat`
    - Implements `Serialization`

`Util`
This is a utility class responsible for file I/O and serialization.

### File Reading:
- Reads an external file specified by the `fname` variable
- Reads the file line-by-line and uses `StringTokenizer` to parse the data
- Converts string tokens to integers and populates `Student` objects

### Error Handling:
- Includes an `openFile()` method that checks for `FileNotFoundException` and throws a custom `StudentGradingException` if the file is missing

### Serialization:
- `serializeGrades()`: Takes an array of `StudentGrade` objects and serializes each one to a separate `.dat` file using `ObjectOutputStream`. The filename is based on the student's ID
- `deserializeGrades()`: Reads a `.dat` file for a given `SID` using `ObjectInputStream` and prints the deserialized `StudentGrade` object's data

`StudentGradingException`
This is a custom exception class to handle errors specific to this application.
  - **Variables**: Stores a private `errorno` (int) and `errormsg` (String)
  - **Functionality**:
    - Provides constructors, getters, and setters
    - Includes a `fixFileNotFound()` method that suggests a default file path as a fix
    - `print()`: Prints the error details to the console
    - `printToFile()`: Appends the error details to a central `logs.txt` file

`StudentAPIImpl`
An abstract class that provides a simple, high-level API for accessing the system's functionality.
  - **Functionality**:
    - The constructor handles loading data, computing statistics, and serializing the files
    - `printStudentScore()`: Deserializes and prints the scores for a specific student ID
    - `printStatistics()`: Deserializes any student's file (since statistics are shared) and prints the statistics

### Drivers
Two driver classes are defined to run the application:
- `Driver1`: A simple driver that uses the `Util` class to read the file and print the properties of each `Student` object
- `Driver2`: A more comprehensive driver that:
  - Reads the file to build the `Student` array
  - Initializes the `Statistics` object and calculates the stats
  - Builds an array of `StudentGrade` objects  to prepare them for serialization

## Running the Program
Specific instructions to run the program are in `run_instructions.md`.

Visual representations of the program are in `Class_Diagram6-R1.jpg`, `Class_Diagram6-R2.jpg', and `Class_Diagram6-R1.jpg`.

More details about each classes are in their respective `.txt` files.

## Disclaimer:
This project was created for academic purposes.
