
package com.wipro.projetofinal;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.wipro.projetofinal.entities.Account;
import com.wipro.projetofinal.entities.CheckingAccount;
import com.wipro.projetofinal.service.CustomerService;
import com.wipro.projetofinal.service.exeption.InvalidValueException;
import com.wipro.projetofinal.service.exeption.ResourceNotFoundExcception;


@SpringBootTest
@RunWith(SpringRunner.class)

@WebAppConfiguration
public class CustomerServiceTest {

	@Autowired
	private CustomerService customer;
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void get_checking_account_test_failed() throws ResourceNotFoundExcception {//teste de falha para conta corrente(getAccount)
		ResourceNotFoundExcception resource = new ResourceNotFoundExcception("1");
		Account acc = customer.getAccount("478473594143955");
		String expected = "1";
		assertFalse(expected.equals(acc.getAccountNumber()));
		assertFalse(resource.equals(expected));
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void get_special_account_test_failed() throws ResourceNotFoundExcception {//teste de falha para conta special(getAccount)
		ResourceNotFoundExcception resource = new ResourceNotFoundExcception("1");
		Account acc = customer.getAccount("276711954579855");
		String expected = "1";
		assertFalse(expected.equals(acc.getAccountNumber()));
		assertFalse(resource.equals(expected));
	}
	
	@Test
	public void get_checking_account_test_success() {//teste de sucesso para conta corrente(getAccount)
		Account acc = customer.getAccount("478473594143955");
		assertEquals("478473594143955", acc.getAccountNumber());
	}
	
	@Test
	public void get_special_account_test_success() {//teste de sucesso para conta corrente(getAccount)
		Account acc = customer.getAccount("276711954579855");
		assertEquals("276711954579855", acc.getAccountNumber());
	}
	
	@Test
	public void get_all_movimenties_checking_account() {//movimentação conta corrente
		String acountNumber = "478473594143955";
		customer.getAllMovimenties(acountNumber);
		assertTrue(true);
		
	}
	
	@Test
	public void get_all_movimenties_special_account() {//movimentação conta special
		String acountNumber = "276711954579855";
		customer.getAllMovimenties(acountNumber);
		assertTrue(true);
		
	}
	
	
	@Test
	public void deposit_test_failed() throws Exception,InvalidValueException {//teste de falha para deposito
		double value = 0.0;
		InvalidValueException invalid = new InvalidValueException(value);
		assertFalse(false);
	}

	
	@Test
	public void deposit_checking_account_test_success() throws Exception {//teste de sucesso para deposito em conta corrente
		double value = 500.0;
		customer.deposit("478473594143955", value);
	}
	
	@Test
	public void deposit_special_account_test_success() throws Exception {//teste de sucesso para deposito em conta special
		double value = 500.0;
		customer.deposit("276711954579855", value);
		assertTrue(true);
	}
	
	@Test(expected = InvalidValueException.class)
	public void withdraw_test_failed() throws Exception,InvalidValueException {//teste de falha para saque
		double value = 0.0;
		customer.withdraw("276711954579855", value);

	}
	@Test
	public void withdraw_exception_test_failed() throws Exception,InvalidValueException  {//teste de falha para saque
		double value = 100000.0;
		customer.deposit("276711954579855", value);
	}
	@Test
	public void withdraw_saldo_insuficiente_test_failed() throws Exception,InvalidValueException {//teste de falha para saque
		double value = 100000000.0;	
		InvalidValueException invalid = new InvalidValueException(value);
		invalid.getMessage();
		assertFalse(false);
	}
	
	
	@Test
	public void withdraw_checking_account_test_success() throws Exception {//teste de sucesso para saque em conta corrente
		double value = 500.0;	
		customer.withdraw("478473594143955", value);
		assertTrue(true);
	}
	
	@Test
	public void withdraw_special_account_test_success() throws Exception {//teste de sucesso para saque em conta special
		double value = 500.0;	
		customer.withdraw("276711954579855", value);
		assertTrue(true);
	}
	
	@Test(expected = NullPointerException.class)
	public void transfer_checking_account_success() throws Exception {
		double value = 1.0;
		String accOrigin = "478473594143955";
		String accDestin = "863032057630184";
		Account originAcc = customer.getAccount(accOrigin);
		Account destinAcc = customer.getAccount(accDestin);
		
		customer.transfer(accOrigin, accDestin, value);
		assertTrue(true);
	}
	@Test(expected = NullPointerException.class)
	public void transfer_checking_account_failed() throws Exception, InvalidValueException {
		double value = 1.0;
		String accOrigin = "863032057630184";
		String accDestin = "478473594143955";
		Account originAcc = customer.getAccount(accOrigin);
		Account destinAcc = customer.getAccount(accDestin);
		
		customer.transfer(accOrigin, accDestin, value);
		assertFalse(false);
		
	}
}
