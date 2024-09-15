package nz.ac.auckland.se206.controllers;

import nz.ac.auckland.apiproxy.exceptions.ApiProxyException;

public interface Controller {

  void initialize() throws ApiProxyException;

  void setTime(String timeRemaining);
}
