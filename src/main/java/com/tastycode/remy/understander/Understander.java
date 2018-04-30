package com.tastycode.remy.understander;

import com.tastycode.remy.commonobjects.dialog.UserUtterance;
import com.tastycode.remy.commonobjects.dialog.systemutterance.SystemUtterance;
import com.tastycode.remy.commonobjects.domainmodel.RecipeDomainModel;
import com.tastycode.remy.commonobjects.recipe.Recipe;
import com.tastycode.remy.taskmanager.step.StepFeature;

/**
 *
 *
 */
public class Understander
{
    private Recipe recipe;
    
    private StepFeature stepFeature;
    
    public Understander(Recipe recipe)
    {
        this.recipe = recipe;
        stepFeature = new StepFeature(new RecipeDomainModel(recipe));
    }
    
    public void go()
    {
        //find predicate structure
        //find semantic roles
        //detect semantic restrictions.. assume stuff
    }
    
    /**
     * update our knowledge base.
     * @param userUtter
     */
    public SystemUtterance interpret(UserUtterance userUtter)
    {
        //match what was said, to each part of the recipe.
        //the ingredients, the steps, and the tools.
        //find the highest matching line and then talk about that

        //which ingredient?
        SystemUtterance whatToSay = stepFeature.respond(userUtter);
        return whatToSay;
    }
    
    
    //non logical vocab
    //logical vocab, qualifiers, operators etc...

}
