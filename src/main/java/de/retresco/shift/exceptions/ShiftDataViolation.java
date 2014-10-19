/**
 * Copyright 2013 Retresco GmbH
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.retresco.shift.exceptions;

import de.retresco.shift.dao.ShiftDao;

import javax.validation.ConstraintViolation;
import java.text.MessageFormat;

/**
 * Exception is thrown when there was a problem with one of the objects you wanted to import.
 */
public class ShiftDataViolation extends ShiftException {

    /**
     * Access the underlying bean validation error.
     */
    private final ConstraintViolation<? extends ShiftDao> violation;

    public ShiftDataViolation(final ConstraintViolation<? extends ShiftDao> violation) {
        this.violation = violation;
    }

    @Override
    public String getMessage() {
        final String path = this.violation.getPropertyPath().iterator().next().getName();
        return MessageFormat.format("{0} {1}", path, this.violation.getMessage());
    }

    public ConstraintViolation<? extends ShiftDao> getViolation() {
        return violation;
    }
}