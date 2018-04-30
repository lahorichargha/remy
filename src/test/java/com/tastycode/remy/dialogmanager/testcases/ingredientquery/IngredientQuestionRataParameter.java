package com.tastycode.remy.dialogmanager.testcases.ingredientquery;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.junit.experimental.theories.ParametersSuppliedBy;

@Retention(RetentionPolicy.RUNTIME)
@ParametersSuppliedBy(IngredientQuestionsRatatouille.class)
public @interface IngredientQuestionRataParameter
{

}
