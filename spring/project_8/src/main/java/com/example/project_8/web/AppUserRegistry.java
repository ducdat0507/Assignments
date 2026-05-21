package com.example.project_8.web;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AppUserRegistry {

    private final ConcurrentHashMap<String, String> sessionIdToUser = new ConcurrentHashMap<>();

    public void register(String sessionId, String username) {
        sessionIdToUser.put(sessionId, username);
    }

    public void unregister(String sessionId) {
        sessionIdToUser.remove(sessionId);
    }

    public Set<String> getUsers() {
        return Collections.unmodifiableSet(Collections.newSetFromMap(new ConcurrentHashMap<>()));
    }

    public Set<String> usernames() {
        return Collections.unmodifiableSet(Set.copyOf(sessionIdToUser.values()));
    }

}
