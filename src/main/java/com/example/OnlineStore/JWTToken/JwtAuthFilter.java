package com.example.OnlineStore.JWTToken;

import com.example.OnlineStore.entity.ResponseMessage;
import com.example.OnlineStore.enums.ResultCode;
import com.example.OnlineStore.service.MyUserDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final MyUserDetailService userDetailsService;

    public JwtAuthFilter(MyUserDetailService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        if(nonNull(authorizationHeader)) {

            String jwtToken = null;
            String userName = null;
            try {
                jwtToken = getJwtToken(authorizationHeader);
                userName = JsonWebTokenUtil.validateToken(jwtToken);
            } catch (SignatureException e) {
                log.error("Ошибка при обработке JWT токена", e);
                ResponseMessage<String> errorMessage = new ResponseMessage<>(
                        null, ResultCode.FAIL, "Ошибка при обработке JWT токена", ResultCode.FAIL.getHttpCode());
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                try (PrintWriter out = response.getWriter()) {
                    out.print(new ObjectMapper().writeValueAsString(errorMessage));
                    out.flush();
                } catch (IOException ioException) {
                    log.error("Ошибка при записи сообщения об ошибке в ответ", ioException);
                }
                return;
            }
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtToken(String authorizationHeader) {
        if(isNull(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid Auhorization Header");
        }
        return authorizationHeader.replaceFirst("Bearer ", "").trim();
    }
}