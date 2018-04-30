package com.tastycode.remy.dialogmanager.testcases.step;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.junit.experimental.theories.ParametersSuppliedBy;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@ParametersSuppliedBy(SpecificStepQuestions.class)
public @interface SpecificStepQuestionsTest 
{
}
