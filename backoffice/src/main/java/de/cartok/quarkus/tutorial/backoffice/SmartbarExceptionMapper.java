package de.cartok.quarkus.tutorial.backoffice;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class SmartbarExceptionMapper implements ExceptionMapper<Exception> {
  @Override
  public Response toResponse(Exception exception) {
    if (causedByConstraintViolation(exception)) {
      return Response.status(Response.Status.CONFLICT).build();
    }
    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
  }

  private static boolean causedByConstraintViolation(Exception exception) {
    for (Throwable t = exception; t != null; t = t.getCause()) {
      if (t instanceof ConstraintViolationException) {
        return true;
      }
    }
    return false;
  }
}
