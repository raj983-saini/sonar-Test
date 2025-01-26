package com.example.LoginTest.serviceImpl;

import java.util.concurrent.*;

import com.example.LoginTest.Model.Resgister;
import org.springframework.stereotype.Component;

@Component
public class TemporaryUserStorage {

    private static final long TOKEN_EXPIRY_DURATION = 15 * 60 * 1000; // 15 minutes

    // ConcurrentMap with token and a wrapper for user and creation time
    private final ConcurrentMap<String, UserToken> userStorage = new ConcurrentHashMap<>();

    // Save a user with the associated token and timestamp
    public void saveUser(String token, Resgister user) {
        userStorage.put(token, new UserToken(user, System.currentTimeMillis()));
    }

    // Retrieve a user by token if not expired
    public Resgister getUser(String token) {
        UserToken userToken = userStorage.get(token);
        if (userToken != null && !isExpired(userToken)) {
            return userToken.getUser();
        }
        return null;
    }

    // Remove a user by token
    public void removeUser(String token) {
        userStorage.remove(token);
    }

    // Check if a token exists and is valid
    public boolean containsToken(String token) {
        UserToken userToken = userStorage.get(token);
        return userToken != null && !isExpired(userToken);
    }

    // Check if a token is expired
    private boolean isExpired(UserToken userToken) {
        return System.currentTimeMillis() - userToken.getCreationTime() > TOKEN_EXPIRY_DURATION;
    }

    // Scheduled cleanup task to remove expired tokens
    public TemporaryUserStorage() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::removeExpiredTokens, 1, 1, TimeUnit.MINUTES);
    }

    private void removeExpiredTokens() {
        long now = System.currentTimeMillis();
        userStorage.entrySet().removeIf(entry -> (now - entry.getValue().getCreationTime()) > TOKEN_EXPIRY_DURATION);
    }

    // Wrapper class for user and token creation time
    private static class UserToken {
        private final Resgister user;
        private final long creationTime;

        public UserToken(Resgister user, long creationTime) {
            this.user = user;
            this.creationTime = creationTime;
        }

        public Resgister getUser() {
            return user;
        }

        public long getCreationTime() {
            return creationTime;
        }
    }
}
