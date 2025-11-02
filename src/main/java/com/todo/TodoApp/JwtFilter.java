package com.todo.TodoApp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	private static final String SECRET = "MySecretStringIs****";
	private static final Algorithm ALGO = Algorithm.HMAC256(SECRET);
	private static final ObjectMapper mapper = new ObjectMapper();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String path = request.getRequestURI();

		// Skip /auth routes
		if (path.startsWith("/auth")) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = null;
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals("token")) {
					token = cookie.getValue();
					break;
				}
			}
		}

		// If no token
		if (token == null) {
			sendJsonError(response, "Missing token");
			return;
		}

		try {
			JWT.require(ALGO).build().verify(token);
			filterChain.doFilter(request, response);
		} catch (JWTVerificationException e) {
			sendJsonError(response, "Invalid or expired token");
		}
	}

	private void sendJsonError(HttpServletResponse response, String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		Map<String, String> errorBody = new HashMap<>();
		errorBody.put("message", message);

		response.getWriter().write(mapper.writeValueAsString(errorBody));
	}
}
