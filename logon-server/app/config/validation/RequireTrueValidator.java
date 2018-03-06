package config.validation;

import play.data.validation.Constraints;
import play.libs.F;

import javax.validation.ConstraintValidator;

/**
 * @author Michael Ruf
 * @since 2018-03-05
 */
public class RequireTrueValidator extends Constraints.Validator<Boolean>
        implements ConstraintValidator<MyConstraints.RequireTrue, Boolean> {

    final static public String message = "error.required_true";

    @Override
    public void initialize(MyConstraints.RequireTrue constraintAnnotation) {
    }

    @Override
    public boolean isValid(Boolean object) {
        return object != null && object;
    }

    @Override
    public F.Tuple<String, Object[]> getErrorMessageKey() {
        return F.Tuple(message, new Object[]{});
    }
}
