package pl.documents.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import pl.documents.JwtFilter;

import java.util.Arrays;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    private final Encryption encryption;

    public WebSecurityConfig(final Encryption encryption)
    {
        this.encryption = encryption;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.cors().and().csrf().disable();
        http.authorizeRequests()
                .antMatchers("/register").permitAll()
                .antMatchers("/deleteworker").hasAnyRole("ADMIN","HR_EMPLOYEE")
                .antMatchers("/allusers").hasAnyRole("ADMIN","HR_EMPLOYEE")
                .antMatchers("/deletehr").hasRole("ADMIN")
                .antMatchers("/generatelink").hasAnyRole("ADMIN","HR_EMPLOYEE")
                .antMatchers("/generatelinkadmin").hasRole("ADMIN")
                .antMatchers("/alltokens").hasRole("ADMIN")
                .antMatchers("/uploadfiles/*").hasRole("ADMIN")
                .antMatchers("/deletefile/*").hasRole("ADMIN")
                .antMatchers("/downloadfilenames").hasAnyRole("WORKER","ADMIN","HR_EMPLOYEE")
                .antMatchers("/getobligatorynames/*").hasAnyRole("WORKER","ADMIN","HR_EMPLOYEE")
                .antMatchers("/downloadfile/*").hasAnyRole("WORKER","ADMIN","HR_EMPLOYEE")
                .and()
                .addFilter(new JwtFilter(authenticationManager(),encryption.getSequence()));
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
