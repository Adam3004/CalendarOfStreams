## Calendar for streamers
# About project:
My friend streams games. It inspired me to write application in which streamers and viewers can communicate easily with each other. Streamer can add event. 
Viewers will be informate about that and they can easily check when they can watch some stream. Moreover, I added message communicator. It is my second bigger project, so I focused mostly on training my skills. I wanted to write as much as possible myself. I didn't use any libraries, but I connected my application with POstreSQL database, but some of data I saved in file to test on my way which option is better. Now I know that database is better :D 
# Technologies:
* Java 10 (16.0.2)
* postgresql (42.2.23)
# How to run this application?:
1. Find services on your computer and open it
2. Find postgresql-x64-13 and start it
3. Remember to set your password to PostgreSQL in Kalendarz-Streamow\src\com\company\connection\Connector.java 
4. Now add 3 new dataBases and name them: "users", "messages", "calendar"
5. Click on them and choose restore, now find folder database in Kalendarz-Streamow and choose correct base
6. If u will have to add driver, it is in Kalendarz-Streamow\postgresql-42.2.23.jar
7. Click on file in intelij, choose Project structure...
8. Click on dependencies and + 
9. Find driver in your computer (Kalendarz-Streamow\postgresql-42.2.23.jar)
10. Click on apply and ok
11. Now everything should be ready :D
