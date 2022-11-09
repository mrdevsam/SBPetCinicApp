package com.app.petclinic.formatters;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.app.petclinic.model.Pet;

public class PetValidator implements Validator {

	private static final String REQUIRED = "required";

	@Override
	public void validate(Object obj, Errors errors) {
		Pet pet = (Pet) obj;
		String name = pet.getName();
		// name validation
		if (!StringUtils.hasLength(name)) {
			errors.rejectValue("name", REQUIRED, REQUIRED);
		}

		// type validation
		if (pet.isNew() && pet.getPetType() == null) {
			errors.rejectValue("type", REQUIRED, REQUIRED);
		}

		// birth date validation
		if (pet.getBirthDate() == null) {
			errors.rejectValue("birthDate", REQUIRED, REQUIRED);
		}
	}

	/**
	 * This Validator validates *just* Pet instances
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Pet.class.isAssignableFrom(clazz);
	}

}
