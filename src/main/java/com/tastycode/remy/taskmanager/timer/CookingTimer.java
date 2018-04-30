package com.tastycode.remy.taskmanager.timer;

import java.util.List;

import com.tastycode.remy.commonobjects.recipe.Ingredient;
import com.tastycode.remy.commonobjects.recipe.Step;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class CookingTimer
{
    private long startTime;
    private long endTime;
    private String name;
    private Step referringStep;
    private List<Ingredient> referringIngredients;
    
    public void start()
    {
        
    }

}
