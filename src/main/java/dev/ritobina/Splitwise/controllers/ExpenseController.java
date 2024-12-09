package dev.ritobina.Splitwise.controllers;

import dev.ritobina.Splitwise.dtos.ExpenseCreationReqDTO;
import dev.ritobina.Splitwise.models.Expense;
import dev.ritobina.Splitwise.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/expense")
    public ResponseEntity addExpense(@RequestBody ExpenseCreationReqDTO expenseCreationReqDTO){
        return ResponseEntity.ok(expenseService.createExpense(expenseCreationReqDTO));
    }

}
