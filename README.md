# TIRA-2021

This repository contains the exercises and course projects for the 2021 implementation of the Data Structures and Algorithms (TIRA, Tietorakenteet ja algoritmit in Finnish) course.

You as a student should fork and clone this repository. You will then work on that forked private repository and use it do deliver your course work to the course teachers. How to start with all this is explained in this readme, and also in the exercise `00-init` readme.

## GitLab 

You will need **an account** at [GitLab](https://gitlab.com). All exercises and course projects are delivered through GitLab. You must fork this repository and deliver all your course work through that forked private repository.

More information about how to use git and GitLab in the course is provided elsewhere. If this file is located now on your own PC, you should have gotten this project by a) forking the course repository to a private repository of your own, and then b) cloning it to your PC. Instructions on how to do that is provided below.

You also **must fill in the Moodle form** where you specify the requested information about you and your repository. That data is used by our automatic scripts to fetch your repository for testing and grading at the end of the course. Without this information, we won't test and grade your work. The number of students on this course is so high that automation is a necessity, and without the data the scripts cannot handle your work.

## Tools

The tools that are used in this course and you need to install, are:

* [git](https://git-scm.com) for version control and delivering work to teachers.
* [JDK version 17](https://jdk.java.net/17/) -- install the version 17 if you have an older JDK. JDK 16 is also OK.
* [Maven](http://maven.apache.org) for building and running tests. Install the latest version.
* IDE for coding and debugging -- teachers use [Visual Studio Code](https://code.visualstudio.com), and recommend that for you too.
* [Java extensions](https://code.visualstudio.com/docs/java/java-tutorial) to the VS Code. Useful extensions are Extension Pack for Java, Language support for Java by Red Hat and Maven for Java extensions for VS Code.

You can check the versions of tools you may have installed or now install, using the terminal. Here is my installation:

```console
> git --version
git version 2.30.1 (Apple Git-130)

> mvn --version
Apache Maven 3.8.3 (ff8e977a158738155dc465c6a97ffaf31982d739)
Maven home: /usr/local/Cellar/maven/3.8.3/libexec
Java version: 17, vendor: Homebrew, runtime: /usr/local/Cellar/openjdk/17/libexec/openjdk.jdk/Contents/Home
Default locale: fi_FI, platform encoding: UTF-8
OS name: "mac os x", version: "11.6", arch: "x86_64", family: "mac"

> java --version
openjdk 17 2021-09-14
OpenJDK Runtime Environment Homebrew (build 17+0)
OpenJDK 64-Bit Server VM Homebrew (build 17+0, mixed mode, sharing)
```
VS Code version can be checked from the VSC menus. Installed VSC extensions can be seen from the VSC Extensions tool.

**Make sure** JDK `bin` and Maven `bin` directories are on your **PATH** environment variable, and that **JAVA_HOME** environment variable points to the JDK home directory!

Each exercise contains unit tests. For unit testing, JUnit Jupiter version 5.7 or newer is used. These component dependencies (JUnit Jupiter) are installed by Maven / Visual Studio Code *automatically*. You do not have to try to install these yourself.

Optionally, it is also recommended that you install a proper terminal app, especially on Windows. Though you can work with Visual Studio Code and the terminal in it, having a good terminal for command line based work is better than working with the Windows Command Prompt. 

One option is Microsoft's [Windows Terminal](https://github.com/microsoft/terminal). [Other terminal apps](https://dev.to/adnanmostafa/the-best-free-standalone-terminals-for-windows-2019-kmj) than the basic Command Prompt have been designed for programmers and are usually much better in e.g. supporting Unicode text. For macOS users, [iTerm2](https://iterm2.com) is a good option to native macOS Terminal app.

After installing the tools, continue reading.

## How to set up your workspace

The instructions assume you are reading this from the GitLab web page of the course repository.

First step is to fork the course project in GitLab and clone it to your own PC, assuming you are reading this from the web pages of the course repository. The course Moodle workspace has instructions for you how to do all this, so **check out that first**.

This step is done only *once* assuming you will work from one PC throughout the course. After executing the steps below, you have the project on your PC. Later you will add and change files in the local project, commit the changes and when needed, update the remote repository from your local repository.

1. Login to your GitLab account and access this TIRA course project repository. URL is provided in the course Moodle workspace. Though if you are reading this, you probably have that URL already :)
1. **Fork** the project from the repository web page. You will get your own forked copy of the repository in GitLab.
1. From the forked project settings, **make** your project **private**.
1. **Add the course teachers** to your private repository with **Developer** access level. Teachers need access to help you solve issues in the exercises and grade your work in the course. No one else than you and the teachers should have access to the forked private repository of yours.
1. **Open the command prompt** (terminal window) on your PC.
1. **Navigate** to a directory you want to place your *local cloned* repository. Select a directory path with *no spaces nor special characters* in the directory names. Some tools do not like these at all. All work done in the course will be located in this directory.
1. **Clone** your private remote repository to your PC: `git clone <url-to-your-remote-private-forked-repository>`. You need to provide the GitLab credentials (username and password) at this point. Enter the private repository URL after `clone` without the "< >" characters -- just the URL to the repository.
1. You should have now a **local repository**, a "copy" of your remote private repository, on your PC. 

In the terminal, list the contents of the directory (`dir` on Windows, `ls` on Unixes). Browse the subdirectories and files to study what is included. 

> Do *not* accidentally delete or move anything here! If you mess this local project and the work you have done has not yet been committed to local repository or pushed to the remote private repository of yours, the work is *lost*.

Each exercise and the course projects are now on your local PC. Study them and start working with them following the timing below, and further instructions from the teachers.

> If you must or want to work on several PCs, please do ask further instructions from the teachers. Unless you are already experienced with using git.

## Timing and deadlines

The table below describes the timing of the course in Fall 2021. 

> Note that if you want and are able to, **you may proceed faster** than the schedule indicates. It is also a good idea to start looking at the **course projects early**. For example, the BooksAndWords project requires you to implement reading text files. You should already know how this is done, based on what you have learned in previous courses. So you can start implementing that early, and later focus on choosing a suitable data structure and algorithms for your implementation, when you have learned more on this course.

Each subdirectory contains detailed instructions in `README.md` files for that specific exercise or course project. As you go along to those exercises, view the readme files in each subdirectory for exercise / course project specific instructions.

When the course deadline comes, teachers will use automatic scripts that will clone all student repositories for evaluation and grading. The version on that date & time will be evaluated.  Late deliveries are *not* taken into account.

Note that those who apply for the study program **Candidate Project** course starting in January 2022 will have a **deadline earlier in December**. Exact date is communicated in the course Moodle workspace.

**Lectures** marked with + are short, online **live Q&A and demonstration sessions**, not actual lectures.

The actual lectures are **recordings** viewable at suitable times considering your own schedules. Note that you must watch the corresponding lecture videos and view relevant demonstrations *before* the exercises. So reserve enought time to view the lectures each week before the exercise. Lecture topics and exercise numbers match so you can use these to make sure you view lectures on time.

Note that if you decide to do the **Mazes course project** and not the BooksAndWords, you may want to start watching the Graph lectures earlier to get a good start to your course project. This way you have more time to focus on the Maze project than watching the lectures following the default time table.

**Exercises** are scheduled so that you *start* working on them the week indicated. You may work on the exercise later; no other deadline for exercises than the course deadline at the end. But do not push the work too far ahead, then you have many unfinished exercises to work with, overloading you. It is a good idea to start working on the exercises *before* the actual exercise sessions. Otherwise, in the session you do not have anything (questions, problems) teachers can help you with. Using the exercise sessions to read instructions is not productive use of time.


| Week | Lecture #    | View   | Topic                | Exercise                        | Lecture Demo             |
|------|--------------|--------|----------------------|---------------------------------|--------------------------|
|  44  | +Lecture 1   | 01 Nov | 00 Course intro      | [00-init](00-init)              | SSN                      |
|      |  Lecture 2   | 03 Nov | 01 Topic intro       | [02-arrays](02-arrays)          | BooksAndWords - BadBook  |
|      | +Lecture 3   | 05 Nov | 02 Time complexity   | [02-mode](02-mode)              |                          |
|  45  | +Lecture 4   | 08 Nov | 03 Analysis          | [03-permutation](03-permutation)| Fibonacci - Maze         |
|      |  Lecture 5   | 09 Nov | 03 Analysis          | [04-stack](04-stack)            |                          |
|      | +Lecture 6   | 10 Nov | 04-1 Stack           |                                 |  Persistence & Outsource |
|      |  Lecture 7   | 12 Nov | 04-2 Queue + list    |                                 |                          |
|  46  | +Lecture 8   | 15 Nov | 05 Sorting algorithms| [04-queue](04-queue)            |             Arrays&Lists |
|      |  Lecture 9   | 16 Nov | 05 Sorting algorithms| [04-linkedlist](04-linkedlist)  |            SortSpectacle |
|      | +Lecture 10  | 17 Nov | 06 Hash tables       |                                 |LinkedList  SwiftPipeline |
|      |  Lecture 11  | 19 Nov | 06 Hash              |                                 |                          |
|  47  | +Lecture 12  | 22 Nov | 07 Binary search tree| [05-binsearch](05-binsearch)    |          BSTree & Events |
|      |  Lecture 13  | 23 Nov | 07 Binary search tree| [05-invoices](05-invoices)      |                   Graphs |
|      | +Lecture 14  | 26 Nov | 08 Graphs part 1     |                                 |   Graphs, InfiniteMirror |
|      |  Lecture 15  | 30 Nov | 08 Graphs part 2     |                                 |                   Graphs |
|  48  | +Lecture 16  | xx Nov | 09 Design & Dynamic  | [67-phonebook](67-phonebook)    |         SwiftKickforward |
|      |  Lecture 17  | xx Nov |                      | Course project                  |                          |
|      | +Lecture 18  | xx Dec | Q&A                  |                                 |            SwiftPipeline |
|      |  Lecture 19  | xx Dec |                      |                                 |                          |
|  49  | +Lecture 20  | xx Dec | Q&A                  | Course project                  | SwiftKickForwardParallel |
|  50  |              |        | Exam                 | Course project                  |                          |
|2/2022|**2022-01-14**|        |**23:59 EET**         |**Deadline for**                 | **all course work**      |

## Grading

The course is graded with the following rules:

1. All exercises must be acceptable (1 points), 5 pts is awarded from a good result.
1. Select *either* BooksAndWords *or* Maze to implement as a course project. You are not required to do both.
1. Exam must be passed with minimum of 10 points from the total of 20 pts.
1. Project is graded either 0 (failed) or 30 pts, but we may drop points down from 30 if implementation has weaknesses.
1. *Optionally*, you may implement *also* the other course project to raise grade by one (1) or implement BooksAndWords optional things.
1. Note also that in the exercise `67-phonebook` there is also a chance to get extra points. More details in that exercise readme file.

Remember that functionally correct course project *is not enough*. The course projects must be implemented with *time complexity* in mind. If your project is not *fast* enough with *large* data sets, it may be graded failed even though it may be functionally correct, i.e. produces the correct result. This is explained in detail in the course introductory lectures.

Note that in the table below, in the *Min points* column it is assumed that the course project has no weaknesses. If the project *does* have weaknesses, then you must get better points from exercises and/or the exam to pass the course.

| Course task   | Min points      | Max Points      |
|---------------|-----------------|-----------------|
| Exercises     | 10 * 1 == 10pts | 10 * 5 == 50pts |
| Course project| 30 pts          | 30pts +bonuses  |
| Exam          | 10 pts          | 20 pts          |
| **TOTAL**     | **50 pts**      | **100 pts**     |

Grade is determined from the points following the table below:

| Points | < 50  | 50-59 | 60-69 | 70-79 | 80-89 | >= 90  |
|--------|-------|-------|-------|-------|-------|--------|
| Grade  | Fail  |   1   |   2   |   3   |   4   |   5    |

## Copying and plagiarizing

All code delivered as course work must be written by the student him/herself taking part in the course. Copying code from others and from the internet in this course is **forbidden**. You may work together with a student friend or small group, but each of you must write **each and every line** of code to your project by yourself. You may do pair programming, for example, so that you work together on the problem to solve and *both of you* write your *own* code in your *own* repository to solve the tasks. *You yourself alone* are responsible for your code and receive the course grade from work done with your project.

You may use the course demonstrations and other course material for inspiration for your implementations in the exercises and course projects. You may -- and it is also recommended -- also search the internet for help. This is actually an important developer skill to have. But again, **each and every line of code** must be written by yourself, not copy-pasted.

If you use any code from others as inspiration, you **must** acknowledge that in your code in the comments. Add the source of the inspiration in the comments and provide the URL to the inspiration.

If copying or plagiarizing is suspected, teachers will follow the [University of Oulu procedures (pdf)](https://www.oulu.fi/external/Ohje-vilppitapausten-ehkaisemiseen-ja-kasittelemiseen-Oulun-yliopistossa-2018.pdf) in handling these cases. An interview with the student is arranged with course teachers. After hearing the student, teachers will decide if the issue should be taken forward. The minimum consequence is the immediate termination of the course for the student with a failed grade. If the case is serious, the matter will also be handed over to the Dean of the Faculty of Information Technology and Electrical Engineering.

## About

* Course material for Tietorakenteet ja algoritmit | Data structures and algorithms 2021.
* Study Program for Information Processing Science, University of Oulu.
* Antti Juustila, INTERACT Research Group.
