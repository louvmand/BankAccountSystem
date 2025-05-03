package org.example.account;

import jakarta.persistence.EntityManager;
import org.example.account.domain.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private AccountService accountService;

    private Account testAccount;

    @BeforeEach
    void setUp() {
        testAccount = new Account("ACC123", new BigDecimal("1000"));
    }

    @Test
    void createAccount_ShouldPersistAccount() {
        accountService.createAccount(testAccount);

        verify(entityManager).persist(testAccount);
    }

    @Test
    void getAccount_ShouldReturnAccount_WhenAccountExists() {
        when(entityManager.find(Account.class, "ACC123")).thenReturn(testAccount);

        Account result = accountService.getAccount("ACC123");

        assertNotNull(result);
        assertEquals("ACC123", result.getAccountNumber());
        assertEquals(new BigDecimal("1000"), result.getBalance());
    }

    @Test
    void getBalance_ShouldReturnBalance_WhenAccountExists() {
        when(entityManager.find(Account.class, "ACC123")).thenReturn(testAccount);

        BigDecimal balance = accountService.getBalance("ACC123");

        assertEquals(new BigDecimal("1000"), balance);
    }

    @Test
    void getBalance_ShouldReturnZero_WhenAccountDoesNotExist() {
        when(entityManager.find(Account.class, "NONEXISTENT")).thenReturn(null);

        BigDecimal balance = accountService.getBalance("NONEXISTENT");

        assertEquals(BigDecimal.ZERO, balance);
    }
}