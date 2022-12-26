package person.xwx.erpserver.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import person.xwx.erpserver.entity.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: xwx
 * @date: 2022-12-07  20:13
 * @Description: 用于权限认证和授权一块，存放于SecurityContext中
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"enabled","accountNonExpired", "accountNonLocked", "credentialsNonExpired", "authorities"})
public class UserDTO implements UserDetails, Serializable {

    private User user;

    private List<String> permissions;

    @JsonIgnore
    private List<SimpleGrantedAuthority> authorities;

    public UserDTO(User user, List<String> list) {
        this.user = user;
        this.permissions = list;
    }

    /**
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //把permissions中的String权限封装成SimpleGrantedAuthority对象
        if ( authorities != null ) {
            return authorities;
        }
        authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    /**
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @return
     */
    @Override
    @JsonIgnore
    public String getPassword() {
        return this.user.getPassword();
    }

    /**
     * @return
     */
    @Override
    @JsonIgnore
    public String getUsername() {
        return this.user.getUsername();
    }


    /**
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
