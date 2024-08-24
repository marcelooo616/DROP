package io.drop.drop_api.jwt;

import io.drop.drop_api.models.entities.usuario.Usuarios;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import java.util.logging.Logger;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${security.jwt.expiracao}")
    private String expiracao;

    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinada;

    private static final Logger logger = Logger.getLogger(JwtService.class.getName());

    public String gerarToken(Usuarios usuario) {
        logger.info("Iniciando geração do token para o usuário: " + usuario.getEmail());

        try {
            long expString = Long.parseLong(expiracao);
            LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
            Date dataExpiracao = Date.from(dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant());

            String active = usuario.is_active_user() ? "true" : "false";
            boolean isAdmin = usuario.isAdmin();

            logger.info("Dados do token - Emitente: drop_api, Assunto: " + usuario.getEmail() +
                    ", is_active: " + active + ", is_admin: " + isAdmin +
                    ", Data de Expiração: " + dataExpiracao);

            String token = Jwts.builder()
                    .setIssuer("drop_api") // Emitente do token
                    .setSubject(usuario.getEmail())
                    .claim("is_active", active)
                    .claim("is_admin", isAdmin)
                    .setIssuedAt(new Date()) // Data de emissão do token
                    .setExpiration(dataExpiracao)
                    .signWith(SignatureAlgorithm.HS512, chaveAssinada)
                    .compact();

            logger.info("Token gerado com sucesso para o usuário: " + usuario.getEmail());
            return token;
        } catch (Exception e) {
            logger.severe("Erro ao gerar token: " + e.getMessage());
            throw new RuntimeException("Erro ao gerar token", e);
        }
    }



    private Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(chaveAssinada)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenValido(String token){
        try {
            Claims claims = obterClaims(token);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(data);
        }catch (Exception e){
            return false;
        }
    }

    /*public boolean tokenValido(String token) {
        try {
            Claims claims = obterClaims(token);
            boolean usuarioAtivo = (boolean) claims.get("ativo");
            System.out.println("USUARIO: " + usuarioAtivo);
            return !claims.getExpiration().before(new Date()) && usuarioAtivo;
        } catch (Exception e) {
            return false;
        }
    }*/




    public String obterLoginUsuario(String token) throws ExpiredJwtException {
        Claims claims = obterClaims(token);
        return claims.getSubject();
    }
}
