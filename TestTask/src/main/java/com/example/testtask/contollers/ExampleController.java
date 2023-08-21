package com.example.testtask.contollers;

import com.example.testtask.models.Example;
import com.example.testtask.services.ExampleService;
import com.example.testtask.util.EquationSolver;
import com.example.testtask.util.ExampleErrorResponse;
import com.example.testtask.util.ExampleException;
import com.example.testtask.util.ExampleValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.testtask.util.ErrorsUtil.returnErrorsToClient;

@RestController
public class ExampleController {
    private final ExampleService exampleService;
    private final ExampleValidator exampleValidator;
    private final EquationSolver equationSolver;

    public ExampleController(ExampleService exampleService, ExampleValidator exampleValidator, EquationSolver equationSolver) {
        this.exampleService = exampleService;
        this.exampleValidator = exampleValidator;
        this.equationSolver = equationSolver;
    }

    @PostMapping("/new")
    public ResponseEntity<?> createExample(@RequestBody Example example, BindingResult bindingResult) {
        exampleValidator.validate(example, bindingResult);
        if (bindingResult.hasErrors()) {
            returnErrorsToClient(bindingResult);
        }
        try {
            boolean isRoot = equationSolver.isRoot(example.getExample(), example.getRoots());

            if (isRoot) {
                exampleService.save(example);
                return ResponseEntity.ok("Record saved successfully.");
            } else {
                return ResponseEntity.badRequest().body("Value is not a root.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error evaluating expression.");
        }

    }

    @GetMapping("/examples/{roots}")
    public ResponseEntity<List<Example>> getExamplesByRoots(@PathVariable Double roots) {
        List<Example> examples = exampleService.findAllByRoots(roots);
        return ResponseEntity.ok(examples);
    }

    @GetMapping("/check-unique-roots")
    public ResponseEntity<List<Example>> getUniqueRoots() {
        List<Example> uniqueRootsAndExamples = exampleService.findUniqueRootsAndExamples();
        return ResponseEntity.ok(uniqueRootsAndExamples);
    }
    @GetMapping("allRootsAndExamples")
    public ResponseEntity<List<Example>> getAllExamples(){
        List<Example> allExample = exampleService.findAll();
        return ResponseEntity.ok(allExample);
    }

    @ExceptionHandler
    private ResponseEntity<ExampleErrorResponse> handleException(ExampleException e) {
        ExampleErrorResponse exampleErrorResponse = new ExampleErrorResponse(
                e.getMessage()
        );
        return new ResponseEntity<>(exampleErrorResponse, HttpStatus.BAD_REQUEST);

    }
}
