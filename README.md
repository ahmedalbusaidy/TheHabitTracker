# Habit Tracker

## An Easy Way To Track Your Habits

This project is part of a bigger project, e-Journal, in which I will continue to build on it gradually. A look into
The future, the e-Journal will be focused on maintaining a healthy and productive lifestyle, with the use of many
features like: the habit tracker, mood tracker, sleep log, gratitude log, brain dump and much more! At this step of the
project, I introduce the **Habit Tracker**, an easy way to track new habits where you will be able to:
- Add a new habit with your commitment target (# of days per month)
- Delete habits that you are no longer in need
- Record/Modify your progress easily
- Display summary log of your habits and progress for each
- Reset log to start all over again

If you want an easy way to stick to your habits, and track you progress then this project is designed for you. It is 
designed for anyone who is interested in building habits and maintaining a healthy and productive lifestyle. This is 
a simple and effective way to achieve your goals!

***

### Inspiration

I centered my 2021 resolution around the notion of ***breaking bad habits, and building new ones.*** The book *Atomic
Habits: Tiny Changes, Remarkable Results* by *James Clear* has inspired me to start building new habits. Around the same
time, I started *Bullet Journaling (BuJo)*, so I integrated James ideas into journaling. Things went well, I was
committed for more than a week, but soon I started to procrastinate and ignore recording in my journal. This was because
the process was time-consuming and not efficient, which lead to the idea of this project!

***

### User Stories

- As a user, I want to be able to add a new habit with commitment target (e.g. number of days/month) to a list of habits
- As a user, I want to be able to delete a habit from my list of habits
- As a user, I want to be able to record my progress of all habits easily
- As a user, I want to be able to record my progress to an individual habit
- As a user, I want to be able to modify my previously recorded progress of any habits
- As a user, I want to be able to see a list of all my habits with details including: commitment target, number of
committed days, and maximum commitments in a row
- As a user, I want to be able to see a record of an individual habit
- As a user, I want to be able to reset all of my habit's progresses to zero 
- As a user, I want to be able to reset a single habit's progress to zero
- As a user, I want to be able to see a confirmation dialog before deleting a habit or resetting its progress
(for example: "Are you sure you want to permanently delete/reset this habit?")
- As a user, I want to be able to save my habit list to file
- As a user, I want to be able to be able to load my habit list from file
- As a user, when I start the application, I want to be given the option to load my habit list from file

### Phase 4: Task 2
- Type hierarchy included in my code other than the one that uses the Saveable interface introduced in Phase 2:
- HabitsTable extends AbstractTableModel
- DeleteProgressWindow, RecordProgressWindow, PrintSummaryWindow, ResetProgressWindow extends HabitsTable

### Phase 4: Task 3
- In the root folder I have added a UML diagram class diagram that represents the final version of this code. From the
first look it seems not easy to read without tracing the arrows from class to class. Most of the arrows are
associations. I made a good job in extracting common code into a superclass and connects some subclasses by
inheritance relationship. However, I believe that the code could be better improved by reducing associations and 
making the code more robust. I can get rid of the HabitTrackerCUI, which is the old console user interface. Also, many
of the classes can be combined and reduce the number of windows, for instance: the save and load buttons could be
implemented as a drop-down menu, and the other functions could all be in one window with few buttons to perform the
actions (adding/deleting habit, recording/modifying/resetting progress, and printing summary).

### References
1- Teller Application

URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git


2- JsonSerializationDemo

URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

