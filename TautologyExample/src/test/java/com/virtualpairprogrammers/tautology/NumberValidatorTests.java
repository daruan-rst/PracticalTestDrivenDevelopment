package com.virtualpairprogrammers.tautology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NumberValidatorTests {

	@Test
	public void checkPrimeNumbers()
	{
		
		Integer[] numbers = {1,23,61,79};
		NumberValidator validator = new NumberValidator();

		for (Integer number : numbers) {
			assertTrue(validator.isItPrime(number));
		}
	}
		


	@Test
	public void checkNonPrimeNumbers()
	{

		Integer[] numbers = {15,25,60,63,207};
		NumberValidator validator = new NumberValidator();

		for (Integer number : numbers) {
			assertFalse(validator.isItPrime(number));
		}

		}

	}
