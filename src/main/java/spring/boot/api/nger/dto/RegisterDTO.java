package spring.boot.api.nger.dto;

import spring.boot.api.nger.enums.Role;

public record RegisterDTO(String login, String password, Role role) {
}
