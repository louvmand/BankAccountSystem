package org.example.account;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.inject.Inject;
import org.example.account.domain.Account;

import java.math.BigDecimal;

@ApplicationScoped
public class AccountService {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void createAccount(Account account) {
        entityManager.persist(account);
    }

    public Account getAccount(String accountNumber) {
        return entityManager.find(Account.class, accountNumber);
    }

    public BigDecimal getBalance(String accountNumber) {
        Account account = entityManager.find(Account.class, accountNumber);
        return account != null ? account.getBalance() : BigDecimal.ZERO;
    }
}