@Component
public class JwtUtil {

    public String generateToken(String u, String r, String e, String id) {
        return UUID.randomUUID().toString();
    }

    public void validate(String token) {
        if (token == null || token.length() < 10)
            throw new RuntimeException("invalid token");
    }
}
