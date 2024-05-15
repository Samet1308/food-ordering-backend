package com.ecommerce.foodordering.services.concretes;

import com.ecommerce.foodordering.dtos.requests.user.CreateUserRequest;
import com.ecommerce.foodordering.dtos.responses.user.GetAllUserResponse;
import com.ecommerce.foodordering.entities.User;
import com.ecommerce.foodordering.enums.UserRole;
import com.ecommerce.foodordering.repository.UserRepository;
import com.ecommerce.foodordering.services.abstracts.AuthService;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthManager implements AuthService {

    private final UserRepository userRepository;

    @Override
    public GetAllUserResponse createUser(CreateUserRequest createUserRequest) {
        User user = new User();
        user.setName(createUserRequest.getName());
        user.setEmail(createUserRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(createUserRequest.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);

        User createdUser = userRepository.save(user);
        GetAllUserResponse usersResponse = new GetAllUserResponse();
        usersResponse.setId(createdUser.getId());


        return usersResponse;

    }
//    @PostConstruct
//    public void createAdminAccount(){
//        User adminAccount = this.userRepository.findByUserRole(UserRole.ADMIN);
//        if(adminAccount == null){
//            User newAdminAccount = new User();
//            newAdminAccount.setName("admin");
//            newAdminAccount.setEmail("admin@test.com");
//            newAdminAccount.setPassword(new BCryptPasswordEncoder().encode("admin"));
//            newAdminAccount.setUserRole(UserRole.ADMIN);
//
//            userRepository.save(newAdminAccount);
//            System.out.println("Admin oluşturuldu.");
//        }
//    }


}
