package com.github.zgeeks.ads.auth.server.user;

public class UserRepository {

//    private static final Logger LOG = LoggerFactory.getLogger(UserRepository.class);
//
//    private final Clock clock;
//    private final PasswordEncoder passwordEncoder;
//
//    public UserRepository(Clock clock, PasswordEncoder passwordEncoder) {
//        this.clock = clock;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public JwtUser create(String email, String encodedPassword) throws UserAlreadyExistsException {
//
//        final String id = UUID.randomUUID().toString();
//
//        try {
//            LdapUser.createUser(id)
//                .attr(LdapUser.ATTR_EMAIL, email)
//                .attr(LdapUser.ATTR_CREDENTIAL, encodedPassword.getBytes(UTF_8))
//                .withContext(ctx -> ldapTemplate.bind(ctx.getDn(), ctx, null));
//        } catch (NameAlreadyBoundException ex) {
//            throw new UserAlreadyExistsException(email);
//        } catch (InvalidAttributeValueException ex) {
//            // Looking at com.sun.jndi.ldap.LdapCtx.mapErrorCode this exception matches LDAP error code 19 or 21
//            // (See http://wiki.servicenow.com/index.php?title=LDAP_Error_Codes
//            // With a high probability, this is a unique constraint violation
//            throw new UserAlreadyExistsException(email);
//        }
//
//        return User.builder()
//            .id(id)
//            .email(email)
//            .build();
//    }
//
//    public User create(String email, String password, String salt, HashAlgorithm algorithm) {
//        String encodedPassword = encodePassword(password, salt, algorithm);
//        return create(email, encodedPassword);
//    }
//
//    private String encodePassword(String password, String salt, HashAlgorithm algorithm) {
//        String encodedPassword;
//
//        if (algorithm == HashAlgorithm.NONE) {
//            encodedPassword = passwordEncoder.encode(password);
//        } else {
//            byte[] passwordBytes = Base64.decodeBase64(password);
//            byte[] saltBytes = Optional.ofNullable(salt).map(Base64::decodeBase64).orElse(new byte[0]);
//
//            PasswordDigestFormatter digestFormatter = new PasswordDigestFormatter(algorithm, passwordBytes.length);
//            encodedPassword = digestFormatter.format(passwordBytes, saltBytes);
//        }
//        return encodedPassword;
//    }
//
//    public void updateEmail(String id, String email) {
//        try {
//            DirContext.fromContext(ldapTemplate.lookupContext(LdapUser.dn(id)))
//                .attr(LdapUser.ATTR_EMAIL, email)
//                .withContext(ldapTemplate::modifyAttributes);
//        } catch (InvalidAttributeValueException ex) {
//            // Looking at com.sun.jndi.ldap.LdapCtx.mapErrorCode this exception matches LDAP error code 19 or 21
//            // (See http://wiki.servicenow.com/index.php?title=LDAP_Error_Codes
//            // With a high probability, this is a unique constraint violation
//            throw new UserAlreadyExistsException(email);
//        }
//    }
//
//    public void updatePassword(String id, String credentials) {
//        updatePassword(id, credentials, null, HashAlgorithm.NONE);
//    }
//
//    public void updatePassword(String id, String password, String salt, HashAlgorithm algorithm) {
//        String encodedPassword = encodePassword(password, salt, algorithm);
//        DirContext.fromContext(ldapTemplate.lookupContext(LdapUser.dn(id)))
//            .attr(LdapUser.ATTR_CREDENTIAL, encodedPassword.getBytes(UTF_8))
//            .withContext(ldapTemplate::modifyAttributes);
//    }
//
//    public void prepareResetPassword(String id, String resetCode, Duration validity) {
//
//        DirContext.fromContext(ldapTemplate.lookupContext(LdapUser.dn(id)))
//            .attr(LdapUser.ATTR_RESETCODE, resetCode)
//            .attr(
//                LdapUser.ATTR_RESETCODE_EXPIRATION,
//                LdapUtils.TIMESTAMP_FORMATTER.format(ZonedDateTime.now(clock).plus(validity)))
//            .withContext(ldapTemplate::modifyAttributes);
//    }
//
//    public void resetPassword(String id, String resetCode, String encodedPassword) {
//
//        final ContainerCriteria query = LdapQueryBuilder.query()
//            .base(LdapUser.dn(id))
//            .where(LdapUser.ATTR_RESETCODE_EXPIRATION).gte(LdapUtils.TIMESTAMP_FORMATTER.format(ZonedDateTime.now(clock)))
//            .and(LdapUser.ATTR_RESETCODE).is(resetCode);
//
//        try {
//            DirContext.fromContext(ldapTemplate.searchForContext(query))
//                .attr(LdapUser.ATTR_RESETCODE, null)
//                .attr(LdapUser.ATTR_RESETCODE_EXPIRATION, null)
//                .attr(LdapUser.ATTR_CREDENTIAL, encodedPassword.getBytes(UTF_8))
//                .withContext(ldapTemplate::modifyAttributes);
//        } catch (IncorrectResultSizeDataAccessException ex) {
//            throw new InvalidResetCodeException();
//        }
//    }
//
//    public void delete(String id) {
//        ldapTemplate.unbind(LdapUser.dn(id));
//    }
//
//    public Optional<User> findByEmail(String email) {
//        try {
//            ContainerCriteria query = LdapQueryBuilder.query().where(LdapUser.ATTR_EMAIL).is(email);
//            return Optional.of(ldapTemplate.searchForObject(query, LdapUser.contextMapper()));
//        } catch (IncorrectResultSizeDataAccessException ex) {
//            LOG.debug("IncorrectResultSizeDataAccessException", ex);
//            return Optional.empty();
//        }
//    }
//
//    public Optional<User> findById(String id) {
//        try {
//            return Optional.of(ldapTemplate.lookup(LdapUser.dn(id), LdapUser.contextMapper()));
//        } catch (NameNotFoundException ex) { // NOSONAR rule: Exception handlers should preserve the original exception
//            return Optional.empty();
//        }
//    }
//
//    private static final class LdapUtils {
//
//        static final DateTimeFormatter TIMESTAMP_FORMATTER = new DateTimeFormatterBuilder()
//            .parseCaseInsensitive()
//            .appendValue(YEAR, 4)
//            .appendValue(MONTH_OF_YEAR, 2)
//            .appendValue(DAY_OF_MONTH, 2)
//            .appendValue(HOUR_OF_DAY, 2)
//            .appendValue(MINUTE_OF_HOUR, 2)
//            .appendValue(SECOND_OF_MINUTE, 2)
//            .appendZoneId()
//            .toFormatter();
//
//        private LdapUtils() {
//            // sonar
//        }
//    }
}
