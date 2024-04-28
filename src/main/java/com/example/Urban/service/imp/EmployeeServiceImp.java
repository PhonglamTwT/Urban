package com.example.Urban.service.imp;

import com.example.Urban.dto.EmployeeAccountDTO;
import com.example.Urban.entity.AccountEntity;
import com.example.Urban.entity.EmployeeEntity;
import com.example.Urban.repository.AccountRepository;
import com.example.Urban.repository.EmployeeRepository;
import com.example.Urban.service.EmployeeService;
import com.example.Urban.service.FileStorageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.lang.ref.SoftReference;
import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Override
    public List<EmployeeEntity> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public List<AccountEntity> getAllAccount() {
        return accountRepository.findAll();
    }


//    @Override
//    public Boolean updateEmployee(int employeeId, EmployeeAccountDTO employeeAccountDTO) {
//        EmployeeEntity employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new RuntimeException("Employee not found"));
//
//        employee.setImage(employeeAccountDTO.getImage());
//        employee.setName(employeeAccountDTO.getName());
//        employee.setEmail(employeeAccountDTO.getEmail());
//        employee.setPhone(employeeAccountDTO.getPhone());
//        employee.setGender(employeeAccountDTO.getGender());
//        employee.setAddress(employeeAccountDTO.getAddress());
//        employee.setPosition(employeeAccountDTO.getPosition());
//        employee.setHeadquarter(employeeAccountDTO.getHeadquarter());
//
//        AccountEntity account = employee.getAccount();
//        account.setUsername(employeeAccountDTO.getUsername());
//        account.setPassword(employeeAccountDTO.getPassword());
//        account.setRole(employeeAccountDTO.getRole());
//        try{
//            employeeRepository.save(employee);
//            accountRepository.save(account);
//            return true;
//        }
//        catch (Exception e){
//            System.out.println("ManagerServiceImp updateEmployee Error: " + e.getLocalizedMessage());
//            return false;
//        }
//    }

    @Override
    public Boolean updateEmployee(int employeeId, MultipartFile file, String name, String email, String phone, String gender, String address,
                                  String position, String headquarter, String username, String password, String role) {
        fileStorageService.save(file);
        EmployeeEntity employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        String existPhoto = employee.getImage();
        if(!existPhoto.equals(file.getOriginalFilename())){
            fileStorageService.deleleEmployeePhoto(existPhoto);
        }
        employee.setImage(file.getOriginalFilename());
        employee.setName(name);
        employee.setEmail(email);
        employee.setPhone(phone);
        employee.setGender(gender);
        employee.setAddress(address);
        employee.setPosition(position);
        employee.setHeadquarter(headquarter);
        AccountEntity account = employee.getAccount();
        account.setUsername(username);
        account.setPassword(password);
        account.setRole(role);
        try{
            employeeRepository.save(employee);
            accountRepository.save(account);
            return true;
        }
        catch (Exception e){
            System.out.println("ManagerServiceImp updateEmployee Error: " + e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public Boolean deleteEmployee(int employeeId) {
        try{
            EmployeeEntity employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
            AccountEntity account = employee.getAccount();
            accountRepository.deleteById(account.Getid());
            fileStorageService.deleleEmployeePhoto(employee.getImage());
            employeeRepository.deleteById(employeeId);
            return true;
        }catch (Exception e){
            System.out.println("EmployeeService deleteEmployee Error: "+e.getLocalizedMessage());
            return false;
        }
    }

    @Transactional
    public EmployeeEntity createEmployeeAndAccount(EmployeeAccountDTO employeeAccountDTO){

        EmployeeEntity employee = new EmployeeEntity();
        employee.setImage(employeeAccountDTO.getImage());
        employee.setName(employeeAccountDTO.getName());
        employee.setEmail(employeeAccountDTO.getEmail());
        employee.setPhone(employeeAccountDTO.getPhone());
        employee.setGender(employeeAccountDTO.getGender());
        employee.setAddress(employeeAccountDTO.getAddress());
        employee.setPosition(employeeAccountDTO.getPosition());
        employee.setHeadquarter(employeeAccountDTO.getHeadquarter());
        employeeRepository.save(employee);

        AccountEntity account = new AccountEntity();
        account.setUsername(employeeAccountDTO.getUsername());
        account.setPassword(employeeAccountDTO.getPassword());
        account.setRole(employeeAccountDTO.getRole());
        account.setEmployee(employee);
        accountRepository.save(account);

        return employee;

    }
}
