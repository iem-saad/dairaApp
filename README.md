# dairaApp 🌴
A semester project, Android application for famous event Daira of FAST-CFD campus.
I am a student of FAST NUCES and I was assigned a semester project in which we have to build an android application using Java. I have properly binded whole application.
There were four types of users of this application which are as following:
- Admin
- Mentors
- Organizing Committee
- Participants

I have divided the activities in four packages each for each type of user and one extra common package or onboarding and login/signup.
Moreover, it was required to use singleton pattern in our app. So, I used it to fetch current signed in user. For login and signup of users I have used firebase authentication for participants and for remaining users firebase Realtime database is used.

For Animations **Lottie by Airbnb** is used.
For Database **Firebase** is used.

## Each User and its responsibilities are given below:


### Admin and their role:

1. Admins are pre-defined persons (already in database) who can log in to the admins
portal.
2. Admin can create different categories of events i.e. Socials, Sports, Technical, etc.
3. Admin can register multiple mentors (give them a generic username/pass).
4. Admin can assign events to registered mentors.
5. Admin can see all registrations.


### Mentors and their role:

1. Mentor can log in with username/pass (provided by admin) to mentor portal.
2. Mentor can create different sub-events with brief descriptions and images i.e. For
Socials: Mushaira, Concert, Theme Dinner, etc. in their assigned event category.
3. Mentor can register multiple OC (Organizing Committee members-student) and give
them a generic username/pass).
4. Mentor can assign sub-events to registered OC (one for each), so that for each sub-
event there will be a group of OC (student) managing that sub-event.
5. Mentor can see event registrations and all registrations.

### Organizing Committee and their role:

1. The organizing Committee can log in with username/pass (provided by Mentors) to
OC portal.
2. Organizing Committee can see the mentor’s message.
3. Organizing Committee can see registered participants in their event.
4. Organizing Committee set the venue for their event.
5. Organizing Committee can send event update/news messages.
6. For competitive event Organizing Committee should also set score boards.

### Participant and their role:

1. Participant can register by participating in any event (signup).
2. Registered participant can log in to the participant portal.
3. Participant can see all the venues of events happening in Daira.
4. Participant can see the scoreboard of their participating event.
5. Participant can see news about participating events.


## Following is snapshot of UI of App.

![Daira app](https://user-images.githubusercontent.com/54322326/179301443-8fa5e4ef-0bcf-478f-ab68-9d6d338f83e8.png)
