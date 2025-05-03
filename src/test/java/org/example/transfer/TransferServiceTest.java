package org.example.transfer;

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
public class TransferServiceTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private TransferService transferService;

    private Account account1;
    private Account account2;

    @BeforeEach
    void setUp() {
        account1 = new Account("ACC1", new BigDecimal("1000"));
        account2 = new Account("ACC2", new BigDecimal("500"));
    }

    @Test
    void deposit_ShouldIncreaseBalance_WhenAccountExists() {
        when(entityManager.find(Account.class, "ACC1")).thenReturn(account1);

        transferService.deposit("ACC1", new BigDecimal("100"));

        assertEquals(new BigDecimal("1100"), account1.getBalance());
        verify(entityManager).find(Account.class, "ACC1");
    }

    @Test
    void transfer_ShouldMoveAmount_WhenBothAccountsExistAndSufficientFunds() {
        when(entityManager.find(Account.class, "ACC1")).thenReturn(account1);
        when(entityManager.find(Account.class, "ACC2")).thenReturn(account2);

        transferService.transfer("ACC1", "ACC2", new BigDecimal("300"));

        assertEquals(new BigDecimal("700"), account1.getBalance());
        assertEquals(new BigDecimal("800"), account2.getBalance());
    }

    @Test
    void transfer_ShouldThrowException_WhenInsufficientFunds() {
        when(entityManager.find(Account.class, "ACC1")).thenReturn(account1);
        when(entityManager.find(Account.class, "ACC2")).thenReturn(account2);

        assertThrows(IllegalArgumentException.class, () ->
                transferService.transfer("ACC1", "ACC2", new BigDecimal("2000"))
        );
    }
}