WebRowSet - Sample

Contents

1. Overview of Sample

   ---------------------
        WebRowSet
   ---------------------
   
   This is a sample for demonstrating the functionality of a WebRowSet.
   It shows how to serialize data in the WebRowSet to an XML file once 
   the WebRowSet has been populated with data in the database. This 
   data in XML format is interoperable.
      
      It also shows how to populate a WebRowSet by reading an XML file,
   this can be used in scenarios where data is received from a remote
   sender in XML format and needs to be written to a database.
   
    
2. How to Run the Sample

   Make sure that your database is started,whatever database you are using.
   
   Ensure that you are in the directory corresponding to each sample.
   % ant
      Just run the ant command the default target gets invoked, the samples are then 
   compiled and run.
   
      The program takes 4 parameters, they are:
      i) The URL of the database to which we have to connect.
     ii) The username for connecting to this database.
    iii) The password for the above username
    iV ) The name of the class that implements the Driver interface.
    
   Make sure that the driver jar file is present in the lib directory.
   
   Currently the samplesare run against PointBase DB. If you want to run against other
   databases configure the build.xml accordingly to provide the correct parameters for
   the samples to run properly.
      
3. More information

(c) Sun Microsystems Inc. 2004
   
   
   
