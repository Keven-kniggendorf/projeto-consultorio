package com.mballem.curso.security.config;

import com.mballem.curso.security.service.usuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private usuarioService service;

    // Aqui é onde o Spring Security irá configurar as permissões de acesso.
    // Por exemplo, a página de login e home não precisam de autenticação.
    // Qualquer outra página precisa de autenticação.
    // os que tem asteriscos quer dizer que estou chamando todos os arquivos que estão dentro da pasta.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/webjars/**", "/css/**", "/image/**", "/js/**").permitAll().
                antMatchers("/", "/home").permitAll()

                //acessos privados admin
                .antMatchers("/u/**").hasAnyAuthority("ADMIN")
                //acessos privados medicos
                .antMatchers("/medicos/**").hasAnyAuthority("MEDICO")

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login-error")
                .permitAll().and().logout().logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/acesso-negado");

    }

    // Aqui é onde o Spring Security irá buscar o usuário e senha para autenticação.
    // E irá comparar com o que está no banco de dados a mesma cripografia.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
    }



}
