package com.tastycode.remy.util;

import java.util.HashSet;
import java.util.Set;

import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Quantity;
import javax.measure.quantity.Volume;
import javax.measure.unit.NonSI;
import javax.measure.unit.SystemOfUnits;
import javax.measure.unit.Unit;

public class CookingUnits extends SystemOfUnits {

	@Override
	public Set<Unit<?>> getUnits() {
		return UNITS;
	}

    public static CookingUnits getInstance()
    {
        return INSTANCE;
    }
    
    public static <E extends Quantity> Unit<E> cookingUnit(Unit<E> unit) {
    	UNITS.add(unit);
    	return unit;
    }
	
    private static final CookingUnits INSTANCE = new CookingUnits();
    private static HashSet<Unit<? extends Quantity>> UNITS = new HashSet<Unit<? extends Quantity>>();

    public static final Unit<Mass> PINT_US = cookingUnit(NonSI.OUNCE.times(16.0)); 
    public static final Unit<Volume> PINT_LIQUID_US = cookingUnit(NonSI.OUNCE_LIQUID_US.times(16.0)); 
    
    public static final Unit<Mass> CUP = cookingUnit(NonSI.OUNCE.times(8.0)); 
    public static final Unit<Volume> CUP_LIQUID_US = cookingUnit(NonSI.OUNCE_LIQUID_US.times(8.0)); 
    public static final Unit<Volume> TABLESPOON_LIQUID_US = cookingUnit(NonSI.OUNCE_LIQUID_US.divide(2.0));
    public static final Unit<Volume> TEASPOON_LIQUID_US =   cookingUnit(NonSI.OUNCE_LIQUID_US.divide(6.0));
    public static final Unit<Mass> TABLESPOON =   cookingUnit(NonSI.OUNCE.divide(2.0));
    public static final Unit<Mass> TEASPOON =     cookingUnit(NonSI.OUNCE.divide(6.0));
    
    public static final Unit<Mass> PINCH =     cookingUnit(NonSI.OUNCE.divide(96.0));
    public static final Unit<Mass> DASH =     cookingUnit(NonSI.OUNCE.divide(48.0));

    // Copies of NonSI units
    public static final Unit<Volume> LITER = cookingUnit(NonSI.LITER);
    public static final Unit<Volume> GALLON_LIQUID_US = cookingUnit(NonSI.GALLON_LIQUID_US);
    
    public static final Unit<Dimensionless> COUNT = Dimensionless.UNIT.alternate("count");
    public static final Unit<Dimensionless> ONE   = Dimensionless.UNIT.alternate("one");
    public static final Unit<Dimensionless> BUNCH = Dimensionless.UNIT.alternate("bunch");
}
