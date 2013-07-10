/**
 *
 */
package de.retresco.shift.exceptions;

/**
 * Base exception when talking to SHIFT.
 */
public class ShiftClientException extends Exception {

    public ShiftClientException(final Exception cause) {
        this.initCause(cause);
    }
}
