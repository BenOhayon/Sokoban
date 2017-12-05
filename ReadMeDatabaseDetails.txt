----------------------------------------------------------------------------------
ReadMeDatabaseDetails.txt---------------------------------------------------------
Sokoban Project by Sapir Shloush 201058286 and Ben Ohayon 311497135, Milestone 3--
----------------------------------------------------------------------------------

This project uses the following:
(*) Database: "Sokoban"
(*) Table: "World_Records"
(*) Persistant class: db.PersPlayer.java

World_Records table definition by SQL:
--------------------------------------

create table World_Records
(
	Player_Username nvarchar(30) not null,
	Level_Name nvarchar(25) not null,
	Player_steps int not null,
	Player_seconds int not null
)

Additional comments:
--------------------
1) The "Resources" directory containes all the accessory files such as images and media.
2) The SokobanMilestone3.jar includes all the source code files (.java files) and SokobanMilestone3_Runnable.jar file 	includes all the bytecode files (.class files) to make the application run properly.
3) The "Resources" directory and the sokobanMilestone3.bat file are located in the same path to give the application access to all the "Resources" directory.