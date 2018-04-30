package com.tastycode.remy.util.testcases;

import static com.tastycode.remy.util.CookingUnits.CUP;
import static com.tastycode.remy.util.CookingUnits.CUP_LIQUID_US;
import static com.tastycode.remy.util.CookingUnits.TABLESPOON_LIQUID_US;
import static javax.measure.Measure.valueOf;
import static javax.measure.unit.NonSI.OUNCE_LIQUID_US;
import static junit.framework.Assert.assertEquals;

import java.text.NumberFormat;

import javax.measure.Measure;
import javax.measure.MeasureFormat;
import javax.measure.quantity.Volume;

import org.junit.Test;

import com.tastycode.remy.util.CookingUnitFormat;

public class CookingUnitsTest {

	@Test public void testTeaspoons() {
		Measure<?, Volume> liquidTableSpoon = valueOf(1, TABLESPOON_LIQUID_US);
		assertEquals(0.5, liquidTableSpoon.doubleValue(OUNCE_LIQUID_US));
	}
	
	@Test public void testLiquidCupUs() {
		Measure<?, Volume> cup = valueOf(1, CUP_LIQUID_US);
		assertEquals(8.0, cup.doubleValue(OUNCE_LIQUID_US));
	}
	
	@Test public void testMultipleUnits() {
		System.out.println(CookingUnitFormat.DEFAULT.format(CUP));
		System.out.println(
				MeasureFormat.getInstance(NumberFormat.getNumberInstance(), CookingUnitFormat.DEFAULT)
					.format(valueOf(1.0, CUP_LIQUID_US))
				);
		
	}
}
