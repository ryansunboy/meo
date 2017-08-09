
package com.asiainfo.meo.meo.notification.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ApiKeyInitializer  {

  static final String ATTRIBUTE_ACCESS_KEY = "apiKey";

  private static final String PATH = "/api.key";

  private final Logger logger = Logger.getLogger(getClass().getName());



  /**
   * Gets the access key.
   */
  protected String getKey() {
    InputStream stream = Thread.currentThread().getContextClassLoader()
        .getResourceAsStream(PATH);
    if (stream == null) {
      throw new IllegalStateException("Could not find file " + PATH +
          " on web resources)");
    }
    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
    try {
      String key = reader.readLine();
      return key;
    } catch (IOException e) {
      throw new RuntimeException("Could not read file " + PATH, e);
    } finally {
      try {
        reader.close();
      } catch (IOException e) {
        logger.log(Level.WARNING, "Exception closing " + PATH, e);
      }
    }
  }

 
}
