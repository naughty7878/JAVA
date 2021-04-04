package com.test.spring.test;

import java.util.List;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;

import com.test.spring.bean.Person;
import com.test.spring.bean.PersonValidator;

public class TestValidator {

	public static void main(String[] args) {
		
		Person person = new Person();
		person.setAge(1000);
		
		PersonValidator validator = new PersonValidator();
        if(validator.supports(Person.class)){
            BindException errors = new BindException(person, "person");
            validator.validate(person,errors);
            List<ObjectError> allErrors = errors.getAllErrors();
            System.out.println("size="+allErrors.size());
            for (int i=0;i<allErrors.size();i++) {
                System.out.println(allErrors.get(i).getCode());
            }
        }
	}

}
