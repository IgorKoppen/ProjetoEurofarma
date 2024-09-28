package br.com.connectfy.EurofarmaCliente.dtos.security;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TokenVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String employeeRegistration;
    private String name;
    private List<String> roles;
    private Boolean authenticated;
    private Instant created;
    private Instant expiration;
    private String accessToken;
    private String refreshToken;
    private Long instructorId;

    public TokenVO() {
    }

    public TokenVO(Long id, String employeeRegistration, String name, List<String> roles, Boolean authenticated, Instant created, Instant expiration, String accessToken, String refreshToken, Long instructorId) {
        this.id = id;
        this.employeeRegistration = employeeRegistration;
        this.name = name;
        this.roles = roles;
        this.authenticated = authenticated;
        this.created = created;
        this.expiration = expiration;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.instructorId = instructorId;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmployeeRegistration() {
        return employeeRegistration;
    }

    public void setEmployeeRegistration(String employeeRegistration) {
        this.employeeRegistration = employeeRegistration;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getExpiration() {
        return expiration;
    }

    public void setExpiration(Instant expiration) {
        this.expiration = expiration;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenVO tokenVO = (TokenVO) o;
        return Objects.equals(employeeRegistration, tokenVO.employeeRegistration) && Objects.equals(authenticated, tokenVO.authenticated) && Objects.equals(created, tokenVO.created) && Objects.equals(expiration, tokenVO.expiration) && Objects.equals(accessToken, tokenVO.accessToken) && Objects.equals(refreshToken, tokenVO.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeRegistration, authenticated, created, expiration, accessToken, refreshToken);
    }

}
