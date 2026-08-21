package in.MomenTarek.musifyapi.controller;

import in.MomenTarek.musifyapi.document.User;
import in.MomenTarek.musifyapi.dto.AuthRequest;
import in.MomenTarek.musifyapi.dto.AuthResponse;
import in.MomenTarek.musifyapi.dto.RegisterRequest;
import in.MomenTarek.musifyapi.dto.UserResponse;
import in.MomenTarek.musifyapi.service.AppUserDetailsService;
import in.MomenTarek.musifyapi.service.UserService;
import in.MomenTarek.musifyapi.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final AppUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        try {
            User exitingUser=userService.findByEmail(request.getEmail());
            if(request.getPortal().equalsIgnoreCase("admin")&&exitingUser.getRole().name().equalsIgnoreCase("USER")){
                return ResponseEntity.badRequest().body("Email/Password is incorrect");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

            UserDetails userDetails=userDetailsService.loadUserByUsername(request.getEmail());

            String token=jwtUtil.generateToken(userDetails,exitingUser.getRole().name());

            return ResponseEntity.ok(new AuthResponse(token,request.getEmail(),exitingUser.getRole().name()));

        }catch (BadCredentialsException e){
            return ResponseEntity.badRequest().body("Email/Password is incorrect");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        try {
            UserResponse response=userService.registerUser(request);
            return ResponseEntity.ok(response);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PostMapping("/promote-admin")
    public ResponseEntity<?> promoteToAdmin (@RequestBody Map<String,String> request){
        try{
            User user=userService.promoteToAdmin(request.get("email"));
            return ResponseEntity.ok(new AuthResponse(null,user.getEmail(),"ADMIN"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Failed to promote user to admin");
        }
    }
}
