package com.mad4n7.webapp.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public void addNewAccount(Account account) {
        accountRepository.findAccountByEmail(account.getEmail())
            .ifPresentOrElse(
                (account1) -> {
                    throw new IllegalStateException("The email is already in use");
                },
                () -> {
                    accountRepository.save(account);
                }
            );
        System.out.println(account);
    }

    public void deleteAccount(Long accountId) {
        boolean exists = accountRepository.existsById(accountId);
        if (!exists) {
            throw new IllegalStateException("Account with id " + accountId + " does not exists");
        }
        accountRepository.deleteById(accountId);
    }

    @Transactional
    public void updateAccount(Long accountId, String username, String password, String email, LocalDate dateOfBirth) {
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalStateException("Account with id " + accountId + " does not exists"));

        if (username != null && username.length() > 0 && !account.getUsername().equals(username)) {
            account.setUsername(username);
        }

        if (password != null && password.length() > 0 && !account.getPassword().equals(password)) {
            account.setPassword(password);
        }

        if (email != null && email.length() > 0 && !account.getEmail().equals(email)) {
            Optional<Account> accountOptional = accountRepository.findAccountByEmail(email);
            if (accountOptional.isPresent()) {
                throw new IllegalStateException("The email is already in use");
            }

            account.setEmail(email);
        }

        if (dateOfBirth != null && !account.getDateOfBirth().equals(dateOfBirth)) {
            account.setDateOfBirth(dateOfBirth);
        }

        if (username != null && username.length() > 0 && !account.getUsername().equals(username)) {
            account.setUsername(username);
        }

    }
}
