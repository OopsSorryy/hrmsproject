package hrms.hrms.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import javax.servlet.http.HttpServletResponse;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenFilter jwtTokenFilter;

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(JwtTokenFilter jwtTokenFilter,UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenFilter = jwtTokenFilter;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling().authenticationEntryPoint((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,e.getMessage());
                }).and()
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/api/employer/**").hasRole("EMPLOYER")
                .antMatchers(HttpMethod.POST, "/api/jobAdvertisement/**").hasRole("EMPLOYER")
                .antMatchers(HttpMethod.PUT, "/api/jobAdvertisement/**").hasRole("EMPLOYER")
                .antMatchers(HttpMethod.GET, "/api/jobAdvertisement/**").hasRole("JOB_SEEKER")
                .antMatchers( "/api/jobSeeker/**").hasRole("JOB_SEEKER")
                .antMatchers("/api/jobPosition/**").hasRole("JOB_SEEKER")
                .anyRequest().authenticated();

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
