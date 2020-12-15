package model.domain;











import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * QQ用户实体类
 */

public class User implements UserDetails {
    /** 用户ID */
    private Long id;
    /** 用户账号 */
    private String username;
    /** 账号密码 */
    private String password;

    private List<Role> roles;

    private Collection<? extends GrantedAuthority> authorities;
    /** 用户昵称 */
    //private String nickname;
    /** 用户姓名 */
    //private String name;
    /** 用户头像 */
    //private String photo;
    /** 用户性别 */
    //private Boolean gender;
    /** 出生日期 */
    //private Date birthday;
    /** 所属于国家*/
    //private String country;
    /** 所属于城市 */
    //private String city;
    /** 手机号码 */
    //private String mobile;
    /** 电子邮箱 */
    //private String email;
    /** 用户备注 */
    //private String description;
    /** 用户状态 */
    //private Boolean status;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isAccountNonExpired() {
        return false;
    }

    public boolean isAccountNonLocked() {
        return false;
    }

    public boolean isCredentialsNonExpired() {
        return false;
    }

    public boolean isEnabled() {
        return false;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
    /*public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }*/
}
