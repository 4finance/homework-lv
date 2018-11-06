package io.fourfinanceit.homework.service;

import io.fourfinanceit.homework.model.dto.CustomerDto;
import io.fourfinanceit.homework.model.dto.LoanDto;
import io.fourfinanceit.homework.model.dto.TermDto;
import io.fourfinanceit.homework.model.entity.Customer;
import io.fourfinanceit.homework.model.entity.Loan;
import io.fourfinanceit.homework.repository.CustomerRepository;
import io.fourfinanceit.homework.repository.LoanRepository;
import io.fourfinanceit.homework.service.exceptions.BadRequestException;
import io.fourfinanceit.homework.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

	private final LoanExtensionService extensionService;
	private final CustomerRepository customerRepository;
	private final LoanRepository loanRepository;

	@Autowired
	public CustomerService(LoanExtensionService extensionService, CustomerRepository customerRepository, LoanRepository loanRepository) {
		this.extensionService = extensionService;
		this.customerRepository = customerRepository;
		this.loanRepository = loanRepository;
	}

	public void createCustomer(CustomerDto dto) {
		Optional<Customer> customer = customerRepository.findById(dto.getId());
		customer.ifPresent(c -> {
			throw new BadRequestException("Customer with that id already exists");
		});
		Customer newCustomer = Customer.builder().id(dto.getId()).name(dto.getName()).surname(dto.getSurname()).build();
		customerRepository.saveAndFlush(newCustomer);
	}

	public List<Loan> findLoans(Long customerId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		return customer.map(Customer::getLoans).orElseThrow(() -> new ResourceNotFoundException("Customer is not found"));
	}

	public void createLoan(Long customerId, LoanDto dto) {
		Customer customer = findCustomer(customerId);
		Loan loan = Loan.builder().customer(customer).amount(dto.getAmount()).term(dto.getTerm()).build();
		loanRepository.saveAndFlush(loan);
	}

	public void extendLoan(Long customerId, Long loanId, TermDto dto) {
		Customer customer = findCustomer(customerId);
		List<Loan> loans = customer.getLoans();
		Loan extendedLoan = loans.stream()
		                         .filter(loan -> loan.getId().equals(loanId))
		                         .findFirst()
		                         .orElseThrow(() -> new ResourceNotFoundException("Loan is not found"));
		extensionService.extendLoan(extendedLoan, dto.getTerm());
	}

	private Customer findCustomer(Long customerId) {
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		return optionalCustomer.orElseThrow(() -> new ResourceNotFoundException("Customer is not found"));
	}
}
