/**
 *
 */
package de.retresco.shift.exceptions;

/**
 * Wrapper exception thrown, when there was a problem communicating with the SHIFT import API.
 */
public class ShiftClientException extends ShiftException {

    public ShiftClientException(final Exception cause) {
        this.initCause(cause);
    }

}
