Title:  C195 Appointment Scheduler.  
Purpose:  To allow for the creation of customers and the scheduling of appointments.

Author:  Glenn Straughn  
Contact:  gstrau3@wgu.edu  
App Version:  v1.0  
Date:  1/7/2022  
IDE:  Intellij Community 2021.1.1, Java SE 11.0.11, JavaFX-SDK-11.0.11  

Directions:  
Enter valid username and password.  
Click Customer to navigate to the Customer view.  
 - Click 'New' to navigate to the add customer page.  
 -- Enter information in each field and click 'Save' to add to the database, or click 'Cancel' to return to the Customer page.  
 - Click 'Update' to navigate to the edit customer page.  
 -- Alter any information and click 'Save' to commit the customer changes to the database, or click 'Cancel' to return to the Customer page.  
 - Click 'Delete' to remove a selected customer from the database.
 - Click 'Back' to return to the Main Menu.  

Choose Appointments to navigate to the Appointment view.  
- Click 'New' to navigate to the add appointment page.  
  -- Enter information in each field and click 'Save' to add to the database, or click 'Cancel' to return to the Appointment page.
- Click 'Update' to navigate to the edit appointment page.  
  -- Alter any information and click 'Save' to commit the appointment changes to the database, or click 'Cancel' to return to the Appointment page.
- Click the 'This Month' radio to display the appointments scheduled during the current month.
- Click the 'This Week' radio to display the appointments scheduled during the current week.  
- Click the 'All' radio to display the all the appointments scheduled.    
- Click 'Delete' to remove a selected appointment from the database.
- Click 'Back' to return to the Main Menu.  

Choose Reports to navigate to the Reports generator view.  
 - Select an appointment type and a month. Then directly below, click 'Generate' to display the total.  
 - Click the 'Generate' button on the right to display the appointment grand total.  
 - Select the Contact Schedule tab to display the second report tab.  
 -- Select a contact to generate a schedule of appointments for the chosen contact.  
 - Click 'Back' to return to the Main Menu.  

Description:  This application allows a user to login and create customers and appointments for those customers in a database.  It can also generate reports containing information about the database.  

Additional Report:  The additional report generates a grand total of all the appointments contained in the database.  

MySQL driver:  mysql-connector-java-8.0.25