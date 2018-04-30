package com.tastycode.remy.commonobjects.domainmodel;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.tastycode.remy.commonobjects.recipe.Recipe;
import com.tastycode.remy.commonobjects.stepmodel.DiscreteStepModel;
import com.tastycode.remy.commonobjects.stepmodel.StepModel;
import com.tastycode.remy.commonobjects.timermodel.DescreteTimerModel;
import com.tastycode.remy.commonobjects.timermodel.TimerModel;

/**
 *
 *
 * @author <a href="mailto:gregorym@gmail.com">Greg Milette</a>
 */
public class RecipeDomainModel
{
    private Recipe recipe;
    private StepModel stepModel;
    private TimerModel timerModel;
    private LikelyMatches likelyMatches;
    private List<RecipeDomainListener> listeners = new CopyOnWriteArrayList<RecipeDomainListener>();

    public RecipeDomainModel(Recipe recipe)
    {
        this(recipe, new DiscreteStepModel(recipe));
    }
    public RecipeDomainModel(Recipe recipe, StepModel stepModel)
    {
        this.recipe = recipe;
        this.stepModel = stepModel;
        this.likelyMatches = new LikelyMatches(this);
        this.timerModel = new DescreteTimerModel();
    }
    
    protected void initialize() {
		this.stepModel = new DiscreteStepModel(getRecipe());
		this.likelyMatches = new LikelyMatches(this);
		this.timerModel = new DescreteTimerModel();
		fireUpdated();
	}
    
    protected void fireUpdated() {
    	for (RecipeDomainListener listener : listeners) {
			listener.updatedRecipeDomainModel(this);
		}
	}
	public void addListener(RecipeDomainListener listener) {
    	listeners.add(listener);
    }
    
    public Recipe getRecipe()
    {
        return recipe;
    }
    
    public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
		initialize();
	}
    
	public StepModel getStepModel()
    {
        return stepModel;
    }
    public LikelyMatches getLikelyMatches()
    {
        return likelyMatches;
    }
	public TimerModel getTimerModel() {
		return timerModel;
	}
	public void setTimerModel(TimerModel timerModel) {
		this.timerModel = timerModel;
	}
}
