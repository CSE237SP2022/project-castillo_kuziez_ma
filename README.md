# project-castillo_kuziez_ma

WEBSTAC: Script-ran self-contained students and courses manager

## Iteration 1:
- Created Student and Course classes
- Created methods necessary to create a student and their schedule
- Created methods necessary to create a course and track student enrollment
- Made JUnit tests for all course methods


## Iteration 2:
- Created Webstac class
- Created .txt files to store existing data
- Created methods necessary to use .txt files to set up a system
- Created script to run program for demo
- Cleaned up JUnit tests for all course methods and added student JUnit tests

### Iteration 2 Questions
Currently, there is no way to interact with the system other than manually editing the .txt files.  
Iteration 3 will include a java interface to be able to make changes to the system without needing to manually edit anything.

The program also does not update students' course lists within the system, which should stay updated after every change, including after initialization.

Lastly, the program is run from the project directory with

```console
./run.sh
```

If permission denied, run

```console
chmod +x run.sh
```
