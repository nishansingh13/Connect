# Software Requirements Specification
for
Connect Social Media Platform
Version 1.0 approved

Prepared by Nishan
Connect Development Team
October 2023

## Table of Contents
- [Revision History](#revision-history)
- [1. Introduction](#1-introduction)
  - [1.1 Purpose](#11-purpose)
  - [1.2 Document Conventions](#12-document-conventions)
  - [1.3 Intended Audience and Reading Suggestions](#13-intended-audience-and-reading-suggestions)
  - [1.4 Project Scope](#14-project-scope)
  - [1.5 References](#15-references)
- [2. Overall Description](#2-overall-description)
  - [2.1 Product Perspective](#21-product-perspective)
  - [2.2 Product Features](#22-product-features)
  - [2.3 User Classes and Characteristics](#23-user-classes-and-characteristics)
  - [2.4 Operating Environment](#24-operating-environment)
  - [2.5 Design and Implementation Constraints](#25-design-and-implementation-constraints)
  - [2.6 User Documentation](#26-user-documentation)
  - [2.7 Assumptions and Dependencies](#27-assumptions-and-dependencies)
- [3. System Features](#3-system-features)
  - [3.1 User Authentication](#31-user-authentication)
  - [3.2 Post Management](#32-post-management)
  - [3.3 User Following](#33-user-following)
  - [3.4 Dashboard Views](#34-dashboard-views)
- [4. External Interface Requirements](#4-external-interface-requirements)
  - [4.1 User Interfaces](#41-user-interfaces)
  - [4.2 Hardware Interfaces](#42-hardware-interfaces)
  - [4.3 Software Interfaces](#43-software-interfaces)
  - [4.4 Communications Interfaces](#44-communications-interfaces)
- [5. Other Nonfunctional Requirements](#5-other-nonfunctional-requirements)
  - [5.1 Performance Requirements](#51-performance-requirements)
  - [5.2 Safety Requirements](#52-safety-requirements)
  - [5.3 Security Requirements](#53-security-requirements)
  - [5.4 Software Quality Attributes](#54-software-quality-attributes)
- [6. Other Requirements](#6-other-requirements)
- [Appendix A: Glossary](#appendix-a-glossary)
- [Appendix B: Analysis Models](#appendix-b-analysis-models)
- [Appendix C: Issues List](#appendix-c-issues-list)

## Revision History
| Name | Date | Reason For Changes | Version |
|------|------|-------------------|---------|
| Nishan | 10/15/2023 | Initial document creation | 1.0 |

## 1. Introduction

### 1.1 Purpose
This Software Requirements Specification (SRS) document describes the requirements for the Connect application, a social media platform that allows users to register, log in, post messages, and follow other users. It provides a detailed outline of the functional and non-functional requirements necessary for the development team to implement the system.

### 1.2 Document Conventions
This document follows these conventions:
- **SHALL**: Indicates a mandatory requirement
- **SHOULD**: Indicates a recommended requirement
- **MAY**: Indicates an optional requirement
- Requirements are prioritized as High (H), Medium (M), or Low (L)
- System features are numbered for easy reference (e.g., REQ-1)

### 1.3 Intended Audience and Reading Suggestions
This document is intended for:
- **Developers**: Focus on sections 3-5 for implementation details
- **Project Managers**: Focus on sections 1-2 for overview and scope
- **Testers**: Focus on sections 3-4 for understanding features to test
- **UI/UX Designers**: Focus on section 4.1 for interface requirements

Readers should begin with the Overall Description (Section 2) to understand the context before moving to specific sections relevant to their role.

### 1.4 Project Scope
Connect is a social networking application that allows users to:
- Create personal accounts with username/password authentication
- Post text messages visible to all users
- Follow other users to curate content
- View posts from all users or just from followed users

The application supports both Command Line Interface (CLI) and Graphical User Interface (GUI) implementations, allowing users to choose their preferred interaction method.

### 1.5 References
- Java Swing Documentation: https://docs.oracle.com/javase/tutorial/uiswing/
- MVC Design Pattern: https://en.wikipedia.org/wiki/Model–view–controller
- Java Development Kit Documentation: https://docs.oracle.com/en/java/

## 2. Overall Description

### 2.1 Product Perspective
Connect is a standalone social media application that demonstrates core social networking features. It is designed as a self-contained system that operates locally on a user's machine. While it shares conceptual similarities with other social platforms, it is not intended to integrate with external systems or existing social networks.

The system follows the Model-View-Controller (MVC) architecture pattern:
- **Model**: User and Post classes for data representation
- **View**: CLI (ConnectView) and GUI components (MyFrame, Register, Dashboard)
- **Controller**: ConnectController managing application logic

### 2.2 Product Features
The major features of Connect include:

1. **User Management**
   - User registration with unique usernames
   - User authentication (login)
   - Session management

2. **Content Management**
   - Creating text posts
   - Viewing all posts in the system
   - Viewing posts from followed users

3. **Social Networking**
   - Following other users
   - Viewing list of followed users
   - Filtering content based on social connections

4. **Multi-Interface Support**
   - Command Line Interface (CLI)
   - Graphical User Interface (GUI) with tabbed views

### 2.3 User Classes and Characteristics
1. **Casual Users**
   - Characteristics: Minimal technical knowledge, prefer GUI interface
   - Frequency: Occasional use
   - Priority: High
   - Features used: Basic posting, following, viewing content

2. **Technical Users**
   - Characteristics: Technical background, comfortable with command line
   - Frequency: Regular use
   - Priority: Medium
   - Features used: CLI interface, all functionality

3. **Power Users**
   - Characteristics: Frequent usage, create lots of content, many connections
   - Frequency: Daily use
   - Priority: Medium
   - Features used: All features across both interfaces

### 2.4 Operating Environment
- **Platform**: Any system supporting Java Runtime Environment 8 or higher
- **Operating Systems**: Windows, macOS, Linux
- **Display Resolution**: Minimum 800x600 pixels for GUI mode
- **Memory**: Minimum 512MB RAM
- **Disk Space**: Minimum 100MB free disk space

### 2.5 Design and Implementation Constraints
- **Programming Language**: Java
- **GUI Framework**: Java Swing
- **Architecture Pattern**: Model-View-Controller (MVC)
- **Data Storage**: In-memory storage (no persistence between application restarts)
- **Single User Session**: Only one user can be logged in at a time
- **No Network Communication**: Local application with no client-server architecture

### 2.6 User Documentation
The following documentation will be delivered with the software:
- **User Guide**: Instructions for installation and basic usage
- **Developer Guide**: Architecture overview and extension guidelines
- **In-application Help**: Basic tooltips and instructions embedded in the GUI

### 2.7 Assumptions and Dependencies
- **Assumptions**:
  - Users have basic knowledge of social media concepts
  - Users understand the concept of following other users
  - Single-user operation (not a multi-user networked application)

- **Dependencies**:
  - Java Runtime Environment (JRE) 8 or higher
  - Java Swing library for GUI components

## 3. System Features

### 3.1 User Authentication

#### 3.1.1 Description and Priority
User authentication provides account creation, login, and session management functionalities. This feature is essential for identifying users and managing their content and connections.
**Priority**: High

#### 3.1.2 Stimulus/Response Sequences
- **Registration**:
  - User selects "Register" option
  - System displays registration form
  - User enters username, email, and password
  - System validates inputs and creates account or shows error

- **Login**:
  - User selects "Login" option
  - System displays login form
  - User enters username and password
  - System validates credentials and grants access or shows error

#### 3.1.3 Functional Requirements
REQ-1: The system SHALL allow users to register with a unique username and password.  
REQ-2: The system SHALL prevent duplicate usernames during registration.  
REQ-3: The system SHALL authenticate users with their username and password.  
REQ-4: The system SHALL maintain user sessions after successful login.  
REQ-5: The system SHALL provide visual feedback for successful/failed authentication.  

### 3.2 Post Management

#### 3.2.1 Description and Priority
Post management allows users to create text posts and view posts from themselves and other users.
**Priority**: High

#### 3.2.2 Stimulus/Response Sequences
- **Create Post**:
  - User enters post content in text field
  - User clicks "Post" button
  - System saves post and displays confirmation
  - System updates post display to include new post

- **View Posts**:
  - User navigates to "Posts" tab
  - System displays all posts with author information
  - User can scroll through posts

#### 3.2.3 Functional Requirements
REQ-6: The system SHALL allow logged-in users to create text posts.  
REQ-7: The system SHALL display posts with the author's username.  
REQ-8: The system SHALL store posts and associate them with their authors.  
REQ-9: The system SHALL prevent non-logged-in users from creating posts.  
REQ-10: The system SHALL display all posts in a scrollable view.  

### 3.3 User Following

#### 3.3.1 Description and Priority
User following enables users to create connections with other users and filter content based on these connections.
**Priority**: Medium

#### 3.3.2 Stimulus/Response Sequences
- **Follow User**:
  - User navigates to "Following" tab
  - User enters username to follow in text field
  - User clicks "Follow" button
  - System creates connection and updates following list

- **View Following**:
  - User navigates to "Following" tab
  - System displays list of followed usernames

#### 3.3.3 Functional Requirements
REQ-11: The system SHALL allow users to follow other users by username.  
REQ-12: The system SHALL display a list of usernames the current user is following.  
REQ-13: The system SHALL validate that the username to follow exists before creating a connection.  
REQ-14: The system SHALL prevent users from following non-existent users.  

### 3.4 Dashboard Views

#### 3.4.1 Description and Priority
Dashboard views provide different ways to view and interact with content based on the user's preferences.
**Priority**: Medium

#### 3.4.2 Stimulus/Response Sequences
- **View All Posts**:
  - User selects "All Posts" tab
  - System displays posts from all users

- **View Following Posts**:
  - User selects "Following Posts" tab
  - System displays only posts from followed users

#### 3.4.3 Functional Requirements
REQ-15: The system SHALL provide a tabbed interface for different content views.  
REQ-16: The system SHALL filter posts to show only content from followed users when in the "Following Posts" view.  
REQ-17: The system SHALL display appropriate messages when no content is available in any view.  
REQ-18: The system SHALL allow users to navigate between views without losing session state.  
REQ-19: The system SHALL provide a way to return to the login/register screen.  

## 4. External Interface Requirements

### 4.1 User Interfaces

#### Command Line Interface (CLI)
- Text-based menu system with numbered options
- Prompt-based input for all operations
- Clear text feedback for all actions

#### Graphical User Interface (GUI)
1. **Welcome Screen**:
   - Welcome message
   - Navigation button to registration/login

2. **Registration/Login Screen**:
   - Username field (text input)
   - Email field (text input)
   - Password field (masked input)
   - Register and Login buttons
   - Error/success message display area

3. **Dashboard Screen**:
   - Header with logged-in username and back button
   - Tabbed interface for different views
   - Text area for content display in each tab
   - Input fields for posting and following users
   - Action buttons with clear labels

GUI Standards:
- Font: Arial, sizes 14-24pt for various elements
- Primary button color: #007bff (blue)
- Error text color: Red
- Success text color: Green
- Background color: White
- Consistent padding (5-10px) around elements
- Responsive layout using appropriate layout managers

### 4.2 Hardware Interfaces
The system has no specific hardware interface requirements beyond standard input/output devices:
- Keyboard for text input
- Mouse for GUI navigation
- Display (minimum 800x600 resolution for GUI mode)

### 4.3 Software Interfaces
- **Java Runtime Environment**: Version 8 or higher
- **Operating System**: Any OS supporting JRE
- **Java Swing**: For GUI components and rendering

### 4.4 Communications Interfaces
The current version of Connect does not implement network communications. All operations are performed locally on the user's machine.

## 5. Other Nonfunctional Requirements

### 5.1 Performance Requirements
- The system SHALL respond to user actions within 500ms.
- The system SHALL load all posts within 2 seconds when switching tabs.
- The system SHALL accommodate at least 1000 posts without significant performance degradation.
- The GUI SHALL render smoothly without visible lag on standard hardware.

### 5.2 Safety Requirements
No critical safety requirements apply to this social media application as it does not control physical systems or have life-critical functions.

### 5.3 Security Requirements
- The system SHOULD NOT display passwords in plain text.
- The system SHALL validate user inputs to prevent injection attacks.
- The system SHALL only allow authenticated users to create posts.
- The system SHALL ensure that user sessions are properly managed.
- The system SHOULD implement appropriate access controls for user data.

### 5.4 Software Quality Attributes
- **Usability**: The interface SHALL be intuitive for users familiar with social media.
- **Maintainability**: The code SHALL follow OOP principles and MVC architecture.
- **Reliability**: The system SHALL handle invalid inputs without crashing.
- **Testability**: All features SHALL have clear inputs and outputs that can be verified.
- **Extensibility**: The system SHALL be designed to allow new features to be added easily.
- **Portability**: The system SHALL run on any platform supporting Java SE 8+.

## 6. Other Requirements
- **Localization**: Initial release will be in English only. Future versions may add support for additional languages.
- **Compliance**: The application is not subject to specific regulatory requirements at this time.

## Appendix A: Glossary
- **Post**: A text message created by a user that can be viewed by other users
- **Follow**: A one-way relationship where a user subscribes to see another user's content
- **Dashboard**: The main interface after login that displays different views of content
- **CLI**: Command Line Interface, a text-based user interface
- **GUI**: Graphical User Interface, a visual interface with graphical elements
- **MVC**: Model-View-Controller, the architectural pattern used in the application

## Appendix B: Analysis Models

### Class Diagram (Simplified)
```
+---------------+      +---------------+      +-----------------+
|    User       |      |     Post      |      | ConnectController|
+---------------+      +---------------+      +-----------------+
| -username     |      | -content      |      | -users          |
| -password     |      | -author       |      | -posts          |
| -following    |      | -comments     |      | -loggedUser     |
+---------------+      +---------------+      +-----------------+
| +getUsername()|      | +getContent() |      | +registerUser() |
| +getPassword()|      | +getAuthor()  |      | +loginUser()    |
| +getFollowing()|     | +getComments()|      | +postMessage()  |
| +follow()     |      | +addComment() |      | +getPosts()     |
| +unfollow()   |      |               |      | +followUser()   |
+---------------+      +---------------+      | +getFollowing() |
                                             +-----------------+
```

### Use Case Diagram (Simplified)
```
+-----------------------------------------------------------+
|                     Connect Application                    |
+-----------------------------------------------------------+
                            |
                            |
+---------------+           |           +-----------------+
|               |           |           |                 |
|   Register    |<----------+---------->|     Login       |
|               |                       |                 |
+---------------+                       +-----------------+
                                          |
                                          |
               +---------------------------+----------------------------+
               |                           |                            |
      +----------------+        +--------------------+       +--------------------+
      |                |        |                    |       |                    |
      |  Create Post   |        |    Follow User     |       |   View Posts       |
      |                |        |                    |       |                    |
      +----------------+        +--------------------+       +--------------------+
```

## Appendix C: Issues List

1. **Data Persistence**: Currently, all data is stored in-memory and lost when the application closes. Future versions should implement persistent storage.

2. **Password Security**: Current implementation stores passwords as plain text. Consider implementing password hashing in a future release.

3. **Post Limitations**: Current system only supports text posts. Consider adding support for images or links in future versions.

4. **Email Verification**: The system collects email addresses but doesn't verify them. Consider adding verification in future releases.

5. **User Search**: Currently following requires exact username entry. A search feature would improve usability.
