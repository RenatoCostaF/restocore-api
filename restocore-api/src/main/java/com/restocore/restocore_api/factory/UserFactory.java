package com.restocore.restocore_api.factory;

import com.restocore.restocore_api.entity.Address;
import com.restocore.restocore_api.entity.User;
import com.restocore.restocore_api.enums.UserType;
import com.restocore.restocore_api.shared.TextNormalizer;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    private final TextNormalizer textNormalizer;

    public UserFactory(TextNormalizer textNormalizer) {
        this.textNormalizer = textNormalizer;
    }

    public User create(
            String name,
            String email,
            String login,
            String password,
            UserType userType,
            Address address
    ) {
        User user = new User();
        user.setName(textNormalizer.normalizeToLower(name));
        user.setEmail(textNormalizer.normalizeToLowerTrim(email));
        user.setLogin(textNormalizer.normalizeToLowerTrim(login));
        user.setPassword(password);
        user.setUserType(userType);
        user.setAddress(address);
        return user;
    }


}
