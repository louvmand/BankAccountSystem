package org.example.transfer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.example.account.domain.Account;

import java.math.BigDecimal;

@ApplicationScoped
public class TransferService {

    @Inject
    EntityManager entityManager;

    public TransferService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void deposit(String accountNumber, BigDecimal amount) {
        Account account = entityManager.find(Account.class, accountNumber);
        if (account != null) {
            account.setBalance(account.getBalance().add(amount));
        }
    }

    @Transactional
    public void transfer(String fromAccount, String toAccount, BigDecimal amount) {
        Account from = entityManager.find(Account.class, fromAccount);
        Account to = entityManager.find(Account.class, toAccount);

        if (isValidTransfer(from, to, amount)) {
            from.setBalance(from.getBalance().subtract(amount));
            to.setBalance(to.getBalance().add(amount));
        } else {
            throw new IllegalArgumentException("Invalid transfer request");
        }
    }

    private boolean isValidTransfer(Account from, Account to, BigDecimal amount) {
        return from != null && to != null && hasSufficientBalance(from, amount);
    }

    private boolean hasSufficientBalance(Account from, BigDecimal amount) {
        return from.getBalance().compareTo(amount) >= 0;
    }
}