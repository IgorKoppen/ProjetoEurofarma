package br.com.connectfy.EurofarmaCliente.dtos.security;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TokenVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String name;
    private List<String> roles;
    private Boolean authenticated;
    private Date created;
    private Date expiration;
    private String accessToken;
    private String refreshToken;
    private Long instructorId;

    public TokenVO() {
    }

    public TokenVO(Long id, String username, String name, List<String> roles, Boolean authenticated, Date created, Date expiration, String accessToken, String refreshToken, Long instructorId) {
        this.id = id;
        this.username = username;
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
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
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
        return Objects.equals(username, tokenVO.username) && Objects.equals(authenticated, tokenVO.authenticated) && Objects.equals(created, tokenVO.created) && Objects.equals(expiration, tokenVO.expiration) && Objects.equals(accessToken, tokenVO.accessToken) && Objects.equals(refreshToken, tokenVO.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, authenticated, created, expiration, accessToken, refreshToken);
    }

}
