package net.youssfi.accountservice.web;

import net.youssfi.accountservice.entities.BankAccount;
import net.youssfi.accountservice.repository.BankAccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountRestController {
    private final BankAccountRepository accountRepository;

    public AccountRestController(BankAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Retrieve all bank accounts
    @GetMapping
    public List<BankAccount> getAllAccounts(){
        return accountRepository.findAll();
    }

    // Retrieve a bank account by ID
    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getAccountById(@PathVariable String id){
        Optional<BankAccount> accountOptional = accountRepository.findById(id);
        return accountOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new bank account
    @PostMapping
    public ResponseEntity<BankAccount> createAccount(@RequestBody BankAccount account){
        BankAccount createdAccount = accountRepository.save(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    // Update an existing bank account
    @PutMapping("/{id}")
    public ResponseEntity<BankAccount> updateAccount(@PathVariable String id, @RequestBody BankAccount updatedAccount){
        Optional<BankAccount> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            updatedAccount.setAccountId(id); // Ensure ID consistency
            BankAccount savedAccount = accountRepository.save(updatedAccount);
            return ResponseEntity.ok(savedAccount);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a bank account
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable String id){
        Optional<BankAccount> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            accountRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
