package de.retresco.shift.exceptions;

import de.retresco.shift.dao.BaseDocument;
import lombok.Getter;

import javax.validation.ConstraintViolation;
import java.text.MessageFormat;

/**
 * Exception is thrown when there was a problem with one of the objects you wanted to import.
 */
public class ShiftDataViolation extends ShiftException {

    /**
     * Access the underlying bean validation error.
     */
    @Getter
    private final ConstraintViolation<BaseDocument> violation;

    public ShiftDataViolation(final ConstraintViolation<BaseDocument> violation) {
        this.violation = violation;
    }

    @Override
    public String getMessage() {
        final String path = this.violation.getPropertyPath().iterator().next().getName();
        return MessageFormat.format("{0} {1}", path, this.violation.getMessage());
    }
}