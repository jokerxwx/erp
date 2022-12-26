package person.xwx.erpserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import person.xwx.erpserver.filter.JwtAuthnenticationTokenFilter;
import person.xwx.erpserver.handler.AuthenticationEntryPointImpl;


/**
 * @author: xwx
 * @date: 2022-11-30  15:33
 * @Description: TODO
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private JwtAuthnenticationTokenFilter jwtAuthnenticationTokenFilter;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    /**
     * 密码明文加密方式配置
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 获取AuthenticationManager（认证管理器），登录时认证使用
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // 基于 token，不需要 csrf
                .csrf().disable()
                .cors().and()
                // 基于 token，不需要 session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 设置 jwtAuthError 处理认证失败、鉴权失败
//                .exceptionHandling().authenticationEntryPoint(jwtAuthError).accessDeniedHandler(jwtAuthError).and()
                // 下面开始设置权限
                .authorizeRequests(authorize -> authorize
                        // 请求放开
                        .antMatchers("/api/login","/user/register").anonymous()
                        .antMatchers("/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs").permitAll()
                        // 其他地址的访问均需验证权限
                        .anyRequest().authenticated()
                )
//                // 添加 JWT 过滤器，JWT 过滤器在用户名密码认证过滤器之前
                .addFilterBefore(jwtAuthnenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
//                // 认证用户时用户信息加载配置，注入springAuthUserService
//                .userDetailsService(xxxAuthUserService)
//                .logout().logoutUrl("/logout").clearAuthentication(true).and()
                //配置异常处理器
                .exceptionHandling()
                    //认证失败
                    .authenticationEntryPoint(authenticationEntryPoint)
                    //授权失败
                    .accessDeniedHandler(accessDeniedHandler).and()
                .build();
    }


    /**
     * 配置跨源访问(CORS)
     * @return
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
