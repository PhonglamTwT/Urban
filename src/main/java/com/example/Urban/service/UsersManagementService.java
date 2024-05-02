package com.example.Urban.service;

import com.example.Urban.dto.EmployeeAccountDTO;
import com.example.Urban.dto.ReqRes;
import com.example.Urban.entity.AccountEntity;
import com.example.Urban.entity.EmployeeEntity;
import com.example.Urban.repository.AccountRepository;
import com.example.Urban.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UsersManagementService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private EmployeeRepository employeeEntity;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmployeeRepository employeeRepository;
    public ReqRes addEmployeeAndAccountJwt(ReqRes createaccountRequest){
        ReqRes resp = new ReqRes();
        try {
            EmployeeEntity emp = new EmployeeEntity();
            emp.setHeadquarter(createaccountRequest.getHeadquarter());
            emp.setName(createaccountRequest.getName());
            emp.setPosition(createaccountRequest.getPosition());
            EmployeeEntity employeeResult = employeeEntity.save(emp);

            AccountEntity account = new AccountEntity();
            account.setUsername(createaccountRequest.getUsername());
            account.setRole(createaccountRequest.getRole());
            account.setPassword(passwordEncoder.encode(createaccountRequest.getPassword()));
            account.setEmployee(employeeResult);
            AccountEntity accountResult = accountRepository.save(account);
            if (accountResult.Getid() > 0 && employeeResult.Getid() > 0) {
                resp.setAccount((accountResult));
                resp.setEmployee(employeeResult);
                resp.setMessage("User Saved Successfully");
                resp.setStatusCode(200);
            }
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }
    public ReqRes loginJwt(ReqRes loginRequest) {
        ReqRes response = new ReqRes();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            var user = accountRepository.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRole(user.getRole());
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setMessage("Successfully Logged In");
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred: " + e.getMessage());
        }
        return response;
    }
    public ReqRes getAllEmployeeJwt() {
        ReqRes reqRes = new ReqRes();
        try {
            List<AccountEntity> result = accountRepository.findAll();
            if (!result.isEmpty()) {
                reqRes.setAccountList(result);
                reqRes.setStatusCode(200);
                reqRes.setMessage("Successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("No users found");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
        }
        return reqRes;
    }
    public ReqRes deleteEmployeeJwt(int employeeId) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<EmployeeEntity> empOptional = employeeRepository.findById(employeeId);
            if (empOptional.isPresent()) {
                EmployeeEntity employee = empOptional.get();
                AccountEntity account = employee.getAccount();
                accountRepository.delete(account);
                employeeRepository.delete(employee);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User deleted successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for deletion");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while deleting user: " + e.getMessage());
        }
        return reqRes;
    }
    public ReqRes updateEmployeeJwt(int employeeId, EmployeeAccountDTO employeeAccountDTO) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<EmployeeEntity> empOptional = employeeRepository.findById(employeeId);
            if (empOptional.isPresent()) {
                EmployeeEntity employee = empOptional.get();
                employee.setName(employeeAccountDTO.getName());
                employee.setPosition(employeeAccountDTO.getPosition());
                employee.setHeadquarter(employeeAccountDTO.getHeadquarter());

                AccountEntity existingAccount = employee.getAccount();
                existingAccount.setUsername(employeeAccountDTO.getUsername());
                existingAccount.setRole(employeeAccountDTO.getRole());
                String newPassword = employeeAccountDTO.getPassword();
                if (newPassword != null && !newPassword.isEmpty()) {
                    existingAccount.setPassword(passwordEncoder.encode(newPassword));
                }
                EmployeeEntity saveEmp = employeeRepository.save(employee);
                AccountEntity savedAcount = accountRepository.save(existingAccount);
                reqRes.setEmployee(saveEmp);
                reqRes.setAccount(savedAcount);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User updated successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while updating user: " + e.getMessage());
        }
        return reqRes;
    }
}
