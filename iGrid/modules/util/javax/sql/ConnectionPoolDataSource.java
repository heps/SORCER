/*
 * @(#)ConnectionPoolDataSource.java	1.1 99/05/11
 * 
 * Copyright (c) 1998 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Sun
 * Microsystems, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Sun.
 * 
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 * 
 * 
 */

package javax.sql;

import java.sql.SQLException;


/**
 * A ConnectionPoolDataSource object is a factory for PooledConnection
 * objects.  An object that implements this interface will typically be
 * registered with a JNDI service.
 */

public interface ConnectionPoolDataSource {

  /**
   * <p>Attempt to establish a database connection.
   *
   * @return  a Connection to the database
   * @exception SQLException if a database-access error occurs.
   */
  PooledConnection getPooledConnection() throws SQLException;
      
  /**
   * <p>Attempt to establish a database connection.
   *
   * @param user the database user on whose behalf the Connection is being made
   * @param password the user's password
   * @return  a Connection to the database
   * @exception SQLException if a database-access error occurs.
   */
  PooledConnection getPooledConnection(String user, String password) 
    throws SQLException;
      
  /**
   * <p>Get the log writer for this data source.  
   *
   * <p>The log writer is a character output stream to which all logging
   * and tracing messages for this data source object instance will be
   * printed.  This includes messages printed by the methods of this
   * object, messages printed by methods of other objects manufactured
   * by this object, and so on.  Messages printed to a data source
   * specific log writer are not printed to the log writer associated
   * with the java.sql.Drivermanager class.  When a data source object is
   * created the log writer is initially null, in other words, logging
   * is disabled.
   *
   * @return the log writer for this data source, null if disabled
   * @exception SQLException if a database-access error occurs.  
   */
  java.io.PrintWriter getLogWriter() throws SQLException;

  /**
   * <p>Set the log writer for this data source.
   *
   * <p>The log writer is a character output stream to which all logging
   * and tracing messages for this data source object instance will be
   * printed.  This includes messages printed by the methods of this
   * object, messages printed by methods of other objects manufactured
   * by this object, and so on.  Messages printed to a data source
   * specific log writer are not printed to the log writer associated
   * with the java.sql.Drivermanager class. When a data source object is
   * created the log writer is initially null, in other words, logging
   * is disabled.
   *
   * @param out the new log writer; to disable, set to null
   * @exception SQLException if a database-access error occurs.  
   */
  void setLogWriter(java.io.PrintWriter out) throws SQLException;

  /**
   * <p>Sets the maximum time in seconds that this data source will wait
   * while attempting to connect to a database.  A value of zero
   * specifies that the timeout is the default system timeout 
   * if there is one; otherwise it specifies that there is no timeout.
   * When a data source object is created the login timeout is
   * initially zero.
   *
   * @param seconds the data source login time limit
   * @exception SQLException if a database access error occurs.
   */
  void setLoginTimeout(int seconds) throws SQLException;
     
  /**
   * Gets the maximum time in seconds that this data source can wait
   * while attempting to connect to a database.  A value of zero
   * means that the timeout is the default system timeout 
   * if there is one; otherwise it means that there is no timeout.
   * When a data source object is created the login timeout is
   * initially zero.
   *
   * @return the data source login time limit
   * @exception SQLException if a database access error occurs.
   */
  int getLoginTimeout() throws SQLException;
   
 } 





