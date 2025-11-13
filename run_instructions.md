### Note: 
There are two copies of the FileIO classes, `Util` and `UtilCopy`. `UtilCopy` is a backup in case there's an irreversible error in `Util`.

## Driver1 (`Driverlab6r1`)
- Since most values are all hard coded, simply run the program with the given file name
- There are nine text files to test this program:
  + `scores1.txt` - empty file with header
  + `scores2.txt` - 1 record with header
  + `scores3.txt` - 15 records with header
  + `scores4.txt` - 40 records with header 
  + `scores5.txt` - 42 records with header
  + `scores6.txt` - 3 records with header \n

    ~ Missing `SID` and scores
  + `scores7.txt` - 3 records WITHOUT header
  + `scores8.txt` - 5 records WITHOUT header

    ~ A string/char in the record
  + `scores9.txt` - 15 records WITHOUT header

    ~ Missing `SID` and scores
    ~ Strings/chars are present

- Since I put the source files and the test files on different folders, you will need to:
1. Access the `testdata` folder
2. Copy the pathway of `scores1.txt` and paste it to line 20

  Util u = new Util("pathwayToFile");
4. To test other files, simply change the number on the file name
 "...`scores1.txt`" --> "...`scores2.txt`"

- You will need to change the path name in the exception handler class
1. Go to `StudentGradingException`
2. Copy the pathway to `logs.txt` file to line 16
3. Remove the tail `logs.txt`

- If `FileNotFoundException` error needed to be test, comment line 20 and uncomment line 19
 Or change the filename in line 20 to an unknown filename

## Driver2 (`Driverlab6r2`)
- Hardcoded values and no `Scanner` are implemented - Simply run the program and change file names
- Similar in **Driver1**, copy the pathnames of the scores files and paste them to line 20
- As for the path to serialize files, highlights the `serialize` folder, copy the pathname (to that folder, not the files inside), and paste them to line 33.

## Driver3 (`Driverlab6r3`)
- Mostly hardcoded values, change the filenames inside `StudentAPIImpl` and run the program in `Driverlab6r3`
- To test different text files
 Go to `StudentAPIImpl`
 Copy pathway to test files and paste to line 24
- To change serialization location
 Go to `StudentAPIImpl`
 Copy pathway to the folder `serialize` and paste to line 35

### Note: 
I did not provide design notes for `StudentAPI`, `SAPI`, and **Driver3** since they simply initializes the interface, extends the parent abstract class to instantiates `StudentAPIImpl`, or call the methods inside `SAPI`.
