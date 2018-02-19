package com.privatesecuredata.eventplanner.pages;


import org.apache.tapestry5.Block;
import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.HttpError;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.slf4j.Logger;

import com.privatesecuredata.eventplanner.entities.Tenant;
import com.privatesecuredata.eventplanner.services.PersistanceService;

import java.util.Date;

/**
 * Start page of application eventplanner.
 */
public class Upcoming
{
  @Inject
  private Logger logger;

  @Inject
  private AjaxResponseRenderer ajaxResponseRenderer;

  @Property
  @Inject
  @Symbol(SymbolConstants.TAPESTRY_VERSION)
  private String tapestryVersion;

  @InjectPage
  private About about;

  @Inject
  private Block block;

  @Property
  Tenant newTenant;

  @Inject
  PersistanceService persistance;
  
  // Handle call with an unwanted context
  Object onActivate(EventContext eventContext)
  {
    return eventContext.getCount() > 0 ?
        new HttpError(404, "Resource not found") :
        null;
  }

  @Log
  void onComplete()
  {
    logger.info("Complete call on Index page");
  }

  @Log
  void onAjax()
  {
    logger.info("Ajax call on Index page");

    ajaxResponseRenderer.addRender("middlezone", block);
  }
  
  @Log
  void onSuccess()
  {
	  logger.info("Success");
	  persistance.save(newTenant);
  }


  public Date getCurrentTime()
  {
    return new Date();
  }
}
