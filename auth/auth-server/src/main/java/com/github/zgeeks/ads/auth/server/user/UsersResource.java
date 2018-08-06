package com.github.zgeeks.ads.auth.server.user;

import org.springframework.stereotype.Controller;

public class UsersResource {

//    private final UserRepository userRepository;
//
//    public UsersResource(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public User getUser() {
//        return userRepository.findById(getUserId())
//            .orElseThrow(() -> new UserDoesNotExistException(getUserId()));
//    }
//
//    @Override
//    public void updateUser(UserUpdateRequest updateRequest) throws UserDoesNotExistException {
//        updateRequest.getOptionalEmail().ifPresent(email -> userRepository.updateEmail(getUserId(), email));
//        updateRequest.getOptionalPassword().ifPresent(
//            password -> userRepository.updatePassword(getUserId(), password, updateRequest.getSalt(), updateRequest.getAlgorithmEnum())
//        );
//    }
//
//    @Override
//    public Response addUser(UserCreationRequest userReq) throws UserAlreadyExistsException {
//
//        HashAlgorithm digest = userReq.getAlgorithmEnum();
//        if (!(digest.validateSalt(userReq.getSalt()) && digest.validatePassword(userReq.getPassword()))) {
//            throw WebApplicationExceptions.badRequest("Password and salt must be base64-encoded");
//        }
//
//        final User user = userRepository.create(
//            userReq.getEmail(),
//            userReq.getPassword(),
//            userReq.getSalt(),
//            digest);
//
//        return created(fromUri("/users/me").build()).entity(user).type(APPLICATION_JSON).build();
//    }
//
//    @Override
//    public void removeUser() throws UserDoesNotExistException {
//        userRepository.delete(getUserId());
//    }
//
//    @Override
//    public User findByMail(final String email) {
//        return userRepository.findByEmail(email).orElseThrow(() -> new UserDoesNotExistException(email));
//    }
//
//    private String getUserId() {
//        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    }
}
