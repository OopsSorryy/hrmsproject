package hrms.hrms.core.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hrms.hrms.core.business.concretes.UserManager;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {


    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(JwtTokenFilter.class);

    private final JwtTokenUtil jwtTokenUtil;


    private final UserManager userDetailsManager;
    @Autowired
    public JwtTokenFilter(JwtTokenUtil jwtTokenUtil, UserManager userDetailsManager) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsManager = userDetailsManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException{

        try {
            String token = getToken(req);
            if (token != null && jwtTokenUtil.validateAccessToken(token)) {
                String userName = jwtTokenUtil.getUserNameFromToken(token);
                UserDetails userDetails = userDetailsManager.loadUserByUsername(userName);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);

            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        filterChain.doFilter(req, res);
    }


    private String getToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer"))
            return header.replace("Bearer ", "");
        return null;
    }
}