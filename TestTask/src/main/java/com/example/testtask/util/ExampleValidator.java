package com.example.testtask.util;


import com.example.testtask.models.Example;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Stack;

@Component
public class ExampleValidator implements Validator {
    private static final String VALID_PATTERN = "^[0-9xX+\\-*/()=\\s]+$";


    @Override
    public boolean supports(Class<?> clazz) {
        return Example.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Example example = (Example) target;
        String input = example.getExample();
        if (!isExpressionValid(input) || !input.matches(VALID_PATTERN)) {
            errors.rejectValue("example", "Invalid Expression");
        }


    }

    private boolean isExpressionValid(String example) {

        for (int i = 1; i < example.length(); i++) {
            char prevChar = example.charAt(i - 1);
            char currentChar = example.charAt(i);

            if (isOperator(prevChar) && isOperator(currentChar) && currentChar != '-') {
                return false;
            }
        }

        Stack<Character> stack = new Stack<>();
        for (char ch : example.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }


}
