package com.example.testtask.util;

import org.springframework.stereotype.Component;

@Component
public class EquationSolver {
        public boolean isRoot(String expression, double roots) {
            try {
                double calculatedValue = evaluateExpression(expression);
                double difference = Math.abs(calculatedValue - roots);
                return difference < 1e-10;
            } catch (Exception e) {
                return false;
            }
        }

        private double evaluateExpression(String expression) {
            String[] parts = expression.split("=");
            String leftSide = parts[0].trim();
            String rightSide = parts[1].trim();

            String[] leftParts = leftSide.split("x");
            double coefficient = Double.parseDouble(leftParts[0].trim());

            String[] rightParts = rightSide.split("x");
            double constant = Double.parseDouble(rightParts[0].trim());

            return (constant / coefficient);
        }




}


