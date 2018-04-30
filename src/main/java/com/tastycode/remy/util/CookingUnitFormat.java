package com.tastycode.remy.util;

import static com.tastycode.remy.util.CookingUnits.BUNCH;
import static com.tastycode.remy.util.CookingUnits.COUNT;
import static com.tastycode.remy.util.CookingUnits.CUP;
import static com.tastycode.remy.util.CookingUnits.CUP_LIQUID_US;
import static com.tastycode.remy.util.CookingUnits.DASH;
import static com.tastycode.remy.util.CookingUnits.GALLON_LIQUID_US;
import static com.tastycode.remy.util.CookingUnits.LITER;
import static com.tastycode.remy.util.CookingUnits.PINCH;
import static com.tastycode.remy.util.CookingUnits.PINT_LIQUID_US;
import static com.tastycode.remy.util.CookingUnits.PINT_US;
import static com.tastycode.remy.util.CookingUnits.TABLESPOON;
import static com.tastycode.remy.util.CookingUnits.TABLESPOON_LIQUID_US;
import static com.tastycode.remy.util.CookingUnits.TEASPOON;
import static com.tastycode.remy.util.CookingUnits.TEASPOON_LIQUID_US;

import javax.measure.unit.UnitFormat;

public abstract class CookingUnitFormat extends UnitFormat {
	protected static class DefaultCookingFormat extends DefaultFormat {
		
	}
	
	public static UnitFormat DEFAULT;
	
	static {
		DEFAULT = new DefaultCookingFormat();
		DEFAULT.label(PINT_US, "pint");
		DEFAULT.label(PINT_LIQUID_US, "liquid_pint");
		DEFAULT.label(CUP, "cup");
		DEFAULT.label(CUP_LIQUID_US, "liquid_cup");
		DEFAULT.label(TABLESPOON, "tablespoon");
		DEFAULT.label(TABLESPOON_LIQUID_US, "liquid_tablespoon");
		DEFAULT.label(TEASPOON, "teaspoon");
		DEFAULT.label(TEASPOON_LIQUID_US, "liquid_teaspoon");
		DEFAULT.label(PINCH, "pinch");
		DEFAULT.label(DASH, "dash");
		DEFAULT.label(LITER, "liter");
		DEFAULT.label(GALLON_LIQUID_US, "liquid_gallon");
		DEFAULT.label(COUNT, "count");
		DEFAULT.label(BUNCH, "bunch");
	}
}
