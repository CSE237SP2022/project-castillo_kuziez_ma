# project-castillo_kuziez_ma

WEBSTAC: Script-ran self-contained students and courses manager.

Called via the command line by using the run.sh script. this compiles the code and initializes the main method.
Data is stored between sessions by using txt files to read and write to for stable storage of variables.
An interface prompts users to select either adding a course, student or ending the session.

## Iteration 1:
- Created Student and Course classes
- Created methods necessary to create a student and their schedule
- Created methods necessary to create a course and track student enrollment
- Made JUnit tests for all course methods


## Iteration 2:
- Created Webstac class
- created interface to add a student or add a classs
- Created .txt files to store existing data
- Created methods necessary to use .txt files to set up a system
- Created script to run program for demo
- Cleaned up JUnit tests for all course methods and added student JUnit tests
- 

### Iteration 2 Questions
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
