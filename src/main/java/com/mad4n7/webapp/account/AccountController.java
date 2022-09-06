package com.mad4n7.webapp.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @PostMapping
    public void registerNewAccount(@RequestBody Account account) {
        accountService.addNewAccount(account);
    }

    @DeleteMapping(path = "{accountId}")
    public void deleteAccount(@PathVariable("accountId") Long accountId) {
        accountService.deleteAccount(accountId);
    }

    @PutMapping(path = "{accountId}")
    public void updateAccount(
        @PathVariable("accountId") Long accountId,
        @RequestParam(required = false) String username,
        @RequestParam(required = false) String password,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) LocalDate dateOfBirth
    ) {
        accountService.updateAccount(accountId, username, password, email, dateOfBirth);
    }
}
