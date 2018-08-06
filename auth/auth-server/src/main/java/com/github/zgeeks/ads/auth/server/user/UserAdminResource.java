package com.github.zgeeks.ads.auth.server.user;

public class UserAdminResource  {
//
//    private final UserRepository userRepository;
//
//    public UserAdminResource(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public User getUser(String uid) {
//        return userRepository.findById(uid)
//            .orElseThrow(() -> new UserDoesNotExistException(uid));
//    }
//
//    @Override
//    public void updateUser(String uid, UserUpdateRequest updateRequest) throws UserDoesNotExistException {
//        updateRequest.getOptionalEmail().ifPresent(email -> userRepository.updateEmail(uid, email));
//        updateRequest.getOptionalPassword().ifPresent(
//            password -> userRepository.updatePassword(uid, password, updateRequest.getSalt(), updateRequest.getAlgorithmEnum())
//        );
//    }
//
//    @Override
//    public void removeUser(String uid) throws UserDoesNotExistException {
//        userRepository.delete(uid);
//    }
//
//    @Override
//    public User findByMail(final String email) {
//        return userRepository.findByEmail(email).orElseThrow(() -> new UserDoesNotExistException(email));
//    }
}
